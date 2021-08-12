package uz.test.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.test.quiz.component.CodeGenerator;
import uz.test.quiz.dto.receive.UserReceive;
import uz.test.quiz.entity.User;
import uz.test.quiz.entity.enums.RoleName;
import uz.test.quiz.payload.ApiResponse;
import uz.test.quiz.payload.ApiResponseObject;
import uz.test.quiz.payload.Payload;
import uz.test.quiz.repository.RoleRepository;
import uz.test.quiz.repository.UserRepository;
import uz.test.quiz.secret.JwtTokenProvider;
import uz.test.quiz.secret.ResToken;
import uz.test.quiz.secret.SignIn;
import uz.test.quiz.utils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;


@Service
public class AuthService implements UserDetailsService {

    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;
    AuthenticationManager authenticationManager;
    @Autowired
    private Converter converter;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public User loadByUserId(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user not found"));

    }

    public ResToken signIn(SignIn signIn) {
        try {
//            System.out.println("sign in " + signIn.getPassword());
//            System.out.println("sign in " + signIn.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User principal = (User) authentication.getPrincipal();

            String jwt = jwtTokenProvider.generateToken(principal);
            return new ResToken(jwt,principal.getRoles().stream().findFirst().get().getRoleName().name());
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }


    public ApiResponse register(UserReceive userReceive) {
        try {
            ApiResponse apiResponse = converter.checkAllForRegister(userReceive);
            if (apiResponse.isSuccess()){
                User user= User.userReceiveToUser(userReceive);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                if (converter.checkEmail(user.getEmail())){
                    user.setId(userRepository.findByEmail(user.getEmail()).get().getId());
                }
                user.setCode(CodeGenerator.generate());
                user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.getByRoleName(RoleName.USER))));
                boolean sendCode = emailService.sendCode(user);
                if (!sendCode) return Payload.conflict("An error occurred while sending the code!");
                userRepository.save(user);
                return Payload.ok("A confirmation code has been sent to your email!");
            }
            return apiResponse;
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }

    }

    public ApiResponse verify(String code, String email){
        try {
            ApiResponse apiResponse = converter.checkCode(code, email);
            if (apiResponse.isSuccess()){
                ApiResponseObject apiResponseObject= (ApiResponseObject) apiResponse;
                User user= (User) apiResponseObject.getObject();
                userRepository.save(user);
                return Payload.accepted("Muvaffaqiyatli ro'yhatdan o'tildi!");
            }
            return apiResponse;
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }


}
