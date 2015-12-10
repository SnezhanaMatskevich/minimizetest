package by.bsuir.group172301.matskevich.tour.util.matrix;

/**
 * Signals about that data type of the matrix are not correct.
 * 
 * @author Artur Buzov
 */
public class IncorrectFormatOfData extends Exception {

    /**
     *
     */
    public IncorrectFormatOfData() {
        super();
    }

    /**
     *
     * @param message the detail message.
     */
    public IncorrectFormatOfData(String message) {
        super(message);
    }
}
