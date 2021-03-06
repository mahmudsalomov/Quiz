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
public class BlockReceive {
    private Integer id;
    private String name;
    private String description;
    private int limit;
    private List<Integer> quizIdList;
}
