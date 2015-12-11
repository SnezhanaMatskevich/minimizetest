package by.bsuir.group172301.matskevich.tour.command.admin;

import by.bsuir.group172301.matskevich.tour.command.AdminCommand;
import by.bsuir.group172301.matskevich.tour.dao.ReporDAO;
import by.bsuir.group172301.matskevich.tour.dao.TourDAO;
import by.bsuir.group172301.matskevich.tour.entity.Report;
import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.notification.creator.NotificationCreator;
import by.bsuir.group172301.matskevich.tour.notification.entity.Notification;
import by.bsuir.group172301.matskevich.tour.notification.service.NotificationService;
import by.bsuir.group172301.matskevich.tour.resource.LocaleManager;
import by.bsuir.group172301.matskevich.tour.resource.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * Delete tour command
 */
public class DeleteTourCommand extends AdminCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String param = request.getParameter("id");
        if (param != null){
            Notification notification = null;
            Locale locale = LocaleManager.INSTANCE.resolveLocale(request);
            try{
                int id = Integer.parseInt(param);
                ReporDAO dao = ReporDAO.getInstance();
                if (dao.delete(id)){
                    List<Report> tours = dao.findAll();
                    request.setAttribute("reports", tours);
                    notification = NotificationCreator.createFromProperty("info.db.delete_success", locale);
                }
            } catch (NumberFormatException e){
                notification = NotificationCreator.createFromProperty("error.invalid_parameter", Notification.Type.ERROR, locale);
            } catch (DAOTechnicalException e) {
                throw new CommandException(e);
            } catch (DAOLogicalException e) {
                notification = NotificationCreator.createFromProperty("error.db.no_such_record", Notification.Type.ERROR, locale);
            } finally {
                if (notification != null){
                    NotificationService.push(request.getSession(), notification);
                }
            }
        }

        return pathManager.getString("path.page.admin.manager");
    }
}
