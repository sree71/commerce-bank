package seniorproject.commercebank2;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import seniorproject.commercebank2.utils.CommerceBankUtils;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="/add", method = RequestMethod.POST)
    public @ResponseBody String addNewUser (@RequestParam(defaultValue = "testGroup") String group, @RequestParam(defaultValue = "testAccount") String account,
                                            @RequestParam(defaultValue = "testPassword") String password, @RequestParam(defaultValue = "testUser") String name){
        User user = new User();
        String hashPassword = JasyptE.encrypt(password);
        user.updateUser(group, account, hashPassword, "", name);
        userRepository.save(user);
        return "Saved";
    }

    @RequestMapping(path="/update", method = RequestMethod.POST)
    public @ResponseBody String updateUser (@RequestParam Long id, @RequestParam(defaultValue = "testGroup") String group, @RequestParam(defaultValue = "testAccount") String account,
                                            @RequestParam(defaultValue = "testPassword") String password, @RequestParam(defaultValue = "testUser") String name){
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            User user= opt.get();
            String hashPassword = JasyptE.encrypt(password);
            user.updateUser(group, account, hashPassword, "", name);
            userRepository.save(user);
            return "Updated";
        }
        return "Not Found";
    }

    @RequestMapping(path="/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteUser (@RequestParam Long id){
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            User user = opt.get();
            userRepository.delete(user);
            return "Deleted";
        }
        return "Not Found";
    }


    @RequestMapping(path="/all", method = RequestMethod.POST)
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("id") Long id){
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            User user = opt.get();
            user.setPassword(JasyptE.decrypt(user.getPassword()));
            return user;
        }
        return null;
    }
}