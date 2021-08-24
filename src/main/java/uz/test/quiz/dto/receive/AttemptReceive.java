package uz.test.quiz.dto.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AttemptReceive {
    private Integer id;
    List<AttemptAnswerReceive> answers=new ArrayList<>();
}
