package uz.test.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.test.quiz.dto.receive.AttemptAnswerReceive;
import uz.test.quiz.dto.receive.AttemptReceive;
import uz.test.quiz.entity.User;
import uz.test.quiz.secret.CurrentUser;
import uz.test.quiz.service.AttemptService;
import uz.test.quiz.utils.Converter;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private AttemptService attemptService;

    @PostMapping("start/{blockId}")
    public HttpEntity<?> start(@CurrentUser User user,@PathVariable Integer blockId){
        return Converter.transform(attemptService.save(user,blockId));
    }

    @PostMapping("stop")
    public HttpEntity<?> stop(@CurrentUser User user,@RequestBody AttemptReceive receive){
        return Converter.transform(attemptService.complete(user, receive));
    }

    @GetMapping("current")
    public HttpEntity<?> current(@CurrentUser User user){
        return Converter.transform(attemptService.getCurrent(user));
    }


    @GetMapping("test")
    public HttpEntity<?> test(){
        AttemptReceive attemptReceive=new AttemptReceive();
        attemptReceive.setId(1);
        attemptReceive.getAnswers().add(AttemptAnswerReceive
                .builder()
                .answerId(1)
                .quizId(1)
                .build());
        attemptReceive.getAnswers().add(AttemptAnswerReceive
                .builder()
                .answerId(2)
                .quizId(2)
                .build());
        attemptReceive.getAnswers().add(AttemptAnswerReceive
                .builder()
                .answerId(3)
                .quizId(3)
                .build());
        attemptReceive.getAnswers().add(AttemptAnswerReceive
                .builder()
                .answerId(4)
                .quizId(4)
                .build());
        return ResponseEntity.ok(attemptReceive);
    }

}
