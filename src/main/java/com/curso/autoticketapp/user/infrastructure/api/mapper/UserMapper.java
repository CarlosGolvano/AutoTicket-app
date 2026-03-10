package com.curso.autoticketapp.user.infrastructure.api.mapper;

import com.curso.autoticketapp.user.application.command.login.LoginUserRequest;
import com.curso.autoticketapp.user.application.command.login.LoginUserResponse;
import com.curso.autoticketapp.user.application.command.signin.SigninUserRequest;
import com.curso.autoticketapp.user.application.command.signin.SigninUserResponse;
import com.curso.autoticketapp.user.infrastructure.api.dto.LoginUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SigninUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SigninUserResponseDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.TokenResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    LoginUserRequest mapToLoginUserRequest(LoginUserRequestDTO loginUserRequestDTO);

    TokenResponseDTO mapToTokenResponseDTO(LoginUserResponse loginUserResponse);

    SigninUserRequest mapToSigninUserRequest(SigninUserRequestDTO signinUserRequestDTO);

    SigninUserResponseDTO mapToSigninUserResponse(SigninUserResponse signinUserResponse);

}
