package by.bsuir.group172301.matskevich.tour.util.matrix;

/**
 * Signals about that the index of the matrix out of bounds.
 * 
 * @author Artur Buzov
 */
public class MatrixIndexOutOfBoundsException extends Exception {

    /**
     *
     */
    public MatrixIndexOutOfBoundsException() {
        super();
    }
    
    /**
     *
     * @param message the detail message.
     */
    public MatrixIndexOutOfBoundsException(String message) {
        super(message);
    }
}
