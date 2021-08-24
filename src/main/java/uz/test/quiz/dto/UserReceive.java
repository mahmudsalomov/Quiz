package uz.test.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReceive {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String fio;
}
