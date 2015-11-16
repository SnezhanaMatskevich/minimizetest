package by.bsuir.group172301.matskevich.tour.notification.service;

import by.bsuir.group172301.matskevich.tour.notification.entity.Notification;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * Notification1 service
 * This class manages notifications in session
 */
public class NotificationService {

    /**
     * Session key
     */
    private static final String SERVICE_VAR = "_notifications";


    /**
     * Pushes a notification to session,
     * this notification will be stored in session
     * util popped out
     * @param notification
     */
    public static void push(HttpSession session, Notification notification){
        List<Notification> queue = getNotifications(session);
        queue.add(notification);
    }

    /**
     * Creates or retrieves a list of
     * notifications from session
     * @return
     */
    public static List<Notification> getNotifications(HttpSession session){

        Object ob = session.getAttribute(SERVICE_VAR);
        List<Notification> list = null;
        if(ob != null){
            list = (List) ob;
        } else{
            list = new LinkedList<Notification>();
            session.setAttribute(SERVICE_VAR, list);
        }

        return list;
    }

    public static boolean haveNotifications(HttpSession session){
        return !getNotifications(session).isEmpty();
    }

}
