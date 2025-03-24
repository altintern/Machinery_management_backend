package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.mapper.MachinaryMgmtMapper;
import com.machinarymgmt.service.api.data.UserRepository;
import com.machinarymgmt.service.api.data.model.User;
import com.machinarymgmt.service.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MachinaryMgmtMapper mapper;

    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
//        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(signUpRequest.getPassword());
//        user.setFirstName(signUpRequest.getFirstName());
//        user.setLastName(signUpRequest.getLastName());
          // use mapper to do the same work
        try {
           user =  mapper.toUserEntity(signUpRequest);
            return userRepository.save(user);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to sign up", e);
        }

    }
}
