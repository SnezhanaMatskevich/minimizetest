package by.bsuir.group172301.matskevich.tour.command;

import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.resource.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract command
 */
public abstract class ActionCommand {

    /**
     * Path manager
     */
    protected static final PathManager pathManager = PathManager.INSTANCE;

    /**
     * Check the access of user, return true if the user has access to
     * this command, otherwise return false
     * @param user can be null
     * @return
     */
    public abstract boolean checkAccess(User user);

    /**
     * This method reads a command from the request
     * and processes it. The result will be given as
     * a page to forward to
     *
     * @param request request to read the command from
     * @param response
     * @return forward page
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
