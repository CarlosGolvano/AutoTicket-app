package com.curso.autoticketapp.user.application.command.signin;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import com.curso.autoticketapp.user.domain.entity.User;
import com.curso.autoticketapp.user.domain.entity.UserRole;
import com.curso.autoticketapp.user.domain.exception.UserAlreadyExistsException;
import com.curso.autoticketapp.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SigninUserHandler implements RequestHandler<SigninUserRequest, SigninUserResponse> {

    private final UserRepository userRepository;

    @Override
    public SigninUserResponse handle(SigninUserRequest request) {
        Optional<User> userExists = userRepository.findByEmail(request.getEmail());

        if (userExists.isPresent()) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        String encodedPassword = request.getPassword();

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(encodedPassword)
                .role(UserRole.CLIENT)
                .build();

        User userSaved = userRepository.upsert(user);

        SigninUserResponse response = new SigninUserResponse();
        response.setToken("OKEY");
        response.setId(userSaved.getId());

        return response;
    }

    @Override
    public Class<SigninUserRequest> getRequestType() {
        return SigninUserRequest.class;
    }
}
