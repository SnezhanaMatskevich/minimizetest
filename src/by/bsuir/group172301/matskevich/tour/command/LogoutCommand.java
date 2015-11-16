package by.bsuir.group172301.matskevich.tour.command;

import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.logic.authentication.AuthenticationLogic;
import by.bsuir.group172301.matskevich.tour.resource.PathManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logout command
 */
public class LogoutCommand extends ActionCommand{

    private final Logger logger = Logger.getRootLogger();

    /**
     * Everyone allowed to logout
     * @param user can be null
     * @return
     */
    @Override
    public boolean checkAccess(User user) {
        return true;
    }

    /**
     * Execute logout
     * @param request request to read the command from
     * @param response
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User) request.getSession().getAttribute(AuthenticationLogic.SESSION_VAR);
        if (user != null){
            AuthenticationLogic.logout(request.getSession());
            logger.info("Logged out: " + user.getUsername());
        }
        return pathManager.getString("path.page.main");
    }
}
