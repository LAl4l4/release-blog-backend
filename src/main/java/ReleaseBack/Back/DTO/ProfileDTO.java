package ReleaseBack.Back.DTO;

import java.time.LocalDate;
import ReleaseBack.Back.entity.Profile;
import lombok.Data;

@Data
public class ProfileDTO {
    private Integer id;
    private String bio;
    private String avatarUrl;
    private LocalDate birthday;
    private Profile.Gender gender;
}
