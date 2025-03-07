package uk.ac.shef.com3529.forum;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class ForumTest {

    /*
     * Problems with following test:
     * 1. Test name could be more descriptive of what the test is doing
     * 2. Test should not require understanding of the makeForum method
     * 3. Test also assumes billy exists (or is created in makeForum) which is brittle
     * */
    @Test
    public void shouldNotLoginBannedUser() {
        // Given a forum with registered users
        Forum forum = makeForum();

        // When the user is banned
        forum.ban("billy");

        // Then the user cannot login
        boolean loggedIn = forum.login("billy");
        assertFalse(loggedIn);
    }

    @Test
    public void shouldPreventBannedUserFromLoggingIn() {
        // Given a forum with registered user Billy
        Forum forum = new Forum();
        forum.registerUser("Billy Sharp", "billy", "b.sharp@sufc.co.uk");

        // When the user is banned
        forum.ban("billy");

        // Then the user cannot login
        boolean loggedIn = forum.login("billy");
        assertFalse(loggedIn);
    }

    /*
     * Problems with following test:
     * 1. Test name indicates a method is being tested rather than behaviour
     * 2. Test does not ensure the correct user has been gotten, since it uses assertNotNull
     * 3.
     * */
    @Test
    public void shouldGetRegisteredUser() {
        // Given a forum
        Forum forum = new Forum();

        // When a user is registered
        forum.registerUser("John Egan", "captain", "j.egan@sufc.co.uk");

        // Then can get the user
        assertNotNull(forum.getUser("captain"));
    }

    @Test
    public void shouldGetRegisteredUserByUsername() {
        // Given a forum
        Forum forum = new Forum();

        // When a user is registered
        forum.registerUser("John Egan", "captain", "j.egan@sufc.co.uk");

        // Then can get the correct user details
        assertTrue(forum.getUser("captain").getName().equals("John Egan"));
        assertTrue(forum.getUser("captain").getEmailAddress().equals("j.egan@sufc.co.uk"));

    }

    /*
    * Problems with following test:
    * 1. names should be more descriptive and ideally begin with should
    * 2. Testing a method and not behaviour
    * 3. Put logic in the test (the for loop)
    * 4. No clear Given When Then logic
    * */
    @Test
    public void testBan() {
        Forum forum = makeForum();
        for (String username : forum.getUsernames()) {
            forum.ban(username);
        }
        for (String username : forum.getUsernames()) {
            User user = forum.getUser(username);
            assertTrue(user.isBanned());
        }
    }

    @Test
    public void shouldRecordUserAsBannedAfterBeingBanned() {
        // Given a forum
        Forum forum = makeForum();

        // When a user is registered and banned
        forum.registerUser("John Egan", "captain", "j.egan@sufc.co.uk");
        forum.ban("captain");

        // Then it should be recorded that the user is banned
        User user = forum.getUser("captain");
        assertTrue(user.isBanned());
    }

    /*
     * Problems with following test:
     * 1. names should be more descriptive and ideally begin with should
     * 2. Testing a method and not behaviour
     * 3. No clear Given When Then logic
     * 4. Relies on understanding makeForum and makePosts - test should be understandable without this
     * 5. Its logic for testing the method isn't concise at all and overcomplicates what it needs to do
     * */
    @Test
    public void testGetMostProfilicPoster() {
        Forum forum = makeForum();
        makePosts(forum);
        assertThat(forum.getUser(forum.getMostProlificUser()).numPostsMade(), equalTo(2));
    }

    @Test
    public void shouldReturnUsernameOfUserWithTheMostPosts() {

        // Given a forum with registered users
        Forum forum = new Forum();
        forum.registerUser("Billy Sharp", "billy", "b.sharp@sufc.co.uk");
        forum.registerUser("Iliman NDiaye", "ili", "i.ndiaye@sufc.co.uk");
        forum.registerUser("Chris Basham", "bash", "c.basham@sufc.co.uk");

        // When users have made posts
        forum.post("bash", "Billy Sharp scores goals", "We got Billy Sharp");
        forum.post("bash", "Boxing Day", "Hark now hear");
        forum.post("billy", "Bladesmen", "We are Bladesmen");

        // Then the username of the user with the most posts is returned
        assertThat(forum.getMostProlificUser(), equalTo("bash"));
    }

    Forum makeForum() {
        Forum forum = new Forum();
        forum.registerUser("Billy Sharp", "billy", "b.sharp@sufc.co.uk");
        forum.registerUser("Iliman NDiaye", "ili", "i.ndiaye@sufc.co.uk");
        forum.registerUser("Chris Basham", "bash", "c.basham@sufc.co.uk");
        return forum;
    }

    void makePosts(Forum forum) {
        forum.post("bash", "Billy Sharp scores goals", "We got Billy Sharp, Billy Sharp. We got Billy Sharp");
        forum.post("bash", "Boxing Day", "Hark now hear, United sing, the Wednesday Ran Away");
        forum.post("billy", "Bladesmen", "We are Bladesmen, super Bladesmen, we are Blademen, from the Lane");
    }
}
