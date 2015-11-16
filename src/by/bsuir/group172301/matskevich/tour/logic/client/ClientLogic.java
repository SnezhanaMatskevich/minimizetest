package by.bsuir.group172301.matskevich.tour.logic.client;




import by.bsuir.group172301.matskevich.tour.connection.ConnectionPool;
import by.bsuir.group172301.matskevich.tour.entity.Role;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.ConnectionPoolException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;


/**
 *
 */
public class ClientLogic {

    private static final String SQL_QUERY = "SELECT client_info.is_regular FROM user  LEFT JOIN client_info ON user.id = client_info.user_id WHERE user.id = ?";
    private static final String SQL_ERROR = "Sql error";
    private static final String NO_CONNECTION_MESSAGE = "Unable to get connection";

    public static boolean isRegularClient(User user) throws DAOTechnicalException, DAOLogicalException{
        if (user != null){
            if (user.getRole().getRolename().equals(Role.ROLE_CLIENT)){
                return isRegular(user.getId());
            }
            return false;
        }
        return false;
    }


    private static boolean isRegular(Integer id) throws DAOLogicalException, DAOTechnicalException{
        if (id != null){
            Logger logger = Logger.getRootLogger();

            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = null;

            if (connection != null) {
                try {
                    statement = connection.prepareStatement(SQL_QUERY);
                    statement.setInt(1, id);

                    ResultSet resultSet = statement.executeQuery();

                    if(resultSet.next()){
                        return resultSet.getBoolean("is_regular");
                    } else{
                        throw new DAOLogicalException(SQL_ERROR);
                    }

                } catch (SQLException e) {
                    throw new DAOTechnicalException(e);
                } finally {
                    if (null != statement) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    connectionPool.release(connection);
                }
            } else{
                throw new DAOTechnicalException(NO_CONNECTION_MESSAGE);
            }

        }

        return false;
    }
}
