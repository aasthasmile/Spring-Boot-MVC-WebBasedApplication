package csulb.edu.aasthajain.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

/**
 * All Spring beans are managed - they "live" inside a container, called "application context".
 * 
 * What is "living" in the application context? This means that the context instantiates the objects, 
 * not you. i.e. - you never make new UserServiceImpl() , the container finds each injection point
 *  and sets an instance there.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController  // Defines that this class is a spring bean
public class UserController  {

	
	@Autowired //Tells the application context to inject an instance of UserService here
	private UserServiceLayer userService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/users",method=RequestMethod.GET,produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	private List<User> getUsersInfo() throws Exception{
		return (List<User>) userService.performService("getAll");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/users/",method=RequestMethod.POST,produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) throws Exception{
		return ((ResponseEntity<User>)userService.performService("insert",user));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/users/{id}",method=RequestMethod.PUT,produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id,@RequestBody User user) throws Exception{
		return ((ResponseEntity<User>)userService.performService("update",user));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/users/{id}",method=RequestMethod.DELETE,produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) throws Exception{
		return ((ResponseEntity<User>)userService.performService("delete"));
	}
}

	
