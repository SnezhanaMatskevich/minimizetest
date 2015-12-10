package by.bsuir.group172301.matskevich.tour.util.matrix;


import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Artur Buzov
 */
public class TagPrintAndEditMatrix extends SimpleTagSupport {

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
            ///jspOut.println("№ in data base: " + matrix.getId() + " <br>");
            jspOut.println("<div>");
            jspOut.println("Size");
            //jspOut.println("<input id=\"cell\" type=\"number\" name=\"rowsA\" size=\"1\" value=\"" + matrix.getRowsCount() + "\">");
            jspOut.println("<select id=\"id\" name=\"rowsA\">");
            jspOut.println("<option value=\"" + matrix.getRowsCount() + "\">" + matrix.getRowsCount() + "</option>");
            jspOut.println("</select>");
            //  <
            //jspOut.println("<input type=\"number\" name=\"rowsA\" size=\"1\" value=\"" + matrix.getRowsCount() + "\" disabled=\"true\" onMouseMove=PRF(T16)>");
            jspOut.println("x");
            //jspOut.println("<input id=\"cell\" type=\"number\" name=\"colsA\" size=\"1\" value=\"" + matrix.getColsCount() + "\" required step=\"any\" >");
            jspOut.println("<select id=\"id\" name=\"colsA\">");
            jspOut.println("<option value=\"" + matrix.getColsCount() + "\">" + matrix.getColsCount() + "</option>");
            jspOut.println("</select>");
            jspOut.println("Id");     
            //jspOut.println("<input id=\"cell\" type=\"number\" name=\"idA\" size=\"1\" value=\"" + matrix.getId() + "\" required step=\"any\">");
            jspOut.println("<select id=\"id\" name=\"idA\">");
            jspOut.println("<option value=\"" + matrix.getId() + "\">" + matrix.getId() + "</option>");
            jspOut.println("</select>");
            jspOut.println("</div>");
            jspOut.println("<br>");
            jspOut.println("<br>");
            jspOut.println("<table>");

            for (int i = 0; i < matrix.getRowsCount(); i++) {

                if (i == 0) {
                    jspOut.println("<tr>");
                    for (int j = 0; j < matrix.getColsCount(); j++) {
                        if (j == 0) {
                            jspOut.println("<td></td>");
                            jspOut.println("<td>" + (j + 1) + "</td>");
                        } else {
                            jspOut.println("<td>" + (j + 1) + "</td>");
                        }
                    }
                    jspOut.println("</tr>");
                }
                jspOut.println("<tr>");
                for (int j = 0; j < matrix.getColsCount(); j++) {
                    try {
                        if (j == 0) {
                            jspOut.println("<td>" + (i + 1) + "</td>");
                            jspOut.println("<td><input id=\"input\" size=1 type=\"number\" value=\"" + matrix.getValue(i, j) + "\" required step=\"any\"/ name=\"mA" + i + j + "\"" + "/></td>");
                        } else {
                            jspOut.println("<td><input id=\"input\" size=1 type=\"number\" value=\"" + matrix.getValue(i, j) + "\" required step=\"any\"/ name=\"mA" + i + j + "\"" + "/></td>");
                        }
                    } catch (MatrixIndexOutOfBoundsException ex) {
                        jspOut.println("Error print");
                    }
                }
                jspOut.println("</tr>");
            }
            jspOut.println("</table>");
            jspOut.println("<p><input type=\"submit\" id=\"Ok\" value=\"Ok!\"/></p>");
            jspOut.println(" <input type=\"hidden\" name=\"action\" value=\"edit\">");
        } else {
            jspOut.println("Error print");
        }
    }
}
