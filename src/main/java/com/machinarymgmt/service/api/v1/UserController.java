package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.mapper.MachinaryMgmtMapper;
import com.machinarymgmt.service.api.UserMgmtV1Api;
import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.service.UserService;
import com.machinarymgmt.service.api.utils.Constants;
import com.machinarymgmt.service.dto.AuthResponse;
import com.machinarymgmt.service.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//Always implement the OpenApi auto generated controller inteface in your custom controller class

@RestController
@RequiredArgsConstructor
public class UserController implements UserMgmtV1Api {
    private final UserService userService;
    private final MachinaryMgmtMapper mapper;
    private final ApiResponseBuilder builder;

    @Override
    public ResponseEntity<AuthResponse> signUp(SignUpRequest signUpRequest) throws Exception {
        AuthResponse response = mapper.toAuthResponse(builder.buildSuccessApiResponse(Constants.SIGN_UP_SUCCESS_MSG));
        userService.signUp(signUpRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        //Todo: implement your actual logic
    }
}
