package seniorproject.commercebank2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommerceBank2ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCase1(){
        int i = 1+1;
        assert(i == 2);
    }

    @Test
    public void testCase2(){
        int i = 1+1;
        assert(i == 2);
    }
}