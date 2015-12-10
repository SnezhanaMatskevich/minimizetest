package by.bsuir.group172301.matskevich.tour.util.matrix;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Artur Buzov
 */
public class CustomerDaoMatrixList {

    
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
    
    private static final String selectHistory = "SELECT `id`, `count_of_rows`, `count_of_cols` FROM `web`.`matrix_info`;";

    /**
     *
     * @param connection
     * @throws SQLException
     */
    public CustomerDaoMatrixList(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public MatrixList selectMatrixList() throws SQLException {
        statement = connection.prepareStatement(selectHistory);
        result = statement.executeQuery();
        MatrixList matrixList = new MatrixList();

        while (result.next()) {
            matrixList.add(result.getInt("id"), result.getInt("count_of_rows"), result.getInt("count_of_cols"));
        }

        statement.close();
        result.close();
        return matrixList;
    }

}
