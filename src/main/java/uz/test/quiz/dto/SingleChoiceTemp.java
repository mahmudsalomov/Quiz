package uz.test.quiz.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SingleChoiceTemp {
    private String text;
    private String A;
    private String B;
    private String C;
    private String D;
    private String rightAnswer;
    private List<String> categories;
}
