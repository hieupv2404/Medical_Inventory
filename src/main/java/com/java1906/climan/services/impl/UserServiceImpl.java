package com.java1906.climan.services.impl;

import com.java1906.climan.data.model.User;
import com.java1906.climan.data.repo.UserRepository;
import com.java1906.climan.exception.LogicException;
import com.java1906.climan.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user, Integer id) throws Exception {
        Optional<User> optionalUpdatingUser = userRepository.findById(id);
        if (!optionalUpdatingUser.isPresent()) {
            throw new LogicException("User không tồn tại", HttpStatus.NOT_FOUND);
        }
        User updatingUser = optionalUpdatingUser.get();
        if (null != updatingUser.getUsername()) {
            updatingUser.setUsername(user.getUsername());
        }
        if (null != updatingUser.getPassword()) {
            updatingUser.setPassword(user.getPassword());
        }
        if (null != updatingUser.getRole()) {
            updatingUser.setRole(user.getRole());
        }
        if (null != updatingUser.getActiveFlag()) {
            updatingUser.setActiveFlag(user.getActiveFlag());
        }
        return userRepository.save(updatingUser);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
