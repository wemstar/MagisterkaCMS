package pl.edu.agh.fis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DiscoverServerApplication.class)
@WebAppConfiguration
public class DiscoverServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
