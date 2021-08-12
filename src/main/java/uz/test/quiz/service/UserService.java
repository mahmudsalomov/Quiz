package uz.test.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.test.quiz.component.CodeGenerator;
import uz.test.quiz.dto.receive.UserReceive;
import uz.test.quiz.entity.User;
import uz.test.quiz.entity.enums.RoleName;
import uz.test.quiz.payload.ApiResponse;
import uz.test.quiz.payload.Payload;
import uz.test.quiz.repository.RoleRepository;
import uz.test.quiz.repository.UserRepository;
import uz.test.quiz.secret.CurrentUser;
import uz.test.quiz.utils.Converter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Converter converter;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;


    public ApiResponse save(UserReceive receive){
        try {
//            if (receive.getPassword()==null) return Payload.badRequest("Password is null");
            ApiResponse apiResponse = converter.checkAllForRegister(receive);
            if (apiResponse.isSuccess()){
                User user= User.userReceiveToUser(receive);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                if (converter.checkEmail(user.getEmail())){
                    user.setId(userRepository.findByEmail(user.getEmail()).get().getId());
                }
                user.setCode(CodeGenerator.generate());
                user.setActive(true);
                user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.getByRoleName(RoleName.USER))));
//                boolean sendCode = emailService.sendCode(user);
//                if (!sendCode) return Payload.conflict("An error occurred while sending the code!");
                User save = userRepository.save(user);
                return Payload.created("Saved!",save.userToUserSend());
            }
            return apiResponse;
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }




    public ApiResponse edit(@CurrentUser User user, UserReceive receive){

        try{
            if (receive.getId()==null) return Payload.badRequest();
            if (user.getRoles().stream().findFirst().get().getRoleName()!=RoleName.SUPER_ADMIN){
                if (!user.getId().equals(receive.getId())) return Payload.forbidden();
                user.setFio(receive.getFio());
                user.setPhone(receive.getPhone());
            } else {
                user.setFio(receive.getFio());
                user.setPhone(receive.getPhone());
            }
            User save = userRepository.save(user);
            return Payload.accepted("Successfully edited",save.userToUserSend());
        }catch(Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public ApiResponse delete(Integer id){
        try{
            userRepository.deleteById(id);
            return Payload.accepted("Deleted");
        }
        catch(Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public ApiResponse getOne(Integer id){
        try{
            Optional<User> user = userRepository.findById(id);
            return user.map(Payload::ok).orElseGet(() -> Payload.notFound("User not found"));
        } catch(Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public ApiResponse getAll(){
        try{
            return Payload.ok("All user",userRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }



}
