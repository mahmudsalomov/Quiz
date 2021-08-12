package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.template.AbsEntityShort;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category extends AbsEntityShort {
    @Column(unique = true)
    private String name;
    private String description;
}
