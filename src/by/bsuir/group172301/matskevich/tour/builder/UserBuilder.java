package by.bsuir.group172301.matskevich.tour.builder;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.entity.TourType;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.BuildException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * Build a tour object from request
 */
public class UserBuilder extends EntityBuilder<User> {

    private Logger logger = Logger.getRootLogger();
    @Override
    public User build(Map<String, String[]> map) throws BuildException {
        User user = new User();
        build(map, user);

        return user;
    }

    /**
     * Fill entity
     * @param map
     * @param user
     * @param tour
     * @throws BuildException
     */
    public void build(Map<String, String[]> map, User user) throws BuildException{
        boolean login = !buildLogin(map.get("login"), user);
        boolean password = !buildPassword(map.get("password"), user);
        boolean firstName = !buildFirstName(map.get("firstName"), user);
        boolean lastName = !buildLastName(map.get("lastName"), user);
        boolean dateOfBirth = !buildDateOfBirth(map.get("dateOfBirth"), user);
        boolean email = !buildEmail(map.get("email"), user);
        boolean fullAddress = !buildFullAdress(map.get("fullAdress"), user);
	

        if (login|| password||firstName||lastName||dateOfBirth||email||fullAddress ){
            throw new BuildException();
        }

    }

    /**
     * build tour Tourname
     * @param args parameter values
     * @param tour target tour
     */
    public boolean buildLogin(String[] args, User user) {
        if (args != null && args.length > 0){
            String login = args[0];
            if (login.length() > 0){
                try {
                    user.setUsername(new String(args[0].getBytes("ISO-8859-1"),"UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
                return true;
            }

        }
        return false;
    }


   

    public boolean buildPassword(String[] args, User user) {
        if (args != null && args.length > 0){
            String pass = args[0];
            
                    
            if (pass.length() > 0){
                 try {
                     String tmp =new String(args[0].getBytes("ISO-8859-1"),"UTF-8");
                     String hash = DigestUtils.md5Hex(tmp);
                    user.setPassword(hash);
                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }

                return true;
            }

        }
        return false;
    }
public boolean buildEmail(String[] args, User user) {
        if (args != null && args.length > 0){
            String email = args[0];
            if (email.length() > 0){
                try {
                    user.setEmail(new String(args[0].getBytes("ISO-8859-1"),"UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
                return true;
            }

        }
        return false;
    }
public boolean buildFirstName(String[] args, User user) {
        if (args != null && args.length > 0){
            String firstName = args[0];
            if (firstName.length() > 0){
                try {
                    user.setFirstName(new String(args[0].getBytes("ISO-8859-1"),"UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
                return true;
            }

        }
        return false;
    }
public boolean buildLastName(String[] args, User user) {
        if (args != null && args.length > 0){
            String lastName = args[0];
            if (lastName.length() > 0){
                try {
                    user.setLastName(new String(args[0].getBytes("ISO-8859-1"),"UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
                return true;
            }

        }
        return false;
    }
public boolean buildDateOfBirth(String[] args, User user) {
        if (args != null && args.length > 0){
            String dateOfBirth = args[0];
            if (dateOfBirth.length() > 0){
                try {
                    user.setDateOfBirth(new String(args[0].getBytes("ISO-8859-1"),"UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
                return true;
            }

        }
        return false;
    }
public boolean buildFullAdress(String[] args, User user) {
        if (args != null && args.length > 0){
            String fullAdress = args[0];
            if (fullAdress.length() > 0){
                try {
                    user.setFullAddress(new String(args[0].getBytes("ISO-8859-1"),"UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
                return true;
            }

        }
        return false;
    }
}
