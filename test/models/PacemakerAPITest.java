package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.PacemakerAPI;
import static models.Fixtures.users;
import static models.Fixtures.activities;
import static models.Fixtures.locations;

public class PacemakerAPITest
{
	private PacemakerAPI pacemaker;

	@Before
	public void setup()
	{
		pacemaker = new PacemakerAPI();
	}

	@After
	public void tearDown()
	{
		pacemaker = null;
	}

	@Test
	public void testUserEmpty()
	{
		User homer = new User ("homer", "simpson", "homer@simpson.com",  "secret");

		assertEquals (0, pacemaker.getUsers().size());
		pacemaker.createUser("homer", "simpson", "homer@simpson.com", "secret");
		assertEquals (1, pacemaker.getUsers().size());
	}

	@Test
	public void testUser()
	{
		User homer = new User ("homer", "simpson", "homer@simpson.com",  "secret");

		assertEquals (0, pacemaker.getUsers().size());
		pacemaker.createUser("homer", "simpson", "homer@simpson.com", "secret");
		assertEquals (1, pacemaker.getUsers().size());

		assertEquals (homer, pacemaker.getUserByEmail("homer@simpson.com"));
	}

	@Test
	public void testUsers()
	{
		for (User user : users)
		{
			pacemaker.createUser(user.firstName, user.lastName, user.email, user.password);
		}
		assertEquals (users.length, pacemaker.getUsers().size());
		for (User user: users)
		{
			User eachUser = pacemaker.getUserByEmail(user.email);
			assertEquals (user, eachUser);
			assertNotSame(user, eachUser);
		}
	}

	@Test
	public void testDeleteUsers()
	{
		for (User user : users)
		{
			pacemaker.createUser(user.firstName, user.lastName, user.email, user.password);
		}
		assertEquals (users.length, pacemaker.getUsers().size());
		User marge = pacemaker.getUserByEmail("marge@simpson.com");
		pacemaker.deleteUser(marge.id);
		assertEquals (users.length-1, pacemaker.getUsers().size());    
	}
}