package bq.springdata.jpa.configuration;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import bq.springdata.jpa.configuration.JPAConfiguration;
import bq.springdata.jpa.entity.User;
import bq.springdata.jpa.repository.UserRepository;

/**
 * To run this unit test properly,  
 * the package name of unit test class need to be same with the package name where the @SpringBootApplication sits
 * 
 * @author qibo
 *
 */


@RunWith(SpringRunner.class)

// Using @DataJpaTest to use in-memory database to do unit test
//@DataJpaTest

// Using @SpringBootTest to use configured database, ie mysql here, to do unit test
@SpringBootTest

public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@Before
	public void setUp() {
		for(int i = 0; i < 100; i++) {
			User user = new User();
			user.setBirthday(new Date());
			user.setCellPhone("cellphone" + i);
			user.setEmail("email" + i);
			user.setFirstName("firstname" + i);
			user.setHomePhone("homephone" + i);
			user.setLastName("lastname" + i);
			user.setPassword("password" + i);
			user.setUserName("username" + i);
			
			repository.save(user);
		}
	}
	
	@After
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	public void testFindbyMethodName() {
		List<User> result = repository.findByEmailAndLastName("email88", "lastname88");
		result.forEach(user -> System.out.println("{" + user.getId() + "," + user.getEmail() + "," + user.getLastName() + "}"));
	}
	
	@Test
	public void testFindbyQuery() {
		List<User> result = repository.findByEmailAndLastNameWithQuery("email99", "lastname99");
		result.forEach(user -> System.out.println("{" + user.getId() + "," + user.getEmail() + "," + user.getLastName() + "}"));
	}
	
}
