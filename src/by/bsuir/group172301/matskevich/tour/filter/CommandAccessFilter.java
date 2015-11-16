package by.bsuir.group172301.matskevich.tour.filter;


import by.bsuir.group172301.matskevich.tour.command.ActionCommand;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.helper.RequestHelper;
import by.bsuir.group172301.matskevich.tour.logic.authentication.AuthenticationLogic;
import by.bsuir.group172301.matskevich.tour.resource.PathManager;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * command access filter
 */
public class CommandAccessFilter implements Filter {

    private PathManager pathManager = PathManager.INSTANCE;
    private Logger logger = Logger.getRootLogger();

    public void destroy() {
        //blank
    }

    /**
     * Checks the user role before executing command
     * If user doesn't have required role a 403 error
     * page will be displayed
     *
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        RequestHelper requestHelper = RequestHelper.INSTANCE;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        ActionCommand command = requestHelper.getCommand(request);

        User user = AuthenticationLogic.user(request);

        if (command.checkAccess(user)){
            chain.doFilter(req, resp);
        } else{
            response.setStatus(403);
            logger.error(String.format("Access denied for %s to the following command: %s", (user != null) ? user : "anonymous user", command));
            request.getRequestDispatcher(pathManager.getString("path.error403")).forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        // blank
    }

}
