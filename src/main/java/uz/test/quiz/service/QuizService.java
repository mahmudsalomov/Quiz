package uz.test.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.test.quiz.dto.receive.QuizReceive;
import uz.test.quiz.entity.Answer;
import uz.test.quiz.entity.Category;
import uz.test.quiz.entity.Quiz;
import uz.test.quiz.entity.enums.QuizType;
import uz.test.quiz.payload.ApiResponse;
import uz.test.quiz.payload.Payload;
import uz.test.quiz.repository.*;
import uz.test.quiz.utils.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Converter converter;
    @Autowired
    private AnswerService answerService;


    public ApiResponse save(QuizReceive receive){
        try {
            Optional<Category> category = categoryRepository.findById(receive.getCategory());
            if (!category.isPresent()) return Payload.notFound("Category not found!");
            if (receive.getOptions().size()<2) return Payload.badRequest("Javoblar soni 2 tada ko'p bo'lishi kerak!");
            List<Answer> answerList=new ArrayList<>();
            boolean isRight;
            for (int i = 0; i <receive.getOptions().size(); i++) {
                isRight= receive.getOptions().get(i).equals(receive.getRight());
                Answer answer = answerService.save(receive.getOptions().get(i), isRight);
                answerList.add(answer);
            }
            Quiz quiz=Quiz
                    .builder()
                    .answerList(answerList)
                    .rate(receive.getRate())
                    .type(QuizType.SINGLE_CHOICE)
                    .category(category.get())
                    .text(receive.getText())
                    .build();
            quiz=quizRepository.save(quiz);
            return Payload.created(quiz.quizToQuizSend());
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }



}
