package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.model.User;
import com.machinarymgmt.service.dto.SignUpRequest;

public interface UserService {

    User signUp(SignUpRequest signUpRequest);

}
