package grupo11.demo.app.api.auth;

import grupo11.demo.domain.auth.AuthService;
import grupo11.demo.domain.auth.LoginInput;
import grupo11.demo.domain.auth.LoginOutput;
import grupo11.demo.domain.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginOutput login(@RequestBody LoginInput loginInput) {
        return authService.login(loginInput);
    }
}
