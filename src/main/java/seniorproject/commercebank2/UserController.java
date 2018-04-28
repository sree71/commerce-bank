package seniorproject.commercebank2;


import org.jasypt.encryption.pbe.PBEByteEncryptor;
import org.springframework.context.ApplicationContext;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import seniorproject.commercebank2.utils.CommerceBankUtils;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.Optional;

//import static seniorproject.commercebank2.JasyptE.encrypt;

@RestController
@RequestMapping("/user")
public class UserController {

    ApplicationContext context = new FileSystemXmlApplicationContext(".idea/jasypt.xml");
    StandardPBEStringEncryptor PBEencryptor = (StandardPBEStringEncryptor)context.getBean("strongEncryptor");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;


    @RequestMapping(path="/add", method = RequestMethod.POST)
    public @ResponseBody String addNewUser (@RequestParam(defaultValue = "testGroup") String group, @RequestParam(defaultValue = "testAccount") String account,
                                            @RequestParam(defaultValue = "testPassword") String password, @RequestParam(defaultValue = "testUser") String name){
        User user = new User();
//        String hashPassword = JasyptE.encrypt(password);
        user.updateUser(group, account, /*hashPassword*/ password, "", name);
        userRepository.save(user);
        return "Saved";
    }

    @RequestMapping(path="/update", method = RequestMethod.POST)
    public @ResponseBody String updateUser (@RequestParam Long id, @RequestParam String group, @RequestParam String account,
                                            @RequestParam String newPassword, @RequestParam(defaultValue = "testUser") String name){
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()) {
            User user= opt.get();
           String hashPassword = PBEencryptor.encrypt(newPassword);
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

    @RequestMapping(path="/display", method = RequestMethod.POST)
    public @ResponseBody String displayPass(@RequestParam Long id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            String password = user.getPassword();
//           String decPass = JasyptE.decrypt(password);
//            String decPass =

            return (PBEencryptor.decrypt(password));
        }
        return "Error";
    }




    @RequestMapping(path="/all", method = RequestMethod.POST)
    public @ResponseBody Iterable<User> getAllUsers(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Login login = loginRepository.findByName(principal.getName());
        String group = login.getGroupName();
        return userRepository.findByGroup(group);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("id") Long id){
        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            User user = opt.get();
//            user.setPassword(JasyptE.decrypt(user.getPassword()));
            return user;
        }
        return null;
    }
}

