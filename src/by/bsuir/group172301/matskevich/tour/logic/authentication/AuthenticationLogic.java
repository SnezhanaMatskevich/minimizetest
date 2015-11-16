package by.bsuir.group172301.matskevich.tour.logic.authentication;

import by.bsuir.group172301.matskevich.tour.dao.UserDAO;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.AuthenticationLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.AuthenticationTechnicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Performs authentication
 */
public class AuthenticationLogic {
    public static final String SESSION_VAR = "_user";

    /**
     * Authenticate user
     * @param login
     * @param password
     * @throws AuthenticationTechnicalException
     */
    public static User authenticate(String login, String password) throws AuthenticationTechnicalException, AuthenticationLogicalException {
        if (login != null && password != null){
            String hash = DigestUtils.md5Hex(password);
            UserDAO dao = new UserDAO();

            try {
                User user = dao.findByLoginAndPassword(login, hash);
				return user;

            } catch (DAOLogicalException e) {
                throw new AuthenticationLogicalException(e);
            } catch (DAOTechnicalException e) {
                throw new AuthenticationTechnicalException(e);
            }
        }
        return null;
    }

    /**
     * check if user is logged in to the system
     * @param request
     * @return
     */
    public static boolean isLoggedIn(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        return  (session.getAttribute(SESSION_VAR) != null);
    }

    /**
     * perform logout
     * @param request
     */
    public static void logout(HttpSession session){
        session.invalidate();
    }

    /**
     *
     * @param request
     * @return
     */
    public static User user(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object ob = session.getAttribute(SESSION_VAR);
        return (ob != null && ob.getClass().equals(User.class)) ? (User) ob : null;
    }
}
