package application;

import java.util.Collection;

public class UserService {
	private Database database;

	public UserService(Database database) {
		this.database = database;

	}

	public void createUser(String name, String dateOfBirth, String gender, String email, String password) {
		User user = new User(name, email, password);
		user.setUsersAge(dateOfBirth);
		user.setUsersGender(gender);
		database.addUserToMap(email, user);
	}

	public Collection<User> getAllUsers() {
		return database.getAllUsers();
	}
}
