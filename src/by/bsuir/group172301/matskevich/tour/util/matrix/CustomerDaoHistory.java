package by.bsuir.group172301.matskevich.tour.util.matrix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Artur Buzov
 */
public class CustomerDaoHistory {

    
    /**
     * Object of connection with a database.
     */
    Connection connection = null;
    /**
     * The object used for executing a static SQL statement and returning the results it produces.
     */
    PreparedStatement statement = null;
    /**
     * This object maintains a cursor pointing to its current row of data.
     */
    ResultSet result = null;
    
    private static final String selectHistory = "SELECT `id`, `data_time` FROM `web`.`matrix_history`;";

    /**
     *
     * @param connection
     * @throws SQLException
     */
    public CustomerDaoHistory(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public History selectHistory() throws SQLException {
        statement = connection.prepareStatement(selectHistory);
        result = statement.executeQuery();
        History history = new History();

        while (result.next()) {
            history.addLine(result.getInt("id"), result.getTimestamp("data_time"));
        }

        statement.close();
        result.close();
        return history;
    }

}
