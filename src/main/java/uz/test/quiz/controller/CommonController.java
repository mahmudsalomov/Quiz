package uz.test.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.test.quiz.service.*;
import uz.test.quiz.utils.Converter;

@RestController
@RequestMapping("api/common")
public class CommonController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlockService blockService;
    @Autowired
    private AttemptService attemptService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/user/all")
    public HttpEntity<?> allUser(){
        return Converter.transform(userService.getAll());
    }

    @DeleteMapping("/user/{id}")
    public HttpEntity<?> deleteById(@PathVariable Integer id){
        return Converter.transform(userService.delete(id));
    }


    /** Block **/
    @GetMapping("block/all")
    public HttpEntity<?> allBlock(){
        return Converter.transform(blockService.getAll());
    }

}
