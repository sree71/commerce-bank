package seniorproject.commercebank2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Login getLogin(@PathVariable("id") Long id){
        Optional<Login> opt = loginRepository.findById(id);
        if(opt.isPresent()){
            Login login = opt.get();
            login.setPassword(JasyptE.decrypt(login.getPassword()));
            return login;
        }
        return null;
    }
}
