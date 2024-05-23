import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testGetId() {
        User user = new ConcreteUser(1, "password123", "Cem", "Kula");
        assertEquals(1, user.getId());
    }

    @Test
    public void testSetId() {
        User user = new ConcreteUser();
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetPassword() {
        User user = new ConcreteUser(1, "password123", "Cem", "Kula");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        User user = new ConcreteUser();
        user.setPassword("newPassword456");
        assertEquals("newPassword456", user.getPassword());
    }

    @Test
    public void testGetName() {
        User user = new ConcreteUser(1, "password123", "Cem", "Kula");
        assertEquals("Cem", user.getName());
    }

    @Test
    public void testSetName() {
        User user = new ConcreteUser();
        user.setName("Eren");
        assertEquals("Eren", user.getName());
    }

    @Test
    public void testGetSurname() {
        User user = new ConcreteUser(1, "password123", "Cem", "Kula");
        assertEquals("Kula", user.getSurname());
    }

    @Test
    public void testSetSurname() {
        User user = new ConcreteUser();
        user.setSurname("Acar");
        assertEquals("Acar", user.getSurname());
    }

    @Test
    public void testGetInfo() {
        User user = new ConcreteUser(1, "password123", "Cem", "Kula");
        assertEquals("ID: 1\nName: Cem\nSurname: Kula", user.getInfo());
    }

    private static class ConcreteUser extends User {
        public ConcreteUser() {
            super(0, "", "", "");
        }

        public ConcreteUser(int id, String password, String name, String surname) {
            super(id, password, name, surname);
        }

        @Override
        public String getInfo() {
            return String.format("ID: %d\nName: %s\nSurname: %s", getId(), getName(), getSurname());
        }
    }
}
