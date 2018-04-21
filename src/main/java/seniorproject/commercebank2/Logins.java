package seniorproject.commercebank2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class Logins implements UserDetailsService{

    private LoginRepository loginRepository;

    @Autowired
    public Logins(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Login login=loginRepository.findByName(username);
        if (login == null){
            return null;
        }

        List<GrantedAuthority> auth = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String password = login.getPassword();
        return new org.springframework.security.core.userdetails.User(username, password,
                auth);

    }


}