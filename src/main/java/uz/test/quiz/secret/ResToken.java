package uz.test.quiz.secret;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResToken {
    private String role;
    private String type="Bearer ";
    private String token;

    public ResToken(String token) {
        this.token = token;
    }

    public ResToken(String token,String role) {
        this.token = token;
        this.role=role;
    }
}
