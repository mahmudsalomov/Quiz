package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Answer;
import uz.test.quiz.entity.Language;

import java.util.Optional;


public interface LanguageRepository extends JpaRepository<Language,String> {
    boolean existsByName(String name);
    Optional<Language> findByName(String name);
}
