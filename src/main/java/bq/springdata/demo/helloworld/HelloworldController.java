package bq.springdata.demo.helloworld;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloworldController {

	@Autowired
	private HelloworldRepository repository;
	
	@RequestMapping(method=RequestMethod.POST, path="/helloworld")
	@ResponseBody
	public long create(@RequestBody HelloworldEntity entity) {
		HelloworldEntity result = repository.save(entity);
		return result.getId();
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/helloworld")
	@ResponseBody
	public boolean update(@RequestBody HelloworldEntity entity) {
		repository.save(entity);
		return true;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/helloworld/{id}")
	@ResponseBody
	public boolean delete(@PathVariable long id) {
		repository.delete(id);
		return true;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@ResponseBody
	public List<HelloworldEntity> find(@RequestParam String name) {
		List<HelloworldEntity> entity = repository.findByName(name);
		return entity;
	}
	
}
