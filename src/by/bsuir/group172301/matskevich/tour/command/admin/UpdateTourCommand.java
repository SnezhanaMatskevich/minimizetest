package by.bsuir.group172301.matskevich.tour.command.admin;

import by.bsuir.group172301.matskevich.tour.builder.TourBuilder;
import by.bsuir.group172301.matskevich.tour.command.AdminCommand;
import by.bsuir.group172301.matskevich.tour.dao.TourDAO;
import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.entity.TourType;
import by.bsuir.group172301.matskevich.tour.exception.BuildException;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.notification.creator.NotificationCreator;
import by.bsuir.group172301.matskevich.tour.notification.entity.Notification;
import by.bsuir.group172301.matskevich.tour.notification.service.NotificationService;
import by.bsuir.group172301.matskevich.tour.resource.LocaleManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 *  Update tour
 */
public class UpdateTourCommand extends AdminCommand{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		request.setAttribute("tourTypes", TourType.values());
		Notification notification = null;
		Locale locale = LocaleManager.INSTANCE.resolveLocale(request);
		TourDAO dao = TourDAO.getInstance();
		Tour tour = new Tour();
		int id;

		try{
			try {
				id = Integer.parseInt(request.getParameter("id"));

			} catch (NumberFormatException e){
				notification = NotificationCreator.createFromProperty("error.invalid_parameter", Notification.Type.ERROR, locale);
				return pathManager.getString("path.page.admin.manager");
			}

			if (request.getParameter("submit") != null){
				tour = new Tour();
				tour.setId(id);

				TourBuilder tourBuilder = new TourBuilder();
				try {

					tourBuilder.build(request.getParameterMap(), tour);

					if (dao.update(tour)){
						notification = NotificationCreator.createFromProperty("info.db.update_success", locale);
                        List<Tour> tours = dao.findAll();
                        request.setAttribute("tours", tours);
						return pathManager.getString("path.page.admin.manager");
					}

				} catch (BuildException e) {
					notification = NotificationCreator.createFromProperty("add_tour.invalid_form_data", Notification.Type.ERROR,  locale);
				} catch (DAOTechnicalException e) {
					throw new CommandException(e);

				} catch (DAOLogicalException e) {
					notification = new Notification(e.getMessage(), Notification.Type.ERROR);
				}
			} else {
				try {
					tour = dao.findById(id);

				} catch (DAOTechnicalException e) {
					throw new CommandException(e);
				} catch (DAOLogicalException e) {
					notification = NotificationCreator.createFromProperty("error.db.no_such_record", Notification.Type.ERROR, locale);
					return pathManager.getString("path.page.admin.manager");
				}
			}
		} finally {
			if (notification != null){
				NotificationService.push(request.getSession(), notification);
			}

		}


		request.setAttribute("tour", tour);

		return pathManager.getString("path.page.admin.update_tour");
	}
}
