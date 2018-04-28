package seniorproject.commercebank2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.security.Principal;
import java.util.Random;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	@InjectMocks
	@Autowired
    private UserController userController;

    private MockMvc mockMvc;
    
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
//    @Test
//    public void test1_getUsers() {
//    	try {
//    		MvcResult result = (MvcResult) mockMvc.perform(post("/user/all")).andReturn();
//    		String content = result.getResponse().getContentAsString();
//    		System.out.println(content);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }

    @Test
    public void test2_addUsers() throws Exception{
    		MvcResult result = (MvcResult) mockMvc.perform(post("/user/add")
    				.param("group", "Admin Group")
    				.param("account", "TestAdmin")
    				.param("password", String.valueOf(new Random().nextInt(100000)))
    				.param("name", "Admin")
    				)
    				.andReturn();
    		String content = result.getResponse().getContentAsString();
    		assertEquals("Saved", content);
    }

    @Test
    public void test3_getUser() throws Exception {
		MvcResult result = (MvcResult) mockMvc.perform(post("/user/22"))
				.andReturn();
		String content = result.getResponse().getContentAsString();
		User user = new ObjectMapper().readValue(content, User.class);
		assertTrue(null != user);
    }

    @Test
    public void test4_updateUsers() throws Exception{
    		MvcResult result = (MvcResult) mockMvc.perform(post("/user/22"))
    				.andReturn();
    		String content = result.getResponse().getContentAsString();
    		User user = new ObjectMapper().readValue(content, User.class);
    		
    		MvcResult updateresult = (MvcResult) mockMvc.perform(post("/user/update")
    				.param("id", String.valueOf(user.getId()))
    				.param("group", user.getGroupName())
    				.param("account", user.getAccountName())
    				.param("password", user.getPassword())
    				.param("name", user.getUserName()+"update")
    				)
    				.andReturn();
    		String updatecontent = updateresult.getResponse().getContentAsString();
    		assertEquals("Updated", updatecontent);
    }

    @Test
    public void test5_deleteUsers() throws Exception{
    	MvcResult result = (MvcResult) mockMvc.perform(post("/user/delete")
    			.param("id", "7")
    			)
    			.andReturn();
    	String content = result.getResponse().getContentAsString();
    	assertEquals("Deleted", content);
    }
}
