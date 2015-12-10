package by.bsuir.group172301.matskevich.tour.util.matrix;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Artur Buzov
 */
public class CustomerDaoMatrix extends CustomerDAOAbstract implements CustomerDAOInterface<Matrix> {

    private static final String insertMatrixInfoString = "INSERT INTO matrix_info (count_of_rows, count_of_cols) VALUES (?,?)";

    private static final String insertMatrixString = "INSERT INTO matrix (id, row_id, col_id, value) VALUES (?,?,?,?)";

    private static final String updateMatrixString = "UPDATE matrix SET value=? WHERE id=? AND row_id=? AND col_id=?";

    private static final String serializeResultString = "INSERT INTO matrix_history (data_time, matrix_first, matrix_second, matrix_result) VALUES (?,?,?,?)";

    private static final String selectColsString = "SELECT COUNT(*) FROM matrix WHERE id = ? AND row_id = 1;";

    private static final String selectRowsString = "SELECT COUNT(*) FROM matrix WHERE id = ?;";

    private static final String selectMatrixString = "SELECT * FROM matrix WHERE id = ? AND row_id = ?;";

    private static final String selectHistory = "SELECT id, data_time` FROM web.matrix_history;";

    private static final String deleteMatrixString = "DELETE FROM 'matrix_info` WHERE id = ?;";

    private static final String deleteOfOldResultsStringH2 = "DELETE FROM `matrix_history` WHERE `data_time` < timestampadd(day, -1, now());";

    private static final String deleteOfOldResultsStringMySQl = "DELETE FROM `matrix_history` WHERE `data_time` < (NOW() - INTERVAL 1 DAY);";

   // private static final String driverName = DAODataBase.getDriverName();
    private static final String driverName = null;

    private static final String createBase = "CREATE TABLE IF NOT EXISTS matrix ( \n"
            + "matrix_id INTEGER NOT NULL , \n"
            + "row_id INTEGER NOT NULL , \n"
            + "col_id INTEGER NOT NULL , \n"
            + "value DOUBLE NOT NULL ); \n"
            + "\n"
            + "CREATE TABLE IF NOT EXISTS matrix_history ( \n"
            + "id INT PRIMARY KEY AUTO_INCREMENT , \n"
            + "data_time TIMESTAMP NOT NULL , \n"
            + "matrix_first LONGBLOB  NOT NULL , \n"
            + "matrix_second LONGBLOB  NOT NULL , \n"
            + "matrix_result LONGBLOB NOT NULL , \n"
            + ");";

    /**
     *
     * @param connection
     * @throws SQLException
     */
    public CustomerDaoMatrix(Connection connection) throws SQLException {
        super(connection);
        //initialize();
    }

    @Override
    public void insert(Matrix matrix) throws SQLException {

        int rows = matrix.getRowsCount();
        int cols = matrix.getColsCount();

        long startTime = System.currentTimeMillis();

        statement = connection.prepareStatement(insertMatrixInfoString);

        try {
            statement.setInt(1, rows);
            statement.setInt(2, cols);
            statement.addBatch();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
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
    }

    @Override
    public void insert(Matrix matrix, int matrix_id) throws SQLException {

        matrix.setId(matrix_id);

        deleteMatrix(matrix_id);

        int rows = matrix.getRowsCount();
        int cols = matrix.getColsCount();

        long startTime = System.currentTimeMillis();

        statement = connection.prepareStatement(insertMatrixInfoString);

        try {
            statement.setInt(1, rows);
            statement.setInt(2, cols);
            statement.addBatch();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        statement.executeBatch();

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
    }

    @Override
    public void update(Matrix matrix, int matrix_id) throws SQLException {
        matrix.setId(matrix_id);

        int rows = matrix.getRowsCount();
        int cols = matrix.getColsCount();

        long startTime = System.currentTimeMillis();

        statement = connection.prepareStatement(updateMatrixString);

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                try {
                    statement.setDouble(1, matrix.getValue(i - 1, j - 1));
                    statement.setInt(2, matrix_id);
                    statement.setInt(3, i);
                    statement.setInt(4, j);
                    
                    //statement.addBatch();
                    statement.executeUpdate();
                } catch (SQLException | MatrixIndexOutOfBoundsException ex) {
                    System.out.println("Error: " + ex);
                }
            }
        }

        //statement.executeBatch();
        System.out.println("executeBatch();executeBatch();executeBatch();executeBatch();executeBatch();executeBatch();executeBatch();executeBatch();executeBatch();");
        statement.close();
        //run time
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Recording of the data base lasted " + time + " ms.");
    }

