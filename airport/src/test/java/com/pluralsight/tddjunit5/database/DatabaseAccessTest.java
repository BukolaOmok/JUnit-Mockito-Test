package com.pluralsight.tddjunit5.database;

import com.pluralsight.tddjunit5.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DatabaseAccessTest {

    @Mock
    private Database database;

    private Credentials credentials = new Credentials ("User", "Password");

    @Test
    void testUserSuccessfulLogin() {
        when(database.login(credentials)).thenReturn(true);
        Credentials sameCredentials = new Credentials ("User","Password");
        assertTrue(database.login(sameCredentials));
    }

    @Test
    void testUserFailedLogin () {
        when(database.login(credentials)).thenReturn(true);
        Credentials otherCredentials = new Credentials ("User", "Password");
        otherCredentials.setUserName("OtherUser");
        otherCredentials.setPassword("OtherPassword");
        assertNotEquals(credentials.getUsername(), otherCredentials.getUsername());
        assertNotEquals(credentials.getPassword(), otherCredentials.getPassword());
        assertFalse(database.login(otherCredentials));
    }
}
