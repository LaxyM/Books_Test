package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.repository.ReaderRepository;
import src.service.ReaderService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReaderServiceTests {
    private ReaderService service;
    private ReaderRepository repository;

    @BeforeEach
    void setUp() {
        service = new ReaderService(repository);
    }

    @Test
    public void testIsEmailValid() {
        assertTrue(service.isEmailValid("test@email.com"));
        assertTrue(service.isEmailValid("test123@email.com"));
        assertTrue(service.isEmailValid("test_123@email.com"));
        assertTrue(service.isEmailValid("test-123@email.com"));
        assertTrue(service.isEmailValid("test123@email.co.uk"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(service.isEmailValid(null));
        assertFalse(service.isEmailValid(""));
        assertFalse(service.isEmailValid("1@q.net"));
        assertFalse(service.isEmailValid("test@.net"));
        assertFalse(service.isEmailValid("test@emailnet"));
        assertFalse(service.isEmailValid("test.net"));
        assertFalse(service.isEmailValid("test@em@il.net"));
        assertFalse(service.isEmailValid("test@.email.net"));
        assertFalse(service.isEmailValid("test@email.net."));
        assertFalse(service.isEmailValid("test@emailne.t"));
        assertFalse(service.isEmailValid("test@.em&il.net"));
        assertFalse(service.isEmailValid("@testemail.net"));
    }

    @Test
    public void testIsPasswordValid() {
        assertTrue(service.isPasswordValid("11111Qw@"));
        assertTrue(service.isPasswordValid("Qqqqqq1&"));
        assertTrue(service.isPasswordValid("QQQQQq1&"));
        assertTrue(service.isPasswordValid("!@#$%&*()[]Qq1"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(service.isPasswordValid(null));
        assertFalse(service.isPasswordValid(""));
        assertFalse(service.isPasswordValid("1"));
        assertFalse(service.isPasswordValid("q"));
        assertFalse(service.isPasswordValid("Q"));
        assertFalse(service.isPasswordValid("@"));
        assertFalse(service.isPasswordValid("1111Qq@"));
        assertFalse(service.isPasswordValid("QQQQqqqq"));
        assertFalse(service.isPasswordValid("QQQQ1111"));
        assertFalse(service.isPasswordValid("QQQQ####"));
        assertFalse(service.isPasswordValid("qqqq####"));
        assertFalse(service.isPasswordValid("1111####"));
        assertFalse(service.isPasswordValid("1111qqqq"));
        assertFalse(service.isPasswordValid("qqq11###"));
        assertFalse(service.isPasswordValid("qqqQQ###"));
        assertFalse(service.isPasswordValid("QQqq1111"));
    }






}
