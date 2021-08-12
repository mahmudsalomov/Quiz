package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.BlockTranslation;


public interface BlockTranslationRepository extends JpaRepository<BlockTranslation,Integer> {
}
