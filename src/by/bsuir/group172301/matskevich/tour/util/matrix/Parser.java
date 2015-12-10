package by.bsuir.group172301.matskevich.tour.util.matrix;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Parser {

    private static final Logger LOGER = LogManager.getLogger(Parser.class);

    private static final String ACTION_MULTIPLY = "multiplyPage";
    private static final String MULTIPLY = "multiply";

    private static final String ACTION_INPUT_AND_MULTIPLY = "inputAndMultiplyPage";
    private static final String INPUT_AND_MULTIPLY = "inputAndMultiply";

    private static final String ACTION_ADD_MATRIX = "addMatrixPage";
    private static final String ADD_MATRIX = "addMatrix";

    private static final String ACTION_EDIT_MATRIX = "editMatrixPage";
    private static final String EDIT_MATRIX = "editMatrix";
    private static final String EDIT = "edit";

    private static final String ACTION_DELETE_MATRIX = "deleteMatrixPage";
    private static final String DELETE_MATRIX = "deleteMatrix";

    private static final String ACTION_PRINT_HISTORY_JSP = "historyPage";

    private static final String ACTION_RESULT = "result";

    private static final String MATRIX_HISTORY = "matrix_history";
    
    private static final String ACTION_RESULT_MULTIPLY = "result_multiply";

   // private static final DAODataBase FACTORY = new DAODataBase();
    private static final DAODataBase FACTORY = null;

    private static final CustomerDaoMatrix MATRIX_TABLE = FACTORY.getCustomerDAO();
    private static final CustomerDaoHistory HISTORY_TABLE = FACTORY.getCustomerHistoryDAO();
    private static final CustomerDaoMatrixList MATRIX_LIST_TABLE = FACTORY.getCustomerMatrixListDAO();

    public static String getPageSetAttributeByParameter(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");

        if (action == null) {
            //System.out.println("homePage");
            return Pages.INDEX;
        }
        LOGER.info(action);
        switch (action) {
            case ACTION_MULTIPLY:
                //System.out.println("multiplyPages");
                printMatrixList(request);
                return Pages.MULTIPLY;
            case MULTIPLY:
                //System.out.println("multiply");
                multiply(request);
                return Pages.RESULT;

            case ACTION_INPUT_AND_MULTIPLY:
                //System.out.println("inputAndMultiplyPages");
                return Pages.INPUT_AND_MULTIPLY;
            case INPUT_AND_MULTIPLY:
                //System.out.println("inputAndMultiply");
                inputAndMultiply(request);
                return Pages.RESULT;

            case ACTION_ADD_MATRIX:
                //System.out.println("addMatrixPages");
                return Pages.ADD_MATRIX;
            case ADD_MATRIX:
                System.out.println("addMatrix");
                addMatrix(request);
                return Pages.RESULT;

            case ACTION_EDIT_MATRIX:
                //System.out.println("editMatrixPage");
                printMatrixList(request);
                return Pages.EDIT_MATRIX;
            case EDIT_MATRIX:
                //System.out.println("editMatrix");
                //printMatrixList(request);
                editMatrix(request);
                return Pages.RESUKLT_EDIT;
            case EDIT:
                //System.out.println("edit");
                //printMatrixList(request);
                edit(request);
                printMatrixList(request);
                return Pages.EDIT_MATRIX;

            case ACTION_DELETE_MATRIX:
                //System.out.println("deleteMatrixPage");
                printMatrixList(request);
                return Pages.DELETE_MATRIX;
            case DELETE_MATRIX:
                //System.out.println("deleteMatrix");
                delete(request);
                printMatrixList(request);
                return Pages.DELETE_MATRIX;

            case ACTION_RESULT:
                return Pages.RESULT;

            case ACTION_PRINT_HISTORY_JSP:
                printHistory(request);
                return Pages.PRINT_ALL_HISTORY_JSP;

            case MATRIX_HISTORY:             
                getMatrixFromHistory(request);
                return Pages.RESULT;
                
            case ACTION_RESULT_MULTIPLY:
                System.out.println("actionResultMultiply");
                getMatrix(request);
                return Pages.RESULT_MULTIPLY;    
        }
        return Pages.ERROR;
    }

    private static void delete(HttpServletRequest request) {
        try {
            String idAString = request.getParameter("idA");
            System.out.println("String idAString = " + idAString);
            int idA = 0;
            try {
                idA = Integer.parseInt(idAString);
            } catch (Exception e) {
                request.setAttribute("message", "Input error.");
            }
            MATRIX_TABLE.deleteMatrix(idA);
            request.setAttribute("message", "Operation is completed.");
        } catch (SQLException ex) {
            request.setAttribute("message", "Input error.");
            LOGER.error("Delete error", ex);
        }
    }

    private static void editMatrix(HttpServletRequest request) {

        String idAString = request.getParameter("idEdit");
        //System.out.println("String idAString = " + idAString);

        int idA = 0;

        try {
            idA = Integer.parseInt(idAString);
        } catch (Exception ex) {
            request.setAttribute("message", "Input error.");
            LOGER.error("Edit error", ex);
        }

        Matrix matrixA = null;

        try {

            matrixA = MATRIX_TABLE.select(idA);
            matrixA.setId(idA);
            request.setAttribute("result", matrixA);
        } catch (SQLException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Matrix select error", ex);
        }
    }

    private static void edit(HttpServletRequest request) {
        String rowsAString = request.getParameter("rowsA");
        System.out.println("String rowsAString = " + rowsAString);
        String colsAString = request.getParameter("colsA");
        System.out.println("String colsAString = " + colsAString);
        String idAString = request.getParameter("idA");
        System.out.println("String idAString = " + idAString);

        int rowsA = 0;
        int colsA = 0;
        int idA = 0;

        try {
            rowsA = Integer.parseInt(rowsAString);
            colsA = Integer.parseInt(colsAString);
            idA = Integer.parseInt(idAString);
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "Input error.");
            LOGER.error("Edit error", ex);
        }

        Matrix matrixA = new MatrixDouble(rowsA, colsA);
        String tempString = null;
        String nameTempString = null;
        double temp = 0;

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                nameTempString = "mA" + i + "" + j;
                //System.out.println("nameTempString   = " + nameTempString);
                tempString = request.getParameter(nameTempString);
                //System.out.println("tempString  = " + tempString);
                temp = Double.parseDouble(tempString);
                try {
                    matrixA.setValue(i, j, temp);
                } catch (MatrixIndexOutOfBoundsException ex) {
                    request.setAttribute("error", "Input error.");
                    LOGER.error("Matrix edit error", ex);
                }
            }
        }

        matrixA.print();

        try {
            MATRIX_TABLE.update(matrixA, idA);
            request.setAttribute("message", "Operation is completed.");
        } catch (NumberFormatException | SQLException ex) {
            request.setAttribute("message", "Input error.");
            LOGER.error("Insert matrix error", ex);
        }
    }

    private static void addMatrix(HttpServletRequest request) {
        String rowsAString = request.getParameter("rowsA");
        //System.out.println("String rowsAString = " + rowsAString);
        String colsAString = request.getParameter("colsA");
        //System.out.println("String colsAString = " + colsAString);
        String idAString = request.getParameter("idA");
        //System.out.println("String idAString = " + idAString);

        int rowsA = 0;
        int colsA = 0;
        int idA = 0;

        try {
            rowsA = Integer.parseInt(rowsAString);
            colsA = Integer.parseInt(colsAString);
            idA = Integer.parseInt(idAString);
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "Input error.");
            LOGER.error("parseInt error", ex);
        }

        Matrix matrixA = new MatrixInteger(rowsA, colsA);
        String tempString = null;
        String nameTempString = null;
        double temp = 0;

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                nameTempString = "mA" + i + "" + j;
                //System.out.println("nameTempString   = " + nameTempString);
                tempString = request.getParameter(nameTempString);
                //System.out.println("tempString  = " + tempString);
                temp = Integer.parseInt(tempString);
                try {
                    matrixA.setValue(i, j, temp);
                } catch (MatrixIndexOutOfBoundsException ex) {
                    request.setAttribute("error", "Input error.");
                    LOGER.error("read matrix error", ex);
                }
            }
        }

        matrixA.print();

        try {
            request.setAttribute("result", matrixA);
            MATRIX_TABLE.insert(matrixA);
        } catch (NumberFormatException | SQLException ex) {
            request.setAttribute("message", "Input error.");
            LOGER.error("insert matrix error", ex);
        }
    }

    private static void inputAndMultiply(HttpServletRequest request) {
        String rowsAString = request.getParameter("rowsA");
        //System.out.println("String rowsAString = " + rowsAString);
        String colsAString = request.getParameter("colsA");
        //System.out.println("String colsAString = " + colsAString);
        String rowsBString = request.getParameter("rowsB");
        //System.out.println("String rowsBString = " + rowsBString);
        String colsBString = request.getParameter("colsB");
        //System.out.println("String colsBString = " + colsBString);

        int rowsA = 0;
        int colsA = 0;
        int rowsB = 0;
        int colsB = 0;

        try {
            rowsA = Integer.parseInt(rowsAString);
            colsA = Integer.parseInt(colsAString);
            rowsB = Integer.parseInt(rowsBString);
            colsB = Integer.parseInt(colsBString);
        } catch (Exception ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("parseInt error", ex);
        }

        Matrix matrixA = new MatrixDouble(rowsA, colsA);
        Matrix matrixB = new MatrixDouble(rowsB, colsB);
        String tempString = null;
        String nameTempString = null;
        double temp = 0;

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                nameTempString = "mA" + i + "" + j;
                //System.out.println("nameTempString   = " + nameTempString);
                tempString = request.getParameter(nameTempString);
                //System.out.println("tempString  = " + tempString);
                temp = Double.parseDouble(tempString);
                try {
                    matrixA.setValue(i, j, temp);
                } catch (MatrixIndexOutOfBoundsException ex) {
                    request.setAttribute("error", "Input error.");
                    LOGER.error("read matrix error", ex);
                }
            }
        }

        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                nameTempString = "mB" + i + "" + j;
                tempString = request.getParameter(nameTempString);
                temp = Double.parseDouble(tempString);
                try {
                    matrixB.setValue(i, j, temp);
                } catch (MatrixIndexOutOfBoundsException ex) {
                    request.setAttribute("error", "Input error.");
                    LOGER.error("read matrix error", ex);
                }
            }
        }

        Matrix matrixResult = null;

        try {
            matrixResult = matrixA.multiply(matrixB);
            request.setAttribute("result", matrixResult);
        } catch (IllegalSizesException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Illegal sizes error", ex);
        } catch (IncorrectFormatOfData | MatrixIndexOutOfBoundsException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Incorrect format of data error", ex);
        }

        try {
            MATRIX_TABLE.serializeResult(matrixA, matrixB, matrixResult);
        } catch (SQLException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("serialize result error", ex);
        }

    }

    private static void multiply(HttpServletRequest request) {
        String firstMatrixIdString = request.getParameter("firstMatrix");
        String secondMatrixIdString = request.getParameter("secondMatrix");

        //System.out.println("String firstMatrixId = " + firstMatrixIdString);
        //System.out.println("String secondMatrixId = " + secondMatrixIdString);
        if (firstMatrixIdString == null || secondMatrixIdString == null) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Input error.");
        } else if (firstMatrixIdString.length() == 0 || secondMatrixIdString.length() == 0) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Input error.");
        }

        int firstMatrixId = 0;
        int secondMatrixId = 0;

        try {
            firstMatrixId = Integer.parseInt(firstMatrixIdString);
            secondMatrixId = Integer.parseInt(secondMatrixIdString);
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("parseInt error.", ex);
        }

        Matrix matrixA = null;
        Matrix matrixB = null;

        try {
            matrixA = MATRIX_TABLE.select(firstMatrixId);
            matrixB = MATRIX_TABLE.select(secondMatrixId);
        } catch (SQLException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Select matrix error.", ex);
        }

        Matrix matrixResult = null;

        try {
            matrixResult = matrixA.multiply(matrixB);
            request.setAttribute("result", matrixResult);
        } catch (IllegalSizesException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Illegal sizes error", ex);
        } catch (IncorrectFormatOfData | MatrixIndexOutOfBoundsException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Incorrect format of data error", ex);
        }

        try {
            MATRIX_TABLE.serializeResult(matrixA, matrixB, matrixResult);
        } catch (SQLException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("serialize result error", ex);
        }
    }

    private static void printHistory(HttpServletRequest request) {
        History historyResult = null;
        try {
            historyResult = HISTORY_TABLE.selectHistory();
            request.setAttribute("result", historyResult);
        } catch (SQLException e) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Select history", e);
        }
    }

    private static void printMatrixList(HttpServletRequest request) {
        MatrixList matrixList = null;
        try {
            matrixList = MATRIX_LIST_TABLE.selectMatrixList();
            //System.out.println(matrixList);
            request.setAttribute("result", matrixList);
        } catch (SQLException e) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Select history", e);
        }
    }
    
    private static void getMatrixFromHistory(HttpServletRequest request) {

        String idString = request.getParameter("id");
        //System.out.println("String idString = " + idString);

        int id = 0;

        try {
            id = Integer.parseInt(idString);
        } catch (Exception ex) {
            request.setAttribute("message", "Input error.");
            LOGER.error("Edit error", ex);
        }
        
        String matrixId = request.getParameter("matrix_id");
        TypeMatrixResult typeMatrix = null;
        
        switch (matrixId) {
            case "1":
                typeMatrix= TypeMatrixResult.MATRIX_FIRST;
                break;
            case "2":
                typeMatrix= TypeMatrixResult.MATRIX_SECOND;
                break;
            case "3":
                typeMatrix= TypeMatrixResult.MATRIX_RESULT;
                break;
            default:
        }

        Matrix matrix = null;

        try {

            matrix = MATRIX_TABLE.deserializeMatrixResult(id, typeMatrix);
            request.setAttribute("result", matrix);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Matrix select error", ex);
        }
    }
    
    private static void getMatrix(HttpServletRequest request) {

        String idString = request.getParameter("matrix_id");
        //System.out.println("String idString = " + idString);

        int id = 0;

        try {
            id = Integer.parseInt(idString);
        } catch (Exception ex) {
            request.setAttribute("message", "Input error.");
            LOGER.error("Edit error", ex);
        }
        

        Matrix matrix = null;

        try {

            matrix = MATRIX_TABLE.select(id);
            matrix.setId(id);
            request.setAttribute("result", matrix);
        } catch (SQLException ex) {
            request.setAttribute("error", "Input error.");
            LOGER.error("Matrix select error", ex);
        }
    }
}
