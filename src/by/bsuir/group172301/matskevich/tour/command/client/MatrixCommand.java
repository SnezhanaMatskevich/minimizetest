/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.group172301.matskevich.tour.command.client;

import by.bsuir.group172301.matskevich.tour.builder.UserBuilder;
import by.bsuir.group172301.matskevich.tour.command.ActionCommand;
import by.bsuir.group172301.matskevich.tour.dao.MatrixDAO;
import by.bsuir.group172301.matskevich.tour.dao.UserDAO;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.BuildException;
import by.bsuir.group172301.matskevich.tour.exception.CommandException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.mail.CongratsMailSender;
import by.bsuir.group172301.matskevich.tour.notification.creator.NotificationCreator;
import by.bsuir.group172301.matskevich.tour.notification.entity.Notification;
import by.bsuir.group172301.matskevich.tour.notification.service.NotificationService;
import by.bsuir.group172301.matskevich.tour.resource.LocaleManager;
import by.bsuir.group172301.matskevich.tour.util.matrix.CustomerDaoHistory;
import by.bsuir.group172301.matskevich.tour.util.matrix.CustomerDaoMatrix;
import by.bsuir.group172301.matskevich.tour.util.matrix.CustomerDaoMatrixList;
import by.bsuir.group172301.matskevich.tour.util.matrix.DAODataBase;
import by.bsuir.group172301.matskevich.tour.util.matrix.Matrix;
import by.bsuir.group172301.matskevich.tour.util.matrix.MatrixDouble;
import by.bsuir.group172301.matskevich.tour.util.matrix.MatrixIndexOutOfBoundsException;
import by.bsuir.group172301.matskevich.tour.util.matrix.Pages;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Sniazhana_Matskevich
 */
public class MatrixCommand extends ActionCommand {

    
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
       MatrixDAO dao = new MatrixDAO();
        String rowsAString = request.getParameter("rowsA");
        //System.out.println("String rowsAString = " + rowsAString);
        String colsAString = request.getParameter("colsA");
        //System.out.println("String colsAString = " + colsAString);
        String idAString = request.getParameter("idA");
        //System.out.println("String idAString = " + idAString);

        int rowsA = 0;
        int colsA = 0;
        int idA = 0;

        try {
            rowsA = Integer.parseInt(rowsAString);
            colsA = Integer.parseInt(colsAString);
            idA = Integer.parseInt(idAString);
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "Input error.");
        //    LOGER.error("parseInt error", ex);
        }

        Matrix matrixA = new MatrixDouble(rowsA, colsA);
        String tempString = null;
        String nameTempString = null;
        double temp = 0;

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                nameTempString = "mA" + i + "" + j;
                //System.out.println("nameTempString   = " + nameTempString);
                tempString = request.getParameter(nameTempString);
                //System.out.println("tempString  = " + tempString);
                temp = Integer.parseInt(tempString);
                try {
                    matrixA.setValue(i, j, temp);
                } catch (MatrixIndexOutOfBoundsException ex) {
                    request.setAttribute("error", "Input error.");
                 //   LOGER.error("read matrix error", ex);
                }
            }
        }

        matrixA.print();

        try {
            request.setAttribute("result", matrixA);
            dao.create(matrixA);
       } catch (NumberFormatException ex) {
           request.setAttribute("message", "Input error.");
         //  LOGER.error("insert matrix error", ex);
        } catch (DAOLogicalException ex) {
            java.util.logging.Logger.getLogger(MatrixCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOTechnicalException ex) {
            java.util.logging.Logger.getLogger(MatrixCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(MatrixCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
         return pathManager.getString("path.page.search");
    }
    
}
