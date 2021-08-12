package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Answer;


public interface AttemptRepository extends JpaRepository<Answer,Integer> {
}
