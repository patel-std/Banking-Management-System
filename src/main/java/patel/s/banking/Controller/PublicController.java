package patel.s.banking.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import patel.s.banking.DTO.Admin;
import patel.s.banking.JwtUtilities.JwtUtility;
import patel.s.banking.Services.MyUserDetailsService;

@Validated
@RestController 
@RequestMapping("/public")
public class PublicController {

    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    PublicController(JwtUtility jwtUtility,MyUserDetailsService myUserDetailsService,AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
        this.jwtUtility=jwtUtility;
        this.myUserDetailsService=myUserDetailsService;
    }
    @GetMapping
    public String pass(){
        PasswordEncoder en=new BCryptPasswordEncoder();
        return en.encode("Abhi@123");
    }
   @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody Admin admin){
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword())
       );
       UserDetails details = myUserDetailsService.loadUserByUsername(admin.getUsername());
       String jwt = jwtUtility.generateToken(details.getUsername());
       return new ResponseEntity<>(jwt, HttpStatus.OK);
   }
}
