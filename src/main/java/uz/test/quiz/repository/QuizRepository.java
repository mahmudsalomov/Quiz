package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Quiz;


public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
