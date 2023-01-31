package com.zc.managementsystem.user.service;

import com.zc.managementsystem.user.dao.UserDao;
import com.zc.managementsystem.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByUserName(String userName) {
        return this.userDao.getUserByUserName(userName);
    }

}
