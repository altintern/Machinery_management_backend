package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.data.model.User;
import com.machinarymgmt.service.dto.AuthResponse;
import com.machinarymgmt.service.dto.SignUpRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MachinaryMgmtMapper {

    User toUserEntity(SignUpRequest signUpRequest);

    AuthResponse toAuthResponse(BaseApiResponse baseApiResponse);
}
