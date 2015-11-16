package by.bsuir.group172301.matskevich.tour.command.admin;


import by.bsuir.group172301.matskevich.tour.command.AdminCommand;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.bsuir.group172301.matskevich.tour.dao.TourDAO;
import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.logic.authentication.AuthenticationLogic;
import by.bsuir.group172301.matskevich.tour.logic.client.ClientLogic;
import by.bsuir.group172301.matskevich.tour.resource.PathManager;


/**
 * Command for managing tours
 */
public class ManagerCommand extends AdminCommand {

    /**
     * Displays a list of tours with the ability to
     * delete, update or create a new one
     *
     * @param request request to read the command from
     * @param response
     * @return
     * @throws CommandException
     */
    @Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        TourDAO dao = TourDAO.getInstance();
        try {
            List<Tour> tours = dao.findAll();
            request.setAttribute("tours", tours);
        } catch (DAOLogicalException e) {
            throw new CommandException(e);
        } catch (DAOTechnicalException e) {
            throw new CommandException(e);
        }
		return pathManager.getString("path.page.admin.manager");
	}
}
