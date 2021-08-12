package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Answer;
import uz.test.quiz.entity.Language;


public interface LanguageRepository extends JpaRepository<Language,String> {
}
