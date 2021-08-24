package uz.test.quiz.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.test.quiz.dto.UserReceive;
import uz.test.quiz.entity.*;
import uz.test.quiz.payload.ApiResponse;
import uz.test.quiz.payload.Payload;
import uz.test.quiz.repository.*;

@Service
public class Converter {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public static HttpEntity<?> transform(ApiResponse apiResponse){
        return ResponseEntity.status(apiResponse.getStatus().getCode()).body(apiResponse);
    }


    public ApiResponse checkAllForRegister(UserReceive userReceive){
        if (userReceive.getEmail()==null) Payload.badRequest("Email is null!");
        if (userReceive.getUsername()==null) Payload.badRequest("Username is null!");
        if (userReceive.getPassword()==null) Payload.badRequest("Password is null!");
        if (checkActiveWithEmail(userReceive.getEmail())) return Payload.conflict("Bu email oldin ro'yhatdan o'tgan!");
        if (checkUsername(userReceive.getUsername())) return Payload.conflict("Bu username oldindan mavjud!");
        if (checkPassword(userReceive.getPassword())) return Payload.badRequest("Parol 6 ta belgidan ko'p bo'lishi kerak!");
        return Payload.ok();
    }

    public boolean checkEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean checkActiveWithEmail(String email){
        return userRepository.existsByEmailAndActiveTrue(email);
    }

    public boolean checkPassword(String password){
        return password.length() < 6;
    }

    public boolean checkUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public ApiResponse checkCode(String code, String email){
        try {
            if (!checkEmail(email)) return Payload.badRequest("Email noto'g'ri!");
            User user= userRepository.findByEmail(email).get();
            if (user.isActive()) return Payload.conflict("Bu email ro'yhatdan o'tgan!");
            if (user.getCode().equals(code)) {
                user.setActive(true);
                return Payload.ok(user);
            }
            return Payload.badRequest("Kod noto'g'ri");
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }

    }



}
