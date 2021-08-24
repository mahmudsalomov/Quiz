package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Answer;
import uz.test.quiz.entity.Category;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,String> {

    boolean existsByName(String name);
    List<Category> findAllByOrderByNameAsc();

}
