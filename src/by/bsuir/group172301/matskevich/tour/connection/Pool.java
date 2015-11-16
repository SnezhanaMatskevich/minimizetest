package by.bsuir.group172301.matskevich.tour.connection;

import java.sql.Connection;

/**
 * object pool interface
 */
public interface Pool<T extends Connection>{
    /**
     * Retrieves one object from the pool
     * @return an object
     */
    public T getConnection();

    /**
     * Releases(returns) object to the pool
     * @param t
     */
    public void release(T t);
}
