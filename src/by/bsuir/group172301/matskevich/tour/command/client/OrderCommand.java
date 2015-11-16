package by.bsuir.group172301.matskevich.tour.command.client;


import by.bsuir.group172301.matskevich.tour.command.ClientCommand;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.bsuir.group172301.matskevich.tour.dao.OrderDAO;
import by.bsuir.group172301.matskevich.tour.dao.TourDAO;
import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.logic.authentication.AuthenticationLogic;
import by.bsuir.group172301.matskevich.tour.logic.client.ClientLogic;
import by.bsuir.group172301.matskevich.tour.logic.order.OrderLogic;
import by.bsuir.group172301.matskevich.tour.notification.creator.NotificationCreator;
import by.bsuir.group172301.matskevich.tour.notification.entity.Notification;
import by.bsuir.group172301.matskevich.tour.notification.service.NotificationService;
import by.bsuir.group172301.matskevich.tour.resource.LocaleManager;

/**
 * Perform a tour order
 */
public class OrderCommand extends ClientCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User user = AuthenticationLogic.user(request);
        Notification notification = null;

        int id;
        Locale locale = LocaleManager.INSTANCE.resolveLocale(request);
        try {
            id = Integer.parseInt(request.getParameter("id"));

        } catch (NumberFormatException e){
            notification = NotificationCreator.createFromProperty("error.invalid_parameter", Notification.Type.ERROR, locale);
            return pathManager.getString("path.page.admin.manager");
        }
        try{
            TourDAO tourDAO = TourDAO.getInstance();
            Tour tour = tourDAO.findById(id);
            boolean regular = ClientLogic.isRegularClient(user);

            double amount = regular ? (double) (tour.getPrice() - (tour.getPrice() * tour.getRegularDiscount() * 0.01)) : tour.getPrice();
            request.setAttribute("amount", amount);
            request.setAttribute("tour", tour);

            if ("1".equals(request.getParameter("confirm"))){
                boolean result = OrderLogic.clientOrders(user, tour, amount);

                if (result){

                    return pathManager.getString("path.page.client.complete");
                } else {
                    notification = NotificationCreator.createFromProperty("unknown error", locale);
                }
            }
        } catch (DAOTechnicalException e) {
            throw new CommandException(e);
        } catch (DAOLogicalException e) {
            throw new CommandException(e);

        } finally {
            if (notification != null){
                NotificationService.push(request.getSession(), notification);
            }
        }

        return pathManager.getString("path.page.client.order");
    }
}
