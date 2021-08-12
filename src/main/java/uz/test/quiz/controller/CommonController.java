package uz.test.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.test.quiz.service.UserService;
import uz.test.quiz.utils.Converter;

@RestController
@RequestMapping("api/common")
public class CommonController {

    @Autowired
    private UserService userService;



    @GetMapping("/user/all")
    public HttpEntity<?> allUser(){
        return Converter.transform(userService.getAll());
    }

    @DeleteMapping("/user/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id){
        return Converter.transform(userService.delete(id));
    }


}
