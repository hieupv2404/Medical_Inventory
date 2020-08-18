package com.java1906.climan.services;

import com.java1906.climan.data.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface IUserInfoService {
    public Optional<UserInfo> get(Integer id);

    public List<UserInfo> getAll();

    public UserInfo save(UserInfo userInfo);

    public UserInfo update(UserInfo userInfo, Integer id) throws Exception;

    public void delete(Integer id);
}
