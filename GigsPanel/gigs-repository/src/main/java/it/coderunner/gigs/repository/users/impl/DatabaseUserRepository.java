package it.coderunner.gigs.repository.users.impl;

import it.coderunner.gigs.model.BaseEntity;
import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.StandardDatabaseRepository;
import it.coderunner.gigs.repository.users.IUserRepository;
import it.coderunner.gigs.repository.users.Users;

import org.springframework.stereotype.Repository;

@Repository
public class DatabaseUserRepository extends StandardDatabaseRepository<User, Long> implements IUserRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522593563237649316L;

	@Override
	public Users findAll() {
		return new CriteriaUsers(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return User.class;
	}
}
