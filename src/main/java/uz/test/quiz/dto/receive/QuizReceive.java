package uz.test.quiz.dto.receive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizReceive {
    private Integer id;
    private String text;
    private int rate;
    private List<String> options;
    private String right;
    private String category;
}
