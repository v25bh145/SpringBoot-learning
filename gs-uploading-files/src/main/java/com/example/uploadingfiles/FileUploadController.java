package com.example.uploadingfiles;

import com.example.uploadingfiles.storage.StorageFileNotFoundException;
import com.example.uploadingfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    //存储服务，StorageService这个类出自服务层(./storage/StorageService.java)
    private final StorageService storageService;


    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    //get路由，将存储的文件列出来
    //Model类:在后端渲染的时候使用，在控制器中，数据可以被存放到model中(key-value形式)，当需要生成HTML时，模板引擎就会根据变量名在model中查询。
    @GetMapping("/")
    public String listUploadFiles(Model model) throws IOException {
        //loadAll: 返回一个Stream<path>类，path来自于java.nio.file.Path
        //Stream对象: 一个来自数据源的元素的队列，支持聚合操作(聚合：filter/map/reduce/find/match/sorted/...)，在这里元素的数据类型都是path
            //Stream的map操作即是遍历一遍Stream中的元素，返回[元素的url构成的数组](聚合操作可以连接起来，将一个流转换为另一个流。这些操作不会消耗流，其目的是建立一个流水线)
                //MvcUriComponentsBuilder：Uri的组件器，使用了builder设计模式，在这里的作用是返回一个path对象的uri字符串
            //Stream的collect操作，是Stream的终端操作，负责收集器的功能，在这里把[uri的Stream流]转换为[list数组](终端操作是一个归约操作)
        model.addAttribute("files", storageService.loadAll().map(path ->
                //MvcUriComponentsBuilder.fromMethodName：(A, B, C)传入 类A的方法B，文件路径的字符串格式C，返回Uri值
                MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                        .build()
                        .toUri()
                        .toString())
                .collect(Collectors.toList()));
        return "uploadForm";
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        //输入文件名，导入文件类
        Resource file = storageService.loadAsResource(filename);
        //ok(): 设置status ok
        //header(): 添加header
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    //上传文件
    @PostMapping("/")
    //@RequestParam: 参数为file的文件
    //RedirectAttributes.addFlashAttribute: 以key-value形式，将变量(这里是个String类型)输送到渲染引擎中
    //redirect:/ 重定向到post根目录(get方法)
    public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/";
    }

    //错误处理，这里没细看
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
