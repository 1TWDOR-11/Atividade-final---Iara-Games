package grupo11.demo.app.interceptor;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import grupo11.demo.app.context.AuthContext;
import grupo11.demo.infra.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final AuthContext authContext;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil, AuthContext authContext) {
        this.jwtUtil = jwtUtil;
        this.authContext = authContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var tokenClaims = jwtUtil.parseToken(authorizationHeader.substring(7));
            authContext.setJwtClaims(tokenClaims);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(authContext.getUserId(), null, List.of())
            );
        }

        chain.doFilter(request, response);
    }
}
