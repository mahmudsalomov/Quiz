package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Table(name = "bbbb")
public class Block extends AbsEntityInteger {

    private Integer timeLimit;
    @ManyToMany
    private List<Quiz> quizList;
}
