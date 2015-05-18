package it.coderunner.gigs.service.users;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.service.IService;

import java.util.List;

public interface IUserService extends IService{

	void delete(User user);
	
	void saveOrUpdate(User user);
	
	void save(User user);
	
	boolean saveIfNew(User user);

	List<User> list(User user);

	long count(User user);
	
	User uniqueObject(User user);
}
