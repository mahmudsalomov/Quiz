package uz.test.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.test.quiz.dto.SingleChoiceTemp;
import uz.test.quiz.dto.UserReceive;
import uz.test.quiz.dto.receive.BlockReceive;
import uz.test.quiz.dto.receive.QuizReceive;
import uz.test.quiz.entity.Category;
import uz.test.quiz.entity.User;
import uz.test.quiz.secret.CurrentUser;
import uz.test.quiz.service.BlockService;
import uz.test.quiz.service.CategoryService;
import uz.test.quiz.service.QuizService;
import uz.test.quiz.service.UserService;
import uz.test.quiz.utils.Converter;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private BlockService blockService;


    /** for User **/

    @PostMapping("user/add")
    public HttpEntity<?> addUser(@RequestBody UserReceive receive){
        return Converter.transform(userService.save(receive));
    }

    @PutMapping("user/edit")
    public HttpEntity<?> editUser(@CurrentUser User user, @RequestBody UserReceive receive){
        return Converter.transform(userService.edit(user,receive));
    }
    @GetMapping("user/all")
    public HttpEntity<?> allUser(){
        return Converter.transform(userService.getAll());
    }

    @GetMapping("user/{id}")
    public HttpEntity<?> oneUser(@PathVariable Integer id){
        return Converter.transform(userService.getOne(id));
    }

    @DeleteMapping("user/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id){
        return Converter.transform(userService.delete(id));
    }




    /** for Category **/

    @PostMapping("category/add")
    public HttpEntity<?> addCategory(@RequestBody Category category){
        return Converter.transform(categoryService.save(category));
    }

    @PutMapping("category/edit")
    public HttpEntity<?> editCategory(@RequestBody Category category){
        return Converter.transform(categoryService.edit(category));
    }

    @GetMapping("category/all")
    public HttpEntity<?> allCategory(){
        return Converter.transform(categoryService.getAll());
    }

    @GetMapping("category/{name}")
    public HttpEntity<?> oneCategory(@PathVariable String name){
        return Converter.transform(categoryService.getOne(name));
    }

    @DeleteMapping("category/{name}")
    public HttpEntity<?> deleteCategory(@PathVariable String name){
        return Converter.transform(categoryService.delete(name));
    }



    /** Quiz **/

    @PostMapping("quiz/add")
    public HttpEntity<?> addQuiz(@RequestBody QuizReceive receive){
        return Converter.transform(quizService.save(receive));
    }






    /** Block **/

    @PostMapping("block/add")
    public HttpEntity<?> addBlock(@RequestBody BlockReceive receive){
        return Converter.transform(blockService.save(receive));
    }




}
