package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.enums.QuizType;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Quiz extends AbsEntityInteger {
    private double rate=1;
    @Enumerated(EnumType.STRING)
    private QuizType type;
    @ManyToMany
    private List<Category> categoryList;
    @OneToMany
    private List<Answer> answerList;

    @ManyToMany
    private List<Block> blockList;
}
