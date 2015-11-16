package by.bsuir.group172301.matskevich.tour.listener; /**
 *
 */

import by.bsuir.group172301.matskevich.tour.connection.ConnectionPool;
import by.bsuir.group172301.matskevich.tour.exception.ConnectionPoolException;
import by.bsuir.group172301.matskevich.tour.resource.LocaleManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Locale;

public class ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener{

    private Logger logger = Logger.getRootLogger();
    private LocaleManager localeManager = LocaleManager.INSTANCE;


    // Public constructor is required by servlet spec
    public ApplicationListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        context.setAttribute("locales", LocaleManager.INSTANCE.getLocales());

        Locale.setDefault(Locale.ENGLISH);

    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.shutDown();
        } catch (ConnectionPoolException e) {
            logger.error(e.getMessage());
        }
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {

    }



    public void sessionDestroyed(HttpSessionEvent se) {

    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {

    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest request = servletRequestEvent.getServletRequest();
        Locale locale = localeManager.resolveLocale(request);
        request.setAttribute("locale", locale);
    }
}
