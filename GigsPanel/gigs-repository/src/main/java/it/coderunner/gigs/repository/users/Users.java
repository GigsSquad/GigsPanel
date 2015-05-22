package it.coderunner.gigs.repository.users;

import it.coderunner.gigs.model.user.User;
import it.coderunner.gigs.model.user.UserStatus;
import it.coderunner.gigs.repository.OrderType;
import it.coderunner.gigs.repository.Queryable;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public abstract class Users extends Queryable<User, Long> {

	private static final long serialVersionUID = -5848249886489304600L;

	protected String firstName; // wyszukiwanie po imieniu i nazwisku
	protected String lastName;
	protected String location; // po lokalizacji
	protected String email;
	protected String username;
	protected String activationHash;
	protected UserStatus userStatus;
	protected Date dateRegister;// kiedy się zarejstrowali?

	protected Users() {
	};

	public Users withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Users withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Users withUsername(String username) {
		this.username = username;
		return this;
	}

	public Users withlocation(String location) {
		this.location = location;
		return this;
	}

	public Users withFirstName(Date dateRegister) {
		this.dateRegister = dateRegister;
		return this;
	}

	public Users withEmail(String email) {
		this.email = email;
		return this;
	}

	public Users withActivationHash(String activationHash) {
		this.activationHash = activationHash;
		return this;
	}

	public Users withStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
		return this;
	}

	public Users withId(Long id) {
		return (Users) super.withId(id);
	}

	public Users addOrder(OrderType orderType, String sortProperty) {
		return (Users) super.addOrder(orderType, sortProperty);
	}

	public Users loadWith(String... propertyNames) {
		return (Users) super.loadWith(propertyNames);
	}

	public Users paginate(int startingAt, int maxResults) {
		return (Users) super.paginate(startingAt, maxResults);
	}

	public Users randomOrder() {
		return (Users) super.randomOrder();
	}

	public Users merge(Users other) {
		super.merge(other);
		if (StringUtils.isNotBlank(other.firstName)) {
			this.firstName = other.firstName;
		}
		if (StringUtils.isNotBlank(other.lastName)) {
			this.lastName = other.lastName;
		}
		if (StringUtils.isNotBlank(other.location)) {
			this.location = other.location;
		}
		if (other.dateRegister != null) {
			this.dateRegister = other.dateRegister;
		}
		if (StringUtils.isNotBlank(other.email)) {
			this.email = other.email;
		}
		if (StringUtils.isNotBlank(other.username)) {
			this.username = other.username;
		}
		if (StringUtils.isNotBlank(other.activationHash)) {
			this.activationHash = other.activationHash;
		}
		if (userStatus != null) {
			this.userStatus = other.userStatus;
		}

		return this;
	}

	public static Users findAll() {
		return new Users() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public User uniqueObject() {
				return null;
			}

			@Override
			public List<User> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}
}
