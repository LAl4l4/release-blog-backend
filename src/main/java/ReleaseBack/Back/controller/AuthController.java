package ReleaseBack.Back.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ReleaseBack.Back.entity.User;
import ReleaseBack.Back.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import ReleaseBack.Back.entity.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import ReleaseBack.Back.VO.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") 
public class AuthController {

    @Autowired
    private UserService userService;

    //测试账号 admin@admin.com/admin1/admin1

    @PostMapping("/login")
    public loginVO login(
        @RequestParam String username, //可能是email或username
        @RequestParam String pass
    ) {
        User foundUser = userService.findByNameEmail(username);
        if (foundUser != null && foundUser.getPassword().equals(pass)) {
            loginVO loginVO = new loginVO();
            loginVO.setResult("登录成功");
            loginVO.setUserid(foundUser.getId());
            return loginVO;
        }
        loginVO loginVO = new loginVO();
        loginVO.setResult("用户名/邮箱或密码错误");
        loginVO.setUserid(null);
        return loginVO;
    }


    @PostMapping("/register")
    public String register(
        @RequestParam String username, 
        @RequestParam String pass,
        @RequestParam String email
    ) {
        User foundUser = userService.findByNameEmail(username);

        if (foundUser != null) {
            return "用户名已存在";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(pass);
        newUser.setEmail(email);
        
        Profile newProfile = new Profile();
        newProfile.setUser(newUser);
        newProfile.setBio("This user is too lazy to leave anything here.");
        newProfile.setAvatarUrl(null);
        newProfile.setBirthday(null);
        newProfile.setGender(Profile.Gender.undisclosed);

        newUser.setProfile(newProfile);

        userService.saveUser(newUser);

        userService.createProfile(newProfile);

        return "注册成功";
    }

    @PostMapping("/getbio")
    public String getBio(@RequestParam Integer userid) {
        return userService.findBioById(userid);
    }

    @PostMapping("/setbio")
    public String setBio(@RequestBody Integer userid, String bio) {
        userService.updateBio(userid, bio);
        return "修改成功";
    }
    
    @PostMapping("/pullProfiles")
    public ProfileVO pullProfiles(@RequestBody Integer userid) {
        Profile profile = userService.findProfileById(userid);
        if (profile == null) {
            return null;
        }
        ProfileVO profileVO = new ProfileVO();
        profileVO.setId(profile.getId());
        profileVO.setBio(profile.getBio());
        profileVO.setAvatarUrl(profile.getAvatarUrl());
        profileVO.setBirthday(profile.getBirthday());
        profileVO.setGender(profile.getGender());
        return profileVO;
    }
    
}
