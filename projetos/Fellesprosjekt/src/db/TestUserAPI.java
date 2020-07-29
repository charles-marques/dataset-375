package db;

import model.User;

public class TestUserAPI {
	public static void main(String[] args) {
		TestBase test = new TestBase();
		
		UserAPI api = test.getUserAPI();
		
		User user = api.getUser("tester");
		if (user != null) {
			System.out.println(user.getName() + " " + user.getEmail());
		}
		
		User newUser = new User(0, "testmannen", "Trond Testmann", "trondtest@hotmail.com", "Brukerstøtte");
		try {
			api.addUser(newUser, "mitt_Passord-3991");
		} catch (Exception e) {
			// the exception is handled in the API
		}
	}
}
