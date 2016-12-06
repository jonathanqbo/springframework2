package bq.springdata.jpa.configuration;

import java.util.Date;
import java.util.LinkedList;
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
import org.springframework.transaction.annotation.Transactional;

import bq.springdata.jpa.configuration.JPAConfiguration;
import bq.springdata.jpa.entity.Address;
import bq.springdata.jpa.entity.User;
import bq.springdata.jpa.repository.AddressRepository;
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
	
	@Autowired
	private AddressRepository addressRepository;
	
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
			
			Address homeAddress = new Address();
			homeAddress.setDetail("street" + i);
			homeAddress.setCity("city" + i);
			homeAddress.setCountry("country" + i);
			homeAddress.setPostcode("postcode" + i);
			
			// don't need do persist manually by setting entity Cascade Type to PERSIST
			// addressRepository.save(homeAddress);
			user.setHomeAddress(homeAddress);

			List<Address> historyAddresses = new LinkedList<>();
			for(int j = 0; j < 5; j++) {
				Address historyAddress = new Address();
				historyAddress.setDetail("street" + i + j);
				historyAddress.setCity("city" + i + j);
				historyAddress.setCountry("country" + i + j);
				historyAddress.setPostcode("postcode" + i + j);
				// don't need do persist manually by setting entity Cascade Type to PERSIST
				//addressRepository.save(historyAddress);
				
				historyAddresses.add(historyAddress);
			}
			
			user.setHistoryAddress(historyAddresses);
			
			repository.save(user);
		}
	}
	
	@After
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	// to enable lazy load after repository find method, need keep the whole method in a same session
	@Transactional
	public void testFindbyMethodName() {
		List<User> result = repository.findByEmailAndLastName("email88", "lastname88");
		result.forEach(user -> System.out.println(user));
	}
	
	@Test
	// to enable lazy load after repository find method, need keep the whole method in a same session
	@Transactional
	public void testFindbyQuery() {
		List<User> result = repository.findByEmailAndLastNameWithQuery("email99", "lastname99");
		result.forEach(user -> System.out.println(user));
	}
	
	@Test
	// to enable lazy load after repository find method, need keep the whole method in a same session
	@Transactional
	public void testFindbyPathExpression() {
		List<User> result = repository.findByLastNameAndCity1("lastname88", "city88");
		result.forEach(user -> System.out.println(user));
	}
	
	@Test
	@Transactional
	public void testFindbyIN() {
		List<User> result = repository.findByLastNameAndCity2("lastname88", "city88");
		result.forEach(user -> System.out.println(user));
	}
	
	@Test
	@Transactional
	public void testFindbyJoin() {
		List<User> result = repository.findByLastNameAndCity3("lastname88", "city88");
		result.forEach(user -> System.out.println(user));
	}
	
}
