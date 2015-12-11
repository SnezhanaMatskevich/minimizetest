/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.group172301.matskevich.tour.command.client;

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
import by.bsuir.group172301.matskevich.tour.util.PDFCreator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownCommand extends AdminCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String param = request.getParameter("id");
        System.out.println("PATAMETR ID");
        System.out.println(param);
        if (param != null){
            Notification notification = null;
            Locale locale = LocaleManager.INSTANCE.resolveLocale(request);
            try{
                int id = Integer.parseInt(param);
                ReporDAO dao = ReporDAO.getInstance();
              Report report= dao.findById(id);
                   // List<Tour> tours = dao.findAll();
                    request.setAttribute("reports", report);
                 
                    int col1=report.getCol1();
                    int col2 = report.getCol2();
                    double perc = report.getPerc();
                    String name = report.getReport();
                    PDFCreator obj = new PDFCreator();
                  //  obj.generateBarChart(col1, col2);
              obj.writeChartToPDF(obj.generateBarChart(col1, col2), 350, 400, "C://Reports//report"+param+".pdf", col1, col2, perc);

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

      //  return pathManager.getString("path.page.admin.manager");
       return pathManager.getString("path.page.download");
    }
}
