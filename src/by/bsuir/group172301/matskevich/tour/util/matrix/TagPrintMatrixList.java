package by.bsuir.group172301.matskevich.tour.util.matrix;


import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagPrintMatrixList extends SimpleTagSupport {

    private MatrixList matrixList;

    public void setMatrixList(MatrixList matrixList) {
        this.matrixList = matrixList;
    }

    public MatrixList getMatrixList() {
        return matrixList;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter jspOut = getJspContext().getOut();
        if (matrixList != null) {
            //jspOut.println("<select name=\"idA\">");
            //jspOut.println("<option value=\"0\"></option>");
            for (int i = 0; i < matrixList.getSize(); i++) {
                jspOut.println("<option value=\"" + matrixList.getId(i) + "\"> id " + matrixList.getId(i) + " size " + matrixList.getRow(i) + "x" + matrixList.getCol(i) + "</option>");
            }
            //jspOut.println("</select>");
        } else {
            jspOut.println("Error print");
        }
    }
}
