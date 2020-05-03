package ua.nure.ageev.finaltask4.security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SecurePasswordTest {

    // Known inputs and outputs
    // hashes are precomputed with an external SHA256 calculator and verified
    public static final String password = "123";

    public static final String salt = "ce7185ff132485e0f4681163b1047c8c";

    // This is what we want: SHA256(salt+password);
    public static final String saltedHash = "6662e796dcd90e73e3e9f95d74ab0a5b1bbdf71d62b703267c4d8351f011c8a6";

    @Test
    public void testCorrectPasswordHash() {
        String hash = SecurePassword.getSecurePassword(password,salt);

        assertEquals("Hash should match SHA256(salt + password)",
                saltedHash, hash);
    }
}