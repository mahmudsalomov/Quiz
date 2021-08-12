package uz.test.quiz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.AttemptAnswer;


public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswer,Integer> {
}
