package bq.springdata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bq.springdata.jpa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public List<User> findByEmailAndLastName(String email, String lastName);
	
	@Query("SELECT u FROM User u WHERE u.email=?1 AND u.lastName=?2")
	public List<User> findByEmailAndLastNameWithQuery(String email, String lastName);
	
	
}
