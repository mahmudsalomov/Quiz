package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.enums.QuizType;
import uz.test.quiz.entity.template.AbsEntityInteger;
import uz.test.quiz.entity.template.AbsEntityShort;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Language {
    @Id
    private String code;
    private String name;
}
