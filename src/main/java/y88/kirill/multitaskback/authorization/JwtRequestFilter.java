package y88.kirill.multitaskback.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import y88.kirill.multitaskback.dtos.UserDTO;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenGenerate jwtTokenGenerate;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");


        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken authenticationToken = createToken(authorizationHeader);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken createToken(String authorizationHeader ){
        String token = authorizationHeader.replace("Bearer ", "");
        UserDTO userDTO = jwtTokenGenerate.parseToken(token);
        System.out.println("роль из create token =" + userDTO.getUserRoleString());
        return new UsernamePasswordAuthenticationToken(userDTO, null, userDTO.getAuthorities() );
    }



}
