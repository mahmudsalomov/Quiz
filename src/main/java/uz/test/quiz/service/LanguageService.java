package uz.test.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.test.quiz.entity.Language;
import uz.test.quiz.payload.ApiResponse;
import uz.test.quiz.payload.Payload;
import uz.test.quiz.repository.LanguageRepository;

import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    public ApiResponse save(Language language){
        try {
            if (languageRepository.existsById(language.getCode())) return Payload.badRequest("This language already exists!");
            if (languageRepository.existsByName(language.getName())) return Payload.badRequest("This language already exists!");
            Language save = languageRepository.save(language);
            return Payload.created("Successfully saved!",save);
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public ApiResponse getAll(){
        try {
            return Payload.ok(languageRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public ApiResponse getByCode(String code){
        try {
            Optional<Language> language = languageRepository.findById(code);
            return language.map(Payload::ok).orElseGet(() -> Payload.notFound("Language not found!"));
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public ApiResponse getByName(String name){
        try {
            Optional<Language> language = languageRepository.findByName(name);
            return language.map(Payload::ok).orElseGet(() -> Payload.notFound("Language not found!"));
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

}
