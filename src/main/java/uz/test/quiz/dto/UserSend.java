package uz.test.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSend {
    private Integer id;
    private String username;
    //    private String password;
    private String email;
    private String phone;
    private String fio;
    private String role;
}
