package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.dto.send.AttemptSend;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

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

//    @OneToMany
//    private List<AttemptAnswer> attemptAnswerList;

    private boolean active;

    private long startDate;
    private long endDate;

    public AttemptSend attemptToAttemptSend(){
        try {
            return AttemptSend
                    .builder()
                    .id(this.getId())
                    .active(active)
                    .endDate(endDate)
                    .startDate(startDate)
                    .block(block.blockToBlockSend())
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
