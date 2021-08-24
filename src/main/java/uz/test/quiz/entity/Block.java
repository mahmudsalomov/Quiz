package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.dto.send.BlockSend;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Table(name = "bbbb")
public class Block extends AbsEntityInteger {
    @Column(unique = true)
    private String name;
    private String description;
    @Column(name = "time_limit")
    private int limit;
    @ManyToMany
    private List<Quiz> quizList;

    public BlockSend blockToBlockSend(){
        try {
            return BlockSend
                    .builder()
                    .description(description)
                    .limit(limit)
                    .name(name)
                    .id(this.getId())
                    .quizList(quizList.stream().map(Quiz::quizToQuizSend).collect(Collectors.toList()))
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
