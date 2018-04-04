package seniorproject.commercebank2;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import seniorproject.commercebank2.utils.CommerceBankUtils;

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
        User user = userRepository.findOne(id);
        String salt = CommerceBankUtils.generateSalt();
        String hashPassword = CommerceBankUtils.generateHash(password, salt);
        user.updateUser(group, account, hashPassword, salt, name);
        userRepository.save(user);
        return "Updated";
    }

    @RequestMapping(path="/delete/{id}",method=RequestMethod.POST)
    public @ResponseBody String deleteUser (@PathVariable Long id){
        userRepository.delete(id);
        return "Deleted";
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long id){
        User user =  userRepository.findOne(id);
        return user;
    }
}
