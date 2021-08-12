package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Answer;
import uz.test.quiz.entity.Block;


public interface BlockRepository extends JpaRepository<Block,Integer> {
}
