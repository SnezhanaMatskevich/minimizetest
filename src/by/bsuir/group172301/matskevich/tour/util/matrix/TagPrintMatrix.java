package by.bsuir.group172301.matskevich.tour.util.matrix;


import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Artur Buzov
 */
public class TagPrintMatrix extends SimpleTagSupport {

    private Matrix matrix;

    /**
     *
     * @return
     */
    public Matrix getMatrix() {
        return matrix;
    }

    /**
     *
     * @param matrix
     */
    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    /**
     *
     * @throws JspException
     * @throws IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter jspOut = getJspContext().getOut();
        if (matrix != null) {
         
            jspOut.println("<table class=\"order-table-result\" id=\"resultTr\" border=\"1\" cellspacing=\"0\">");

            for (int i = 0; i < matrix.getRowsCount(); i++) {
                if (i == 0) {
                    jspOut.println("<tr id=\"resultTr\">");
                    for (int j = 0; j < matrix.getColsCount(); j++) {
                        if (j == 0) {
                            jspOut.println("<td id=\"cell\"></td>");
                            jspOut.println("<td id=\"cell\">" + (j + 1) + "</td>");
                        } else {
                            jspOut.println("<td id=\"cell\">" + (j + 1) + "</td>");
                        }
                    }
                    jspOut.println("</tr>");
                }
                jspOut.println("<tr id=\"resultTr\">");
                for (int j = 0; j < matrix.getColsCount(); j++) {
                    try {
                        if (j == 0) {
                            jspOut.println("<td id=\"cell\">" + (i + 1) + "</td>");
                            jspOut.println("<td id=\"cell\" size=5>" + InitsializatorMatrix.roundNumber(matrix.getValue(i, j), 3) + "</td>");
                        } else {
                            jspOut.println("<td id=\"cell\" size=5>" + InitsializatorMatrix.roundNumber(matrix.getValue(i, j), 3) + "</td>");
                        }
                    } catch (MatrixIndexOutOfBoundsException ex) {
                        jspOut.println("Error print");
                    }
                }
                jspOut.println("</tr>");
            }
            jspOut.println("</table>");
        } else {
            jspOut.println("Error print");
        }
    }
}
