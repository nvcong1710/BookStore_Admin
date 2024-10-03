package vn.edu.uit.IE104.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.edu.uit.IE104.dto.JwtResponse;
import vn.edu.uit.IE104.dto.LoginRequest;
import vn.edu.uit.IE104.entity.EmailDetails;
import vn.edu.uit.IE104.entity.User;
import vn.edu.uit.IE104.repository.UserRepository;
import vn.edu.uit.IE104.security.CustomUserDetails;
import vn.edu.uit.IE104.security.JwtUtils;
import vn.edu.uit.IE104.services.EmailService;
import vn.edu.uit.IE104.services.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final Environment environment;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            User resUser = userService.registerUser(user);
            String verifyLink =  environment.getProperty("app.fe_domain", "http://localhost:3000") + "/auth/verify_email/" + resUser.getId();
            String htmlContent = emailService.loadEmailTemplate();
            htmlContent = htmlContent.replace("${username}", resUser.getUsername());
            htmlContent = htmlContent.replace("${email}", resUser.getEmail());
            htmlContent = htmlContent.replace("${verificationLink}", verifyLink);
            EmailDetails emailDetails = new EmailDetails(resUser.getEmail(), htmlContent, "Verify email", "");
            emailService.sendVerifyEmail(emailDetails);
            return ResponseEntity.ok("Registration successful!");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(request.getEmail()));
        if (optionalUser.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Người dùng không tồn tại.");
        }
        User user = optionalUser.get();
        if (user.getVerifyEmailStatus() != null && !user.getVerifyEmailStatus()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Tài khoản của bạn chưa được xác minh.");
        }
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                jwt,
                roles));
    }

    @GetMapping("/verify_email/{uid}")
    public String verifyEmail(@PathVariable("uid") Long user_id){
        userService.verifyUser(user_id);
        String homeLink =  environment.getProperty("app.fe_domain", "http://localhost:3001");
        return "<h1>Xác thực thành công!</h1>\n" +
                "<p>Bạn đã xác thực email thành công, hãy quay lại <a href=\""+ homeLink + "\">Trang chủ</a>.</p>";
    }
}