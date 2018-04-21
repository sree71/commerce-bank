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
        Optional<Login> opt = loginRepository.findById(Long.valueOf(username));
        if(opt.isPresent()) {
            Login login = opt.get();
            login.setPassword(JasyptE.decrypt(login.getPassword()));
            List<GrantedAuthority> auth = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_ADMIN");
            String password = login.getPassword();
            return new org.springframework.security.core.userdetails.User(login.getAccountName(), password,
                    auth);
        }
        return null;
    }

//    public UserDetails loadUserById(Long id){
//        Optional<Login> opt = loginRepository.findById(id);
//        if(opt.isPresent()) {
//            Login login = opt.get();
//            login.setPassword(JasyptE.decrypt(login.getPassword()));
//            List<GrantedAuthority> auth = AuthorityUtils
//                    .commaSeparatedStringToAuthorityList("ROLE_ADMIN");
//            String password = login.getPassword();
//            return new org.springframework.security.core.userdetails.User(login.getAccountName(), password,
//                    auth);
//        }
//        return null;
//    }


}