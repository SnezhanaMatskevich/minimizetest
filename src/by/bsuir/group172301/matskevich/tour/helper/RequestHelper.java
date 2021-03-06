package by.bsuir.group172301.matskevich.tour.helper;

import by.bsuir.group172301.matskevich.tour.command.CountCommand;
import by.bsuir.group172301.matskevich.tour.command.ActionCommand;
import by.bsuir.group172301.matskevich.tour.command.EmptyCommand;
import by.bsuir.group172301.matskevich.tour.command.LoginCommand;
import by.bsuir.group172301.matskevich.tour.command.LogoutCommand;

import by.bsuir.group172301.matskevich.tour.command.SignUpCommand;
import by.bsuir.group172301.matskevich.tour.command.ViewToursCommand;
import by.bsuir.group172301.matskevich.tour.command.admin.AddTourCommand;
import by.bsuir.group172301.matskevich.tour.command.admin.AddToursCommand;
import by.bsuir.group172301.matskevich.tour.command.admin.DeleteTourCommand;
import by.bsuir.group172301.matskevich.tour.command.admin.ManagerCommand;
import by.bsuir.group172301.matskevich.tour.command.admin.OrdersCommand;
import by.bsuir.group172301.matskevich.tour.command.admin.UpdateTourCommand;
import by.bsuir.group172301.matskevich.tour.command.client.AccountCommand;
import by.bsuir.group172301.matskevich.tour.command.client.DownCommand;
import by.bsuir.group172301.matskevich.tour.command.client.MatrixCommand;
import by.bsuir.group172301.matskevich.tour.command.client.OrderCommand;
import by.bsuir.group172301.matskevich.tour.util.matrix.CustomerDaoHistory;
import by.bsuir.group172301.matskevich.tour.util.matrix.CustomerDaoMatrix;
import by.bsuir.group172301.matskevich.tour.util.matrix.CustomerDaoMatrixList;
import by.bsuir.group172301.matskevich.tour.util.matrix.DAODataBase;
import java.util.HashMap;
import javax.servlet.ServletRequest;
import org.apache.log4j.Logger;

/**
 * Finds command
 */
public enum RequestHelper {

    INSTANCE;

    private final Logger logger = Logger.getRootLogger();
    /**
     * Request parameter name for command
     */
    public static final String COMMAND_PARAMETER = "c";
    /**
     * action commands
     */
    private HashMap<String, ActionCommand> commands = new HashMap<String, ActionCommand>();

    {
        //Everyone commands
        commands.put("login", new LoginCommand());
        commands.put("signup", new SignUpCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("tours", new ViewToursCommand());
        //Client commands
        commands.put("account", new AccountCommand());
        commands.put("order", new OrderCommand());
        //Admin commands
        commands.put("manager", new ManagerCommand());
        commands.put("add_tour", new AddTourCommand());
        commands.put("add_tours", new AddToursCommand());
        commands.put("delete_tour", new DeleteTourCommand());
        commands.put("update_tour", new UpdateTourCommand());
        commands.put("orders", new OrdersCommand());
        commands.put("add_matrix", new MatrixCommand());
        commands.put("count", new CountCommand());
         commands.put("download", new DownCommand());
        
        
    }

    /**
     * Find command from request
     *
     * @param request
     * @return
     */
    public ActionCommand getCommand(ServletRequest request) {
        String action = request.getParameter(COMMAND_PARAMETER);
        return getCommand(action);
    }

    /**
     * Find command by name
     *
     * @param action
     * @return
     */
    public ActionCommand getCommand(String action) {

        ActionCommand command = commands.get(action);

        if (command == null) {
            command = new EmptyCommand();
        }

        return command;
    }
}
