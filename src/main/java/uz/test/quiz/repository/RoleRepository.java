package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Role;


public interface RoleRepository extends JpaRepository<Role,Short> {
}
