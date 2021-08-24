package uz.test.quiz.dto.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizSend {
    private Integer id;
    private String text;
    private List<AnswerSend> options;
    private String category;
}
