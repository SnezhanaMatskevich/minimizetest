package by.bsuir.group172301.matskevich.tour.dao;

import by.bsuir.group172301.matskevich.tour.connection.ConnectionPool;
import by.bsuir.group172301.matskevich.tour.entity.Role;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.ConnectionPoolException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import by.bsuir.group172301.matskevich.tour.util.matrix.Matrix;
import by.bsuir.group172301.matskevich.tour.util.matrix.MatrixIndexOutOfBoundsException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class MatrixDAO {

    private static final String insertMatrixInfoString = "INSERT INTO matrix_info (count_of_rows, count_of_cols) VALUES (?,?)";

    private static final String insertMatrixString = "INSERT INTO matrix (matrix_id, row_id, col_id, value) VALUES (?,?,?,?)";

    private static final String updateMatrixString = "UPDATE matrix SET value=? WHERE id=? AND row_id=? AND col_id=?";

    private static final String serializeResultString = "INSERT INTO matrix_history (data_time, matrix_first) VALUES (?,?)";

    private static final String selectColsString = "SELECT COUNT(*) FROM matrix WHERE id = ? AND row_id = 1;";

    private static final String selectRowsString = "SELECT COUNT(*) FROM matrix WHERE id = ?;";

    private static final String selectMatrixString = "SELECT * FROM matrix WHERE id = ? AND row_id = ?;";

    private static final String selectHistory = "SELECT id, data_time` FROM web.matrix_history;";

    private static final String deleteMatrixString = "DELETE FROM 'matrix_info` WHERE id = ?;";

    private static final String deleteOfOldResultsStringH2 = "DELETE FROM `matrix_history` WHERE `data_time` < timestampadd(day, -1, now());";

    private static final String deleteOfOldResultsStringMySQl = "DELETE FROM `matrix_history` WHERE `data_time` < (NOW() - INTERVAL 1 DAY);";

    private static final Logger logger = Logger.getRootLogger();

    private static MatrixDAO instance;

    public static MatrixDAO getInstance() {
        if (instance == null) {
            instance = new MatrixDAO();
        }
        return instance;
    }

    public boolean create(Matrix matrix) throws DAOLogicalException, DAOTechnicalException, SQLException {

        int rows = matrix.getRowsCount();
        int cols = matrix.getColsCount();
        System.out.println("-----------------------");
        System.out.println(rows);
        System.out.println(cols);
        System.out.println("-----------------------");
        ResultSet result = null;
        long startTime = System.currentTimeMillis();
        if (matrix != null) {
            ConnectionPool connectionPool = null;
            try {
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e) {
                throw new DAOTechnicalException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement(insertMatrixInfoString);
            if (connection != null) {
                try {
                    statement.setInt(1, rows);
                    statement.setInt(2, cols);
                    statement.addBatch();

                    statement.executeBatch();
                    statement = connection.prepareStatement("SELECT MAX(id) FROM matrix_info");
                    result = statement.executeQuery();
                    //System.out.println("");

                    int matrix_id = 0;
                    while (result.next()) {
                        matrix_id = result.getInt(1);
                    }

                    //System.out.println("matrix_id = " + matrix_id);
                    matrix.setId(matrix_id);

                    statement = connection.prepareStatement(insertMatrixString);

                    for (int i = 1; i <= rows; i++) {
                        for (int j = 1; j <= cols; j++) {
                            try {

                                statement.setInt(1, matrix_id);

                                statement.setInt(2, i);
                                statement.setInt(3, j);
                                statement.setDouble(4, matrix.getValue(i - 1, j - 1));
                                statement.addBatch();
//statement.executeUpdate();
                            } catch (SQLException | MatrixIndexOutOfBoundsException ex) {
                                System.out.println("Error: " + ex);
                            }
                        }
                    }

                    statement.executeBatch();
                    statement.close();
                    //run time
                    long endTime = System.currentTimeMillis();
                    long time = endTime - startTime;
                    System.out.println("Recording of the data base lasted " + time + " ms.");
                } catch (SQLException e) {
                    throw new DAOTechnicalException(e.getMessage());

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
            } else {
                //   throw new DAOTechnicalException(NO_CONNECTION);
            }
        } else {
            //   throw new DAOLogicalException(INVALID_DATA);
        }
        return false;

    }

   
   

}
