package ReleaseBack.Back.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(name = "user_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")  // 外键列，同时也是主键
    private User user;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;
    
    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 20)
    private Gender gender;

    public enum Gender {
        male,
        female,
        other,
        undisclosed
    }
}
