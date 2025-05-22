package grupo11.demo.domain.auth;

import grupo11.demo.domain.exception.NotFoundException;
import grupo11.demo.domain.exception.UnauthorizedException;
import grupo11.demo.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginOutput login(LoginInput loginInput) {
        var user = userRepository.findByEmail(loginInput.getEmail()).orElseThrow(NotFoundException::new);

        if (!passwordEncoder.matches(loginInput.getPassword(), user.getPassword()))
            throw new UnauthorizedException();

        var loginOutput = new LoginOutput();
        loginOutput.setUserId(user.getId());
        loginOutput.setName(user.getName());
        loginOutput.setToken("dfd=aSWAR345d==");

        return loginOutput;
    }
}
