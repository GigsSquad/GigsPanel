package it.coderunner.gigs.repository.users;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.repository.IRepository;

public interface IUserRepository extends IRepository<User, Long> {

	Users findAll();
}
