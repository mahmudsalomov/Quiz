package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.test.quiz.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);


    @Query(value = "select * from users where   username=:uname",nativeQuery = true)
    List<User> byUsername(@Param(value = "uname") String uname);


}
