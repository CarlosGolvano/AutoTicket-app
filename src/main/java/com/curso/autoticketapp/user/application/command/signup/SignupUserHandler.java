package com.curso.autoticketapp.user.application.command.signup;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import com.curso.autoticketapp.user.domain.entity.User;
import com.curso.autoticketapp.user.domain.entity.UserRole;
import com.curso.autoticketapp.user.domain.exception.UserAlreadyExistsException;
import com.curso.autoticketapp.user.domain.port.AuthenticationPort;
import com.curso.autoticketapp.user.domain.port.PasswordEncoderPort;
import com.curso.autoticketapp.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SignupUserHandler implements RequestHandler<SignupUserRequest, SignupUserResponse> {

    private final UserRepository userRepository;

    private final PasswordEncoderPort passwordEncoder;

    private final AuthenticationPort authentication;

    @Override
    public SignupUserResponse handle(SignupUserRequest request) {
        Optional<User> userExists = userRepository.findByEmail(request.getEmail());

        if (userExists.isPresent()) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(encodedPassword)
                .role(UserRole.CLIENT)
                .build();

        User userSaved = userRepository.upsert(user);
        String token = authentication.authenticate(request.getEmail(), request.getPassword());

        return new SignupUserResponse(userSaved.getId(), token);
    }

    @Override
    public Class<SignupUserRequest> getRequestType() {
        return SignupUserRequest.class;
    }
}
