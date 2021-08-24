package uz.test.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.test.quiz.dto.receive.AttemptAnswerReceive;
import uz.test.quiz.dto.receive.AttemptReceive;
import uz.test.quiz.entity.*;
import uz.test.quiz.payload.ApiResponse;
import uz.test.quiz.payload.Payload;
import uz.test.quiz.repository.AttemptAnswerRepository;
import uz.test.quiz.repository.AttemptRepository;
import uz.test.quiz.repository.BlockRepository;
import uz.test.quiz.secret.CurrentUser;

import java.util.*;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;
    @Autowired
    private AttemptAnswerRepository attemptAnswerRepository;
    @Autowired
    private BlockRepository blockRepository;
    public ApiResponse save(User user, Integer blockId){
        try {
            if (user==null) return Payload.badRequest("User is null");
            if (blockId==null) return Payload.badRequest("block id is null");
            Optional<Attempt> lastAttempt = attemptRepository.findByUserAndActiveTrue(user);

            if (lastAttempt.isPresent()){
                if (lastAttempt.get().getEndDate()>System.currentTimeMillis()) return Payload.badRequest("Sizda yakunlanmagan test mavjud!");
                else lastAttempt.get().setActive(false);
                attemptRepository.save(lastAttempt.get());
            }

            Optional<Block> block = blockRepository.findById(blockId);
            if (!block.isPresent()) return Payload.notFound("Block not found!");
            Attempt attempt= Attempt.builder()
                    .active(true)
                    .block(block.get())
                    .user(user)
                    .startDate(System.currentTimeMillis())
                    .endDate(System.currentTimeMillis()+block.get().getLimit()*1000)
                    .build();
            attempt=attemptRepository.save(attempt);
            return Payload.created(attempt.attemptToAttemptSend());
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }


    public ApiResponse getCurrent(User user){
        try {
            if (user==null) return Payload.notFound();
            Optional<Attempt> attempt = attemptRepository.findByUserAndActiveTrue(user);
            return attempt.map(value -> Payload.ok(value.attemptToAttemptSend())).orElseGet(() -> Payload.notFound("Sizda yakunlanmagan testlar mavjud emas!"));
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }


    public ApiResponse complete(User user, AttemptReceive receive){
        try {
            if (user==null) return Payload.badRequest("User is null!");
            if (receive.getId()==null) return Payload.badRequest("Attempt id is null");
            Optional<Attempt> attempt = attemptRepository.findByUserAndIdAndActiveTrue(user,receive.getId());
            if (!attempt.isPresent()) return Payload.badRequest("Attempt not found");
            Block block=attempt.get().getBlock();
            List<Quiz> quizList=block.getQuizList();
            quizList.sort(Comparator.comparingInt(Quiz::getId));
            List<AttemptAnswerReceive> answers=receive.getAnswers();
            answers.sort(Comparator.comparingInt(AttemptAnswerReceive::getQuizId));
            ApiResponse apiResponse = quizChecker(answers, quizList);
            if (!apiResponse.isSuccess()) return apiResponse;
            Result result=new Result();
            result.setAttemptId(attempt.get().getId());
            result.setBlockId(block.getId());
            result.setBlockName(block.getName());
            Set<Check> checkList=new HashSet<>();
            List<AttemptAnswer> attemptAnswerList=new ArrayList<>();
            for (int i = 0; i <answers.size() ; i++) {
                int finalI = i;
                Optional<Answer> answer=quizList.get(i).getAnswerList().stream().filter(a-> a.getId().equals(answers.get(finalI).getAnswerId())).findFirst();
                if (!answer.isPresent()) return Payload.badRequest("Noto'g'ri id lar!");
                AttemptAnswer attemptAnswer= AttemptAnswer
                        .builder()
                        .attempt(attempt.get())
                        .quiz(quizList.get(i))
                        .answer(answer.get())
                        .build();
                attemptAnswerList.add(attemptAnswer);
                checkList.add(Check
                        .builder()
                        .answer(answer.get().getText())
                        .isRight(answer.get().isRight())
                        .quiz(quizList.get(i).getText())
                        .build());
            }

            result.setResults(checkList);
            for (AttemptAnswer answer : attemptAnswerList) {
                attemptAnswerRepository.save(answer);
            }
            attempt.get().setActive(false);
            attemptRepository.save(attempt.get());
            return Payload.accepted("Natija",result);

        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }







    private Integer checker(List<Answer> answers){
        try {
            for (Answer answer : answers) {
                if (answer.isRight()) return answer.getId();
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    private ApiResponse quizChecker(List<AttemptAnswerReceive> answers,List<Quiz> quizList){
        try {
            if (answers.size()!= quizList.size()) return Payload.badRequest("Javoblar to'liq emas!");
            for (int i = 0; i <answers.size() ; i++) {
                if (!answers.get(i).getQuizId().equals(quizList.get(i).getId())) return Payload.badRequest("Noto'g'ri id lar!");
                if (!answerIdCheck(answers.get(i),quizList.get(i).getAnswerList())) return Payload.badRequest("Noto'g'ri id lar!");
            }
            return Payload.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }

    }

    private boolean answerIdCheck(AttemptAnswerReceive receive, List<Answer> answers){
        try {
            boolean check=false;
            for (Answer answer : answers) {
                if (answer.getId().equals(receive.getAnswerId())) {
                    check = true;
                    break;
                }
            }
            return check;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private Check attemptAnswer(AttemptAnswerReceive receive,Quiz quiz){
        try {
            boolean check=false;
            if (receive.getAnswerId().equals(checker(quiz.getAnswerList()))){
                check=true;
            }
            return new Check(quiz.getText(),getReceiveAnswerText(receive.getAnswerId(),quiz.getAnswerList()),check);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String getReceiveAnswerText(Integer id,List<Answer> answers){
        try {
            for (Answer answer : answers) {
                if (answer.getId().equals(id)) return answer.getText();
            }
            return "";
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }


}
