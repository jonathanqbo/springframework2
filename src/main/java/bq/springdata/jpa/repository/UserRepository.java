package bq.springdata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bq.springdata.jpa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	/** by Method name */
	public List<User> findByEmailAndLastName(String email, String lastName);
	
	/** by Query, same with above */
	@Query("SELECT u FROM User u WHERE u.email=?1 AND u.lastName=?2")
	public List<User> findByEmailAndLastNameWithQuery(String email, String lastName);
	
	/** ----- three ways to do JOIN ----- */
	
	/** by PATH Expression */
	@Query("SELECT u FROM User u WHERE u.lastName=?1 and u.homeAddress.city=?2")
	public List<User> findByLastNameAndCity1(String lastName, String city);
	
	/** by IN() */
	@Query("SELECT u FROM User u, IN (u.homeAddress) AS h WHERE u.lastName=?1 and h.city=?2")
	public List<User> findByLastNameAndCity2(String lastName, String city);
	
	/** by JOIN */
	@Query("SELECT u FROM User u JOIN u.homeAddress h WHERE u.lastName=?1 and h.city=?2")
	public List<User> findByLastNameAndCity3(String lastName, String city);
}
