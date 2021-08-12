package uz.test.quiz.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import payload.ApiResponse;

@Service
public class Converter {
    public static HttpEntity<?> transform(ApiResponse apiResponse){
        return ResponseEntity.status(apiResponse.getStatus().getCode()).body(apiResponse);
    }
}
