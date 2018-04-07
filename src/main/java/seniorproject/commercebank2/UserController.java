package seniorproject.commercebank2;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import seniorproject.commercebank2.utils.CommerceBankUtils;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam(defaultValue = "testGroup") String group, @RequestParam(defaultValue = "testAccount") String account,
                                            @RequestParam(defaultValue = "testPassword") String password, @RequestParam(defaultValue = "testUser") String name){
        User user = new User();
        String salt = CommerceBankUtils.generateSalt();
        String hashPassword = CommerceBankUtils.generateHash(password, salt);
        user.updateUser(group, account, hashPassword, salt, name);
        userRepository.save(user);
        return "Saved";
    }

    @RequestMapping(path="/update")
    public @ResponseBody String updateUser (@RequestParam Long id, @RequestParam(defaultValue = "testGroup") String group, @RequestParam(defaultValue = "testAccount") String account,
                                            @RequestParam(defaultValue = "testPassword") String password, @RequestParam(defaultValue = "testUser") String name){
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            User user= opt.get();
            String salt = CommerceBankUtils.generateSalt();
            String hashPassword = CommerceBankUtils.generateHash(password, salt);
            user.updateUser(group, account, hashPassword, salt, name);
            userRepository.save(user);
            return "Updated";
        }
        return "Not Found";
    }

    @RequestMapping(path="/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteUser (@RequestParam Long id /*@RequestParam(defaultValue = "testGroup") String group, @RequestParam(defaultValue = "testAccount") String account,
                                            @RequestParam(defaultValue = "testPassword") String password, @RequestParam(defaultValue = "testSalt") String salt, @RequestParam(defaultValue = "testUser") String name*/){
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            User user = opt.get();
            userRepository.delete(user);
            return "Deleted";
        }
        return "Not Found";
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    /*
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("id") Long id){
        return userRepository.findOne(id);
    }*/
}