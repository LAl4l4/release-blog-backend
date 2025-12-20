package ReleaseBack.Back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ReleaseBack.Back.mapper.UserMapper;
import ReleaseBack.Back.entity.User;
import ReleaseBack.Back.entity.Profile;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public User findByNameEmail(String username) {
        if (username.contains("@")){
            return userMapper.findByEmail(username);
        }else{
            return userMapper.findByUsername(username);
        }
    }


    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    public void createProfile(Profile profile) {
        userMapper.createProfile(profile);
    }

    public Profile findProfileById(Integer userid) {
        return userMapper.findProfileById(userid);
    }

    public String findBioById(Integer userid) {
        return userMapper.findProfileById(userid).getBio();
    }

    public void updateBio(Integer userid, String bio) {
        userMapper.updateProfileBio(userid, bio);
    }

}
