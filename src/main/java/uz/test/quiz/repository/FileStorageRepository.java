package uz.test.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.test.quiz.entity.FileStorage;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage,Integer> {

    FileStorage findByHashId(String hashId);
}
