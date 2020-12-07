package com.example.validatingforminput;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class WebController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    //返回一个表单模板，将模板与表单属性关联起来
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/")
    //Valid: 用于收集表单填写的数据
    //bindingResult: 用于验证与测试错误
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }
}

