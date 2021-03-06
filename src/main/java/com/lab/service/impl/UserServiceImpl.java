package com.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.lab.dao.AnnotationUserMapper;
import com.lab.dao.UserMapper;
import com.lab.model.User;
import com.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by elotoma on 2017/12/11.
 */

@Service(value="userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AnnotationUserMapper annotationUserMapper;


    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> selectByUsername(User user) {
        return userMapper.selectByUsername(user.getUsername());
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }




    @Override
    public List<User> findAllUserByAnnotation(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return annotationUserMapper.selectAllUser();
    }
}
