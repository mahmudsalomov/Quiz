package uz.test.quiz.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.test.quiz.entity.enums.RoleName;
import uz.test.quiz.entity.template.AbsEntityShort;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role extends AbsEntityShort implements GrantedAuthority{


    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
