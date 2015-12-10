package by.bsuir.group172301.matskevich.tour.util.matrix;


import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagPrintHistory extends SimpleTagSupport {

    private History history;

    public void setHistory(History history) {
        this.history = history;
    }

    public History getHistory() {
        return history;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter jspOut = getJspContext().getOut();
        if (history != null) {
            jspOut.println("<table border=\"1\" cellspacing=\"0\">");
            for (int i = 0; i < history.getSize(); i++) {
                //jspOut.println("<form name=\"username\" action=\"${pageContext.request.contextPath}/controller\" method=\"post\">");
                jspOut.println("<tr id=\"resultTr\">");
                jspOut.println("<td id=\"history_id\" size=5>" + (i+1) + "</td>");
                jspOut.println("<td id=\"history\" size=100>" + history.getTime(i) + "</td>");
                jspOut.println("<td id=\"history_matrix\" size=20> <a href=\"controller?action=matrix_history&id=" + history.getId(i) + "&matrix_id=1\">matrixA</a> </td>");
                jspOut.println("<td id=\"history_matrix\" size=20> <a href=\"controller?action=matrix_history&id=" + history.getId(i) + "&matrix_id=2\">matrixB</a> </td>");
                jspOut.println("<td id=\"history_matrix\" size=20> <a href=\"controller?action=matrix_history&id=" + history.getId(i) + "&matrix_id=3\">matrixC</a> </td>");
                jspOut.println("</tr>");
                //jspOut.println("</form>");
            }
            jspOut.println("</table>");
        } else {
            jspOut.println("Error print");
        }
    }
}
