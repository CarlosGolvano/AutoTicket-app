package com.curso.autoticketapp.user.infrastructure.api;

import com.curso.autoticketapp.user.infrastructure.api.dto.LoginUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SigninUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SigninUserResponseDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.TokenResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserAPI {

    ResponseEntity<TokenResponseDTO> userLogin(LoginUserRequestDTO loginUserRequestDTO);

    ResponseEntity<SigninUserResponseDTO> userSignin(SigninUserRequestDTO signinUserRequestDTO);

}
