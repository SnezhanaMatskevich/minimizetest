package by.bsuir.group172301.matskevich.tour.command;

import java.util.Locale;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.bsuir.group172301.matskevich.tour.builder.UserBuilder;
import static by.bsuir.group172301.matskevich.tour.command.ActionCommand.pathManager;
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
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import org.apache.log4j.Logger;

/**
 *
 * @author Snezhana
 */
public class SignUpCommand extends ActionCommand {

    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String FIRSTNAME_PARAMETER = "firstName";
    public static final String LASTNAME_PARAMETER = "lastName";
    public static final String DAYOFBIRTH_PARAMETER = "dateOfBirth";
    public static final String FULLADRESS_PARAMETER = "fullAdress";
    public static final String EMAIL_PARAMETER = "email";

    private Logger logger = Logger.getRootLogger();

    @Override
    public boolean checkAccess(User user) {
        return true;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        Notification notification = null;

        Locale locale = LocaleManager.INSTANCE.resolveLocale(request);
        final String login = request.getParameter(LOGIN_PARAMETER);
        final String password = request.getParameter(PASSWORD_PARAMETER);
        final String firstName = request.getParameter(FIRSTNAME_PARAMETER);
        final String lastName = request.getParameter(LASTNAME_PARAMETER);
        final String email = request.getParameter(EMAIL_PARAMETER);
        final String dateOfBirth = request.getParameter(DAYOFBIRTH_PARAMETER);
        final String fullAdress = request.getParameter(FULLADRESS_PARAMETER);

        if (login != null && password != null && firstName != null && lastName != null && email != null && dateOfBirth != null && fullAdress != null) {
            User user = new User();
            UserBuilder userBuilder = new UserBuilder();
            try {
                userBuilder.build(request.getParameterMap(), user);
                UserDAO dao = UserDAO.getInstance();
                if (dao.create(user)) {
                    CongratsMailSender cMailSender = new CongratsMailSender();
                    try {
                        cMailSender.sendMail(email);
                    } catch (MessagingException | UnsupportedEncodingException ex) {
                        java.util.logging.Logger.getLogger(SignUpCommand.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    notification = NotificationCreator.createFromProperty("info.db.create_success", locale);
                    return pathManager.getString("path.page.signup");
                }
            } catch (DAOTechnicalException | DAOLogicalException e) {
                throw new CommandException(e);
            } catch (BuildException ex) {
                java.util.logging.Logger.getLogger(SignUpCommand.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (notification != null) {
                    NotificationService.push(request.getSession(), notification);
                }
            }
        }
        return pathManager.getString("path.page.signup");
    }

}
