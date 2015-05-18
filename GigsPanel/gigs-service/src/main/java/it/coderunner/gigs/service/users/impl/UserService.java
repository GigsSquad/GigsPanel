package it.coderunner.gigs.service.users.impl;

import java.util.List;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.service.gigs.impl.GigService;
import it.coderunner.gigs.service.users.IUserService;
import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@Transactional(rollbackFor = Exception.class)
public class UserService implements IUserService {@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean saveIfNew(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> list(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User uniqueObject(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Autowired
	//private IUserR
}
