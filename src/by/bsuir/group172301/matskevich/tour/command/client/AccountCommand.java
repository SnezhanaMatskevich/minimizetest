package by.bsuir.group172301.matskevich.tour.command.client;

import by.bsuir.group172301.matskevich.tour.command.ClientCommand;
import static by.bsuir.group172301.matskevich.tour.command.LoginCommand.LOGIN_PARAMETER;
import static by.bsuir.group172301.matskevich.tour.command.LoginCommand.PASSWORD_PARAMETER;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.bsuir.group172301.matskevich.tour.dao.OrderDAO;
import by.bsuir.group172301.matskevich.tour.entity.Order;
import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.BuildException;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.logic.authentication.AuthenticationLogic;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * view list of orders
 */
public class AccountCommand extends ClientCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final String rows = request.getParameter("rows");
        final String columns = request.getParameter("colums");
        System.out.println("------------------------------------");
        System.out.println(rows);
         System.out.println(columns);
        return pathManager.getString("path.page.client.account");
    }

    
}