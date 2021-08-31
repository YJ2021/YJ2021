package com.insurance.service.user;


import com.insurance.pojo.Forget;
import com.insurance.pojo.Insurance_User;

public interface User_RegisterService {
    Integer insertUser(Insurance_User user);
    Insurance_User searchUser(String userCode);
    Integer updateActivated(String userCode);
    Insurance_User login(Insurance_User user);
    Integer initializePassword(Forget user);
    Integer updateUser(Insurance_User user);
}
