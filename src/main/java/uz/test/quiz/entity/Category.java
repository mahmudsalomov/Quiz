package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.template.AbsEntityShort;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    private String name;
    private String description;
}
