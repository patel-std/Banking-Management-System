package patel.s.banking.Services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import patel.s.banking.Entity.Credential;
import patel.s.banking.Repository.CredentialRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final CredentialRepo credentialRepo;
    public MyUserDetailsService(CredentialRepo credentialRepo){
        this.credentialRepo=credentialRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        Credential ex=credentialRepo.findByUsername(username);
        if(ex!=null){
            return User.builder()
                    .username(ex.getUsername())
                    .password(ex.getPassword())
                    .authorities(ex.getRole())
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
