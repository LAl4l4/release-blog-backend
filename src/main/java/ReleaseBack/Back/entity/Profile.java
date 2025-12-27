package ReleaseBack.Back.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "profiles")
public class Profile {

    //mybatis会直接无视@Column等注解，那是JPA的，需要在xml里面自己配置映射
    private Integer id;

    @OneToOne
    //mapsid必须从数据库中取id，所以要先把user保存进去才行
    @MapsId
    @JoinColumn(name = "user_id")  // 外键列，同时也是主键
    private User user;

    private String bio;
    
    private String avatarUrl;

    private LocalDate birthday;

    private Gender gender;

    public enum Gender {
        male,
        female,
        other,
        undisclosed
    }
}
