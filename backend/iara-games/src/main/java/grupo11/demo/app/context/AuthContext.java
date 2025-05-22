package grupo11.demo.app.context;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Getter;
import lombok.Setter;

@RequestScope
@Component
public class AuthContext {

    @Setter
    @Getter
    private Claims jwtClaims;

    private long userId;

    public AuthContext() { }

    public long getUserId() {
        return userId == 0 ? (userId = Long.parseLong(jwtClaims.getSubject())) : userId;
    }
}
