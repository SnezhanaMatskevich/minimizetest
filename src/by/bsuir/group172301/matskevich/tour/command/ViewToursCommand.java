package by.bsuir.group172301.matskevich.tour.command;

import by.bsuir.group172301.matskevich.tour.command.ActionCommand;
import by.bsuir.group172301.matskevich.tour.dao.TourDAO;
import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.logic.authentication.AuthenticationLogic;
import by.bsuir.group172301.matskevich.tour.logic.client.ClientLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
public class ViewToursCommand extends ActionCommand{
	@Override
	public boolean checkAccess(User user) {
		return true;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		TourDAO dao = TourDAO.getInstance();
		try {
			List<Tour> tours = dao.findAll();
            User client = AuthenticationLogic.user(request);
            boolean regular = ClientLogic.isRegularClient(client);
            request.setAttribute("regular", regular);
			request.setAttribute("tours", tours);
		} catch (DAOLogicalException e) {
			throw new CommandException(e);
		} catch (DAOTechnicalException e) {
			throw new CommandException(e);
		}

		return pathManager.getString("path.page.tours");
	}
}
