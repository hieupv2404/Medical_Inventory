package com.java1906.climan.services.impl;

import com.java1906.climan.data.model.UserInfo;
import com.java1906.climan.data.repo.UserInfoRepository;
import com.java1906.climan.exception.LogicException;
import com.java1906.climan.services.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Optional<UserInfo> get(Integer id) {
        return userInfoRepository.findById(id);
    }

    @Override
    public List<UserInfo> getAll() {
        return (List<UserInfo>) userInfoRepository.findAll();
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo update(UserInfo userInfo, Integer id) throws Exception {
        Optional<UserInfo> optionalUpdatingUserInfo = userInfoRepository.findById(id);
        if (!optionalUpdatingUserInfo.isPresent()) {
            throw new LogicException("UserInfo khong ton tai", HttpStatus.NOT_FOUND);
        }
        UserInfo updatingUserInfo = optionalUpdatingUserInfo.get();
        if (null != userInfo.getName())
            updatingUserInfo.setName(userInfo.getName());
        if (null != userInfo.getPhone())
            updatingUserInfo.setPhone(userInfo.getPhone());
        if (null != userInfo.getEmail())
            updatingUserInfo.setEmail(userInfo.getEmail());

        return userInfoRepository.save(updatingUserInfo);
    }

    @Override
    public void delete(Integer id) {
        userInfoRepository.deleteById(id);
    }
}
