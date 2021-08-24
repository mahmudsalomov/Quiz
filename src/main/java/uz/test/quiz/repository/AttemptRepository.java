package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Answer;
import uz.test.quiz.entity.Attempt;
import uz.test.quiz.entity.User;

import java.util.List;
import java.util.Optional;


public interface AttemptRepository extends JpaRepository<Attempt,Integer> {
    List<Attempt> findAllByUser(User user);
    Optional<Attempt> findByUserAndActiveTrue(User user);
    Optional<Attempt> findByUserAndIdAndActiveTrue(User user, Integer id);
}
