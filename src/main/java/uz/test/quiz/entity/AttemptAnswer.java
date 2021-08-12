package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AttemptAnswer extends AbsEntityInteger {
    @ManyToOne
    private Attempt attempt;
    @ManyToOne
    private Quiz quiz;
    @ManyToOne
    private Answer answer;
}
