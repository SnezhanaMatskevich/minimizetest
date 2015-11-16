package by.bsuir.group172301.matskevich.tour.command.admin;

import by.bsuir.group172301.matskevich.tour.builder.TourBuilder;
import by.bsuir.group172301.matskevich.tour.command.AdminCommand;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import by.bsuir.group172301.matskevich.tour.resource.PathManager;
import org.apache.log4j.Logger;


/**
 * Add tour command
 */
public class AddTourCommand extends AdminCommand {

    @Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        request.setAttribute("tourTypes", TourType.values());
        Notification notification = null;
        Locale locale = LocaleManager.INSTANCE.resolveLocale(request);
        Tour tour = new Tour();

        if (request.getParameter("submit") != null){
            TourBuilder tourBuilder = new TourBuilder();
            try {
                tourBuilder.build(request.getParameterMap(), tour);
                TourDAO dao = TourDAO.getInstance();
                if (dao.create(tour)){
                    notification = NotificationCreator.createFromProperty("info.db.create_success", locale);
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
            } finally {
                if (notification != null){
                    NotificationService.push(request.getSession(), notification);
                }
            }
        }

        request.setAttribute("tour", tour);

		return pathManager.getString("path.page.admin.add_tour");
	}
}
