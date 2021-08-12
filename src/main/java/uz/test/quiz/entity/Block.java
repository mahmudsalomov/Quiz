package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Block extends AbsEntityInteger {
    private int limit;
    @ManyToMany
    private List<Quiz> quizList;
}
