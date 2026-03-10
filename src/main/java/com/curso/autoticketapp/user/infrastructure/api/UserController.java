package com.curso.autoticketapp.user.infrastructure.api;

import com.curso.autoticketapp.common.application.mediator.Mediator;
import com.curso.autoticketapp.user.application.command.login.LoginUserRequest;
import com.curso.autoticketapp.user.application.command.login.LoginUserResponse;
import com.curso.autoticketapp.user.application.command.signin.SigninUserRequest;
import com.curso.autoticketapp.user.application.command.signin.SigninUserResponse;
import com.curso.autoticketapp.user.infrastructure.api.dto.LoginUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SigninUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SigninUserResponseDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.TokenResponseDTO;
import com.curso.autoticketapp.user.infrastructure.api.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(UserController.BASE_URL)
@Tag(name = "User", description = "User API operations")
@RequiredArgsConstructor
public class UserController implements UserAPI{

    public static final String BASE_URL = "/auth";
    private final Mediator mediator;
    private final UserMapper userMapper;

    @Override
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> userLogin(@RequestBody LoginUserRequestDTO loginUserRequestDTO) {
        LoginUserRequest request = userMapper.mapToLoginUserRequest(loginUserRequestDTO);

        LoginUserResponse response = mediator.dispatch(request);

        return ResponseEntity.ok(userMapper.mapToTokenResponseDTO(response));
    }

    @Override
    @PostMapping("/signin")
    public ResponseEntity<SigninUserResponseDTO> userSignin(@RequestBody SigninUserRequestDTO signinUserRequestDTO) {
        SigninUserRequest request = userMapper.mapToSigninUserRequest(signinUserRequestDTO);

        SigninUserResponse response = mediator.dispatch(request);

        return ResponseEntity
                .created(URI.create(UserController.BASE_URL + "/" + response.getId().toString()))
                .body(userMapper.mapToSigninUserResponse(response));
    }
}
