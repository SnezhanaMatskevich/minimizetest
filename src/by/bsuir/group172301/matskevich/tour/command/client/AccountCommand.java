package by.bsuir.group172301.matskevich.tour.command.client;


import by.bsuir.group172301.matskevich.tour.command.ClientCommand;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.bsuir.group172301.matskevich.tour.dao.OrderDAO;
import by.bsuir.group172301.matskevich.tour.entity.Order;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.logic.authentication.AuthenticationLogic;

/**
 * view list of orders
 */
public class AccountCommand extends ClientCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = AuthenticationLogic.user(request);

        try {
            List<Order> orders = OrderDAO.getInstance().findOrdersForUser(user);
            request.setAttribute("orders", orders);
        } catch (DAOLogicalException e) {
            throw new CommandException(e);
        } catch (DAOTechnicalException e) {
            throw new CommandException(e);
        }

        return pathManager.getString("path.page.client.account");
    }
}
