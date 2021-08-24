package uz.test.quiz.entity;

import lombok.*;
import uz.test.quiz.dto.send.AnswerSend;
import uz.test.quiz.dto.send.QuizSend;
import uz.test.quiz.entity.enums.QuizType;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Quiz extends AbsEntityInteger {
    private double rate=1;

    @Column(columnDefinition = "text")
    private String text;
    @Enumerated(EnumType.STRING)
    private QuizType type;
    @ManyToOne
    private Category category;
    @OneToMany
    private List<Answer> answerList;

    public QuizSend quizToQuizSend(){
        try {
            List<AnswerSend> answerSendList=new ArrayList<>();
            for (Answer answer : answerList) {
                answerSendList.add(AnswerSend.builder()
                        .id(answer.getId())
                        .text(answer.getText())
                        .build());
            }
            return QuizSend
                    .builder()
                    .category(category.getName())
                    .id(this.getId())
                    .options(answerSendList)
                    .text(text)
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
