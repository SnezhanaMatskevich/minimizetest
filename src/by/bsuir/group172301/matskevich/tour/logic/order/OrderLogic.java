package by.bsuir.group172301.matskevich.tour.logic.order;

import by.bsuir.group172301.matskevich.tour.dao.OrderDAO;
import by.bsuir.group172301.matskevich.tour.entity.Order;
import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Performs order logic
 */
public class OrderLogic {
    private static final Logger logger = Logger.getRootLogger();

    public static boolean clientOrders(User user, Tour tour, double amount){

        if (user != null && tour != null){
            Order order = new Order();
            order.setUser(user);
            order.setTour(tour);

            order.setAmount(amount);
            order.setDateTime(new Date());

            OrderDAO dao = OrderDAO.getInstance();
            try{
                return dao.create(order);
            } catch (DAOTechnicalException e) {
                logger.error(e.getMessage(), e);
                return false;
            } catch (DAOLogicalException e) {
                logger.error(e.getMessage(), e);
                return false;
            }
        } else{
            logger.error("Invalid data user: " + user + ", tour: " + tour);
            return false;
        }

    }
}
