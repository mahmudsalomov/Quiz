package uz.test.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.test.quiz.dto.receive.UserReceive;
import uz.test.quiz.entity.User;
import uz.test.quiz.secret.CurrentUser;
import uz.test.quiz.service.UserService;
import uz.test.quiz.utils.Converter;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    /** for User **/

    @PostMapping("user/add")
    public HttpEntity<?> addUser(@RequestBody UserReceive receive){
        return Converter.transform(userService.save(receive));
    }

    @PutMapping("user/edit")
    public HttpEntity<?> editUser(@CurrentUser User user, @RequestBody UserReceive receive){
        return Converter.transform(userService.edit(user,receive));
    }




    /** for Category **/

}
