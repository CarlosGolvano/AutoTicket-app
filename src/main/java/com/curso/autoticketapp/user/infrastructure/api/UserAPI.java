package com.curso.autoticketapp.user.infrastructure.api;

import com.curso.autoticketapp.user.infrastructure.api.dto.LoginUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SignupUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SignupUserResponseDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.TokenResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserAPI {

    ResponseEntity<TokenResponseDTO> userLogin(LoginUserRequestDTO loginUserRequestDTO);

    ResponseEntity<SignupUserResponseDTO> userSignup(SignupUserRequestDTO signupUserRequestDTO);

}
