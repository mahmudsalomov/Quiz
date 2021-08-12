package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Answer;
import uz.test.quiz.entity.QuizTranslation;


public interface QuizTranslationRepository extends JpaRepository<QuizTranslation,Integer> {
}
