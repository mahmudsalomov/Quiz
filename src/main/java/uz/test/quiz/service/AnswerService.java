package uz.test.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.test.quiz.entity.Answer;

import uz.test.quiz.repository.*;
import uz.test.quiz.utils.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

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





    public Answer save(String text,boolean status) {
        try{
            Answer answer=Answer
                    .builder()
                    .text(text)
                    .isRight(status)
                    .build();
            answer=answerRepository.save(answer);
            return answer;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
