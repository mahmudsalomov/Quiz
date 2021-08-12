package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Attempt extends AbsEntityInteger {
    @ManyToOne
    private Block block;

    @ManyToOne
    private User user;

    private boolean start;

    private Date startDate;
    private Date endDate;
}
