package csulb.edu.aasthajain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import csulb.edu.aasthajain.model.User;

@Service("userService")
public class UserServiceImpl implements UserServiceLayer {

	@Autowired
	private UserDAL userDal;

	
	public Object performService(Object... args) throws Exception {
		Assert.notNull(args[0]);
		if (args[0].equals("insertBatch")) {
			userDal.insertBatch();
			return true;
		}
		else if (args[0].equals("insert")) {
			return userDal.CreateUser((User)args[1]);
		}
		else if (args[0].equals("update")) {
			return userDal.updateUser((User)args[1]);
		}
		else if (args[0].equals("delete")) {
			return userDal.deleteUser((String)args[1]);
		}
		else if (args[0].equals("getAll")) {
			return userDal.getAllUser();
		}
		return null;
	}


	}
