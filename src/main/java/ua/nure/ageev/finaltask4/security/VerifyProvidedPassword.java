package ua.nure.ageev.finaltask4.security;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.domain.User;
import ua.nure.ageev.finaltask4.services.UserService;
import ua.nure.ageev.finaltask4.services.impl.UserServiceImpl;

/**
 *
 */
public class VerifyProvidedPassword {

    private static final Logger LOG = Logger.getLogger(VerifyProvidedPassword.class);

    private VerifyProvidedPassword(){
        //to hide implicit public constructor
    }

    public static boolean isPasswordCorrect(String providedPassword,  User user){
        UserService userService = new UserServiceImpl();
        String hashAlgorithm = userService.getHashAlgorithm(user.getId());
        LOG.debug("Found in DB: hashAlgorithm --> " + hashAlgorithm);
        if( hashAlgorithm == null ){
          return isNoHashedPasswordCorrect(providedPassword,user);
        }
        else {
            return isHashedPasswordCorrect(providedPassword,user);
        }
    }

    public static boolean isNoHashedPasswordCorrect(String providedPassword,  User user){
        return user.getPassword().equals(providedPassword);
    }

    public static boolean isHashedPasswordCorrect (String providedPassword,  User user){
        UserService userService = new UserServiceImpl();
        String salt = userService.getSalt(user.getId());
        return user.getPassword().equals(SecurePassword.getSecurePassword(providedPassword,salt));
    }

}
