package uz.test.quiz.dto.receive;

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
    private String username;
    private String password;
    private String email;
    private String phone;
    private String fio;
}
