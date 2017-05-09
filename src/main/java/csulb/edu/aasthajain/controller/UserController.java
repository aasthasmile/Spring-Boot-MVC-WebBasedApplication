package csulb.edu.aasthajain.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * All Spring beans are managed - they "live" inside a container, called "application context".
 * 
 * What is "living" in the application context? This means that the context instantiates the objects, 
 * not you. i.e. - you never make new UserServiceImpl() , the container finds each injection point
 *  and sets an instance there.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import csulb.edu.aasthajain.model.ResponseObject;
import csulb.edu.aasthajain.model.User;

@RestController // Defines that this class is a spring bean
public class UserController {

	@Autowired // Tells the application context to inject an instance of
	private UserServiceLayer userService;

	@RequestMapping(value="/",method = RequestMethod.GET)
	 public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("app/index.html");
     }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "users", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsersInfo() throws Exception {
		return (List<User>) userService.performService("getAll");
	}

	
	@RequestMapping(value = "/users/", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject createUser(@RequestBody User user) throws Exception {
		return getResponseObject((Integer)userService.performService("insert", user));
	}

	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject updateUser(@PathVariable("id") long id, @RequestBody User user) throws Exception {
		return getResponseObject((Integer) userService.performService("update", user));
	}

	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject deleteUser(@PathVariable("id") long id) throws Exception {
		return getResponseObject((Integer) userService.performService("delete",id));
	}

	private ResponseObject getResponseObject(Integer output) {
		return new ResponseObject(output == 1 ? true : false, output == 1 ? "successful" : "failed");
	}
}
