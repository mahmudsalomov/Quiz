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
public class QuizTranslation extends AbsEntityInteger {
    private String name;
    private String text;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private Language language;
}
