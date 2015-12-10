package by.bsuir.group172301.matskevich.tour.util.matrix;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artur Buzov
 */
public class History {

    private List<Integer> id = new ArrayList<>();
    private List<Timestamp> time = new ArrayList<>();

    public Integer getId(int number) {
        return id.get(number);
    }

    public void addLine(Integer idValue, Timestamp timeValue) {
        id.add(idValue);
        time.add(timeValue);
    }

    public Timestamp getTime(int number) {
        return time.get(number);
    }

    public int getSize() {
        return id.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(getId(i));
            sb.append(" Data ");
            sb.append(getTime(i));
            sb.append("\n");
        }
        return sb.toString();
    }

}
