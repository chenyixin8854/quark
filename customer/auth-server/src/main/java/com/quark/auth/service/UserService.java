package com.quark.auth.service;

import com.quark.auth.dao.UserMapper;
import com.quark.auth.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Optional<User> getUserByMobile(String mobile) {
        //TODO
        return null;
    }

    public Optional<User> getUserById(Integer id) {
        return Optional.ofNullable(userMapper.selectByPrimaryKey(id));
    }


    public List<String> getUserRoleNameList(Integer userId) {
        //TODO
        return null;
    }
}
