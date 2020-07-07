package ro.utcn.sd.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ro.utcn.sd.repository.AdminRepository;
import ro.utcn.sd.repository.UserRepository;

import java.util.Date;

@Component
public class HttpHeadersConfiguration {

    private HttpHeaders userHttpHeaders = new HttpHeaders();

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository customerRepository;

    public void generateToken(String role){
        String token = JWT.create()
                .withIssuer("auth0")
                .withSubject(role)
                .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000))
                .sign(Algorithm.HMAC256("RoleToken"));

        userHttpHeaders.set("Authentication", token);
    }
    
    public HttpHeaders getHttpHeaders() {
        return userHttpHeaders;
    }
}