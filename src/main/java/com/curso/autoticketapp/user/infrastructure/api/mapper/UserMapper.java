package com.curso.autoticketapp.user.infrastructure.api.mapper;

import com.curso.autoticketapp.user.application.command.login.LoginUserRequest;
import com.curso.autoticketapp.user.application.command.login.LoginUserResponse;
import com.curso.autoticketapp.user.application.command.signup.SignupUserRequest;
import com.curso.autoticketapp.user.application.command.signup.SignupUserResponse;
import com.curso.autoticketapp.user.infrastructure.api.dto.LoginUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SignupUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SignupUserResponseDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.TokenResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    LoginUserRequest mapToLoginUserRequest(LoginUserRequestDTO loginUserRequestDTO);

    TokenResponseDTO mapToTokenResponseDTO(LoginUserResponse loginUserResponse);

    SignupUserRequest mapToSigninUserRequest(SignupUserRequestDTO signupUserRequestDTO);

    SignupUserResponseDTO mapToSigninUserResponse(SignupUserResponse signupUserResponse);

}
