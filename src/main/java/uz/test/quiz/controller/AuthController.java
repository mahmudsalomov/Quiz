package uz.test.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import payload.Payload;
import uz.test.quiz.dto.receive.UserReceive;
import uz.test.quiz.secret.ResToken;
import uz.test.quiz.secret.SignIn;
import uz.test.quiz.service.AuthService;
import uz.test.quiz.utils.Converter;

import javax.servlet.http.HttpServletRequest;


@RestController
@Controller
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody SignIn signIn){
        ResToken resToken=authService.signIn(signIn);
        return ResponseEntity.status(resToken!=null?200:401).body(resToken);
    }


    @GetMapping("/test")
    public HttpEntity<?> test(){
        return Converter.transform(Payload.ok());
    }


    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody UserReceive receive){
        return Converter.transform(authService.register(receive));
    }

    @PostMapping("/verify")
    public HttpEntity<?> verify(@RequestParam String code,@RequestParam String email){
        return Converter.transform(authService.verify(code,email));
    }

}
