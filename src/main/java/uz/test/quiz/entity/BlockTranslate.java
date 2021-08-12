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
public class BlockTranslate extends AbsEntityInteger {
    private String name;

    @ManyToOne
    private Block block;
    @ManyToOne
    private Language language;
}
