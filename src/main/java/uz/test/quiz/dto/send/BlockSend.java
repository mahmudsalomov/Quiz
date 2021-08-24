package uz.test.quiz.dto.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.test.quiz.entity.Quiz;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockSend {
    private Integer id;
    private String name;
    private String description;
    private int limit;
    private List<QuizSend> quizList;
}
