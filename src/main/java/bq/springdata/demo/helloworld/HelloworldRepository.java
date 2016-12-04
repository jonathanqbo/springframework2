package bq.springdata.demo.helloworld;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloworldRepository extends CrudRepository<HelloworldEntity, Long>{

	List<HelloworldEntity> findByName(String name);
	
}
