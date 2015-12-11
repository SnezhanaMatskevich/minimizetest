package by.bsuir.group172301.matskevich.tour.command;


import by.bsuir.group172301.matskevich.tour.command.ActionCommand;
import by.bsuir.group172301.matskevich.tour.dao.MatrixDAO;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.util.matrix.DAODataBase;
import by.bsuir.group172301.matskevich.tour.util.matrix.Matrix;
import by.bsuir.group172301.matskevich.tour.util.matrix.MatrixDouble;
import by.bsuir.group172301.matskevich.tour.util.matrix.MatrixIndexOutOfBoundsException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class CountCommand extends ActionCommand {

    
        private static final DAODataBase FACTORY = null;
    
//    private static final CustomerDaoMatrix MATRIX_TABLE = FACTORY.getCustomerDAO();
//    private static final CustomerDaoHistory HISTORY_TABLE = FACTORY.getCustomerHistoryDAO();
//    private static final CustomerDaoMatrixList MATRIX_LIST_TABLE = FACTORY.getCustomerMatrixListDAO();

    private Logger logger = Logger.getRootLogger();

    @Override
    public boolean checkAccess(User user) {
        return true;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        

         return pathManager.getString("path.page.client.account");
    }
    
     

}