    @Override
    public void serializeResult(Matrix matrixA, Matrix matrixB, Matrix matrixC) throws SQLException {
        deleteOfOldResults();
        long startTime = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

        statement = connection.prepareStatement(serializeResultString);

        statement.setTimestamp(1, ourJavaTimestampObject);
        statement.setObject(2, matrixA);
        statement.setObject(3, matrixB);
        statement.setObject(4, matrixC);
        statement.executeUpdate();

        statement.close();
        //run time
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        System.out.println("Recording of the data base lasted " + time + " ms.");
    }

    @Override
    public Matrix select(int matrix_id) throws SQLException {

        statement = connection.prepareStatement("SELECT `count_of_cols` FROM `matrix_info` WHERE id = ?;");
        statement.setInt(1, matrix_id);
        result = statement.executeQuery();

        int cols = 0;

        while (result.next()) {
            cols = result.getInt(1);
            //System.out.println("cols = " + cols);
        }

        int rows = 0;

        statement = connection.prepareStatement("SELECT `count_of_rows` FROM `matrix_info` WHERE id = ?;");
        statement.setInt(1, matrix_id);
        result = statement.executeQuery();

        while (result.next()) {
            rows = result.getInt(1); // cols;
            //System.out.println("rows = " + rows);
        }

        Matrix matrix = new MatrixDouble(rows, cols);
        statement = connection.prepareStatement(selectMatrixString);

        for (int i = 0; i < rows; i++) {
            int j = 0;
            statement.setInt(1, matrix_id);
            statement.setInt(2, (i + 1));

            result = statement.executeQuery();
            while (result.next()) {
                try {
                    matrix.setValue(i, j, result.getDouble("value"));
                } catch (MatrixIndexOutOfBoundsException ex) {
                    System.out.println("Error: " + ex);
                }
                j++;
            }
        }

        statement.close();
        result.close();
        return matrix;
    }

    public History selectHistory() throws SQLException {
        deleteOfOldResults();
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

    @Override
    public Matrix deserializeMatrixResult(int id, TypeMatrixResult typeMatrixResult) throws
            ClassNotFoundException, IOException, SQLException {

        String sqlDeserializeObject = null;

        switch (typeMatrixResult) {
            case MATRIX_FIRST:
                sqlDeserializeObject = "SELECT matrix_first FROM matrix_history WHERE id = ? ;";
                break;
            case MATRIX_SECOND:
                sqlDeserializeObject = "SELECT matrix_second FROM matrix_history WHERE id = ? ;";
                break;
            case MATRIX_RESULT:
                sqlDeserializeObject = "SELECT matrix_result FROM matrix_history WHERE id = ? ;";
                break;
            default:
        }

        statement = connection.prepareStatement(sqlDeserializeObject);
        statement.setInt(1, id);

        result = statement.executeQuery();

        result.next();

        InputStream inputStream = result.getBlob(1).getBinaryStream();
        ObjectInputStream oip = new ObjectInputStream(inputStream);
        Object deSerializedObject = oip.readObject();

        result.close();
        statement.close();

        return (Matrix) deSerializedObject;
    }

    @Override
    public void deleteAllMatrix() throws SQLException {
        statement = connection.prepareStatement("DELETE FROM matrix_info;");
        statement.execute();
        statement.close();
    }

    @Override
    public void deleteMatrix(int matrix_id) throws SQLException {
        statement = connection.prepareStatement(deleteMatrixString);
        statement.setInt(1, matrix_id);
        statement.execute();
        statement.close();
    }

    @Override
    public void deleteAllResult() throws SQLException {
        statement = connection.prepareStatement("DELETE FROM matrix_rusult;");
        statement.execute();
        statement.close();
    }

    @Override
    public void deleteOfOldResults() throws SQLException {
        switch (driverName) {
            case "org.h2.Driver":
                statement = connection.prepareStatement(deleteOfOldResultsStringH2);
                break;
            case "com.mysql.jdbc.Driver":
                statement = connection.prepareStatement(deleteOfOldResultsStringMySQl);
                break;
            default:
                System.out.println("Not true name of the driver.");
        }
        statement.execute();
        statement.close();
    }

    private void initialize() throws SQLException {

        statement = connection.prepareStatement(createBase);
        statement.execute();
    }

}
