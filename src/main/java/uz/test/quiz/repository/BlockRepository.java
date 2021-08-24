package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Answer;
import uz.test.quiz.entity.Block;

import java.util.List;
import java.util.Optional;


public interface BlockRepository extends JpaRepository<Block,Integer> {
    List<Block> findAllByOrderById();
    boolean existsByName(String name);
    Optional<Block> findByName(String name);
}
