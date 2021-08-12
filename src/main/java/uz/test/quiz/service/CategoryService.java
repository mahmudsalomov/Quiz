package uz.test.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.test.quiz.entity.Category;
import uz.test.quiz.payload.ApiResponse;
import uz.test.quiz.payload.Payload;
import uz.test.quiz.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public ApiResponse save(Category category){
        try {
            if (category.getId()!=null) return Payload.badRequest();
            if (category.getName()==null) return Payload.badRequest("Name is null!");
            if (categoryRepository.existsByName(category.getName())) return Payload.badRequest("This name already exists!");
            Category save = categoryRepository.save(category);
            return Payload.created(save);
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }


    public ApiResponse edit(Category category){
        try {
            if (category.getId()==null) return Payload.badRequest("Id is null!");
            if (!categoryRepository.existsById(category.getId())) return Payload.notFound("Category not found!");
            Category edit = categoryRepository.save(category);
            return Payload.accepted("Edited",edit);
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public ApiResponse delete(Short id){
        try {
            if (id==null) return Payload.badRequest("Id is null!");
            if (!categoryRepository.existsById(id)) return Payload.notFound("Category not found");
            categoryRepository.deleteById(id);
            return Payload.ok("Deleted");
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public ApiResponse getAll(){
        try {
            return Payload.ok(categoryRepository.findAllByOrderByNameAsc());
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict(null);
        }
    }

    public ApiResponse getOne(Short id){
        try {
            if (id==null) return Payload.badRequest("Id is null!");
            if (!categoryRepository.existsById(id)) return Payload.notFound("Category not found");
            Optional<Category> category = categoryRepository.findById(id);
            return Payload.ok(category);
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict(null);
        }
    }

}
