package by.bsuir.group172301.matskevich.tour.command;

import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.resource.PathManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command is used if empty or wrong command is specified
 */
public class EmptyCommand extends ActionCommand{

    @Override
    public boolean checkAccess(User user) {
        return true;
    }

    /**
     * default command that render main page
     * @param request request to read the command from
     * @param response response
     * @return path
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return PathManager.INSTANCE.getString("path.page.main");
    }
}
