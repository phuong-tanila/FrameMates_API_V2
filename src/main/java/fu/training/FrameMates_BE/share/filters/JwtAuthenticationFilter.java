package fu.training.FrameMates_BE.share.filters;

import fu.training.FrameMates_BE.account.AccountService;
import fu.training.FrameMates_BE.account.JwtService;
import fu.training.FrameMates_BE.account.TokenType;
import fu.training.FrameMates_BE.share.exceptions.ExceptionResponse;
import fu.training.FrameMates_BE.share.exceptions.MissingBearerTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtService jwtService;

    private AccountService accountService;

    public JwtAuthenticationFilter(JwtService jwtService, AccountService accountService) {
        this.jwtService = jwtService;
        this.accountService = accountService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.error(jwtService.toString());
//        log.error(accountService.toString());
            // Lấy jwt từ request
            if(
//                    isSecured(request)
                    true
            ){
                String jwt = getJwtFromRequest(request);

                if (StringUtils.hasText(jwt))  {
                    Claims claims = jwtService.getClaimsFromToken(TokenType.ACCESSTOKEN, jwt);
                    String userEmail = claims.getSubject();
                    UserDetails userDetails = accountService.loadUserByUsername(userEmail);
                    System.out.println(userDetails);
                    if(userDetails != null) {
                        UsernamePasswordAuthenticationToken
                                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        filterChain.doFilter(request, response);


    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
//        System.out.println(bearerToken);
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    private boolean isSecured(HttpServletRequest request) {
        HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        if(handlerMethod != null) {
            Secured securedAnnotation = handlerMethod.getMethodAnnotation(Secured.class);
            return securedAnnotation != null;
        }
        return false;
    }
}
