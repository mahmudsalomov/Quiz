package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.enums.QuizType;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Answer extends AbsEntityInteger {
    private boolean right;
    @ManyToOne
    private Quiz quiz;
}
