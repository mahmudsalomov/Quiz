package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.test.quiz.entity.Role;
import uz.test.quiz.entity.enums.RoleName;


public interface RoleRepository extends JpaRepository<Role,Short> {
    Role getByRoleName(RoleName roleName);
}
