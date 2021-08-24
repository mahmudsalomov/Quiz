package uz.test.quiz.dto.receive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptAnswerReceive {
    private Integer quizId;
    private Integer answerId;
}
