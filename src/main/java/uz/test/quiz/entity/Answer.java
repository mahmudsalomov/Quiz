package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.enums.QuizType;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Table(name = "aaaa")
public class Answer extends AbsEntityInteger {
    private Boolean isRight;
    @ManyToOne
    private Quiz quiz;
}
