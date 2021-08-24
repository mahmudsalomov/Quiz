package uz.test.quiz.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.test.quiz.dto.UserReceive;
import uz.test.quiz.dto.UserSend;
import uz.test.quiz.entity.template.AbsEntityInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User extends AbsEntityInteger implements UserDetails {
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    private String email;
    private String phone;
    @NotNull
    private String fio;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private boolean active=true;
    private String code;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }


    public UserSend userToUserSend(){
        return UserSend
                .builder()
                .email(email)
                .phone(phone)
                .id(this.getId())
                .username(username)
                .role(roles.stream().findFirst().get().getRoleName().name())
                .fio(fio)
                .build();
    }


    public static User userReceiveToUser(UserReceive receive){
        try {
            return User
                    .builder()
                    .email(receive.getEmail())
                    .fio(receive.getFio())
                    .username(receive.getUsername())
                    .phone(receive.getPhone())
                    .password(receive.getPassword())
                    .build();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
