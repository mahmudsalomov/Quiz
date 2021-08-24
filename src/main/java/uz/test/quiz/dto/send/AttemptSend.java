package uz.test.quiz.dto.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptSend {
    private Integer id;
    private BlockSend block;
    private boolean active;
    private long startDate;
    private long endDate;
}
