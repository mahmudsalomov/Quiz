package uz.test.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.test.quiz.entity.User;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;



    public boolean sendCode(User user) {

        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setText(user.getCode());
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject("Tasdiqlash kodi");
            sender.send(simpleMailMessage);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
