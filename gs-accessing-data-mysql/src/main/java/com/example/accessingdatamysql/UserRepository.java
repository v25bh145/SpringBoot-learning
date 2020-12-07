package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

//用于保存用户记录的存储库
//数据访问层接口
//CRUD: Create, Read, Update, Delete
//<User, Integer>: <实体(表)类, 实体(表)类中ID的类型>
public interface UserRepository extends CrudRepository<User, Integer> {

}
