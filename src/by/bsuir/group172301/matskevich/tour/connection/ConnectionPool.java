package by.bsuir.group172301.matskevich.tour.connection;

import by.bsuir.group172301.matskevich.tour.exception.ConnectionPoolException;
import by.bsuir.group172301.matskevich.tour.resource.DBConfigurationManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Database connection pool
 */
public class ConnectionPool implements Pool<Connection>{

    /**
     * Singleton instace
     */
    private static ConnectionPool instance = null;
    /**
     * Concurrent fair lock
     */
    private static Lock lock = new ReentrantLock(true);

    /**
     * Pool size
     */
    private final int POOL_SIZE = 10;

    /**
     * Maximum waiting time
     */
    private final int MAX_WAITING_TIME = 2;

    /**
     * Database configuration manager
     */
    private final DBConfigurationManager config = DBConfigurationManager.INSTANCE;

    /**
     * Logger
     */
    private final Logger logger = Logger.getRootLogger();

    /**
     * Connections
     */
    private BlockingQueue<Connection> connections;

    private ConnectionPool() throws ConnectionPoolException{
        initConnections();
    }

    /**
     * Get single instance
     * @return
     */
    public static ConnectionPool getInstance() throws ConnectionPoolException{

        lock.lock();
        if (null == instance){
            instance = new ConnectionPool();
        }
        lock.unlock();

        return instance;
    }
    /**
     * Create and fill connection pool
     */
    private final void initConnections() throws ConnectionPoolException{
        connections = new LinkedBlockingQueue<Connection>(POOL_SIZE);

        String connectionUrl = config.getString(DBConfigurationManager.DATABASE_CONNECTION_URL);
        String username = config.getString(DBConfigurationManager.DATABASE_USERNAME);
        String password = config.getString(DBConfigurationManager.DATABASE_PASSWORD);
        String driverName = config.getString(DBConfigurationManager.DATABASE_DRIVER_NAME);

        try {
            Class.forName(driverName);
            for (int i = 0; i < POOL_SIZE; i++){
                Connection connection = DriverManager.getConnection(connectionUrl, username, password);
                connections.add(connection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);

        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * Get single connection from concurrent queue
     * @return connection to use
     */
    @Override
    public final Connection getConnection() {
        try {
            Connection connection = connections.poll(MAX_WAITING_TIME, TimeUnit.SECONDS);
            if (connection != null) {
                logger.info("Connection " + connection + " took from connection pool");
            } else {
                logger.error("Couldn't retrieve a connection from pool");
            }
            return connection;
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * Return connection
     * @param connection connection to return to the pool
     */
    public final void release(Connection connection) {
        if (connection != null) {
            try {
                connections.put(connection);
                logger.info("Connection " + connection + " returned to connection pool");
                logger.info("There are(is) " + (connections.size() - connections.remainingCapacity()) + " connection(s) in the pool.");
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * Retrieve pool size
     * @return
     */
    public final int getPoolSize(){
        return POOL_SIZE;
    }

    /**
     * Close all connection
     */
    public void shutDown(){

        Iterator<Connection> iterator = connections.iterator();
        while(iterator.hasNext()){
            Connection connection = iterator.next();
            try {
                // close connection
                connection.close();
                // remove it to prevent the use of closed connection
                iterator.remove();
            } catch (SQLException e) {
                logger.error("Couldn't close connection: " + e.getMessage());
            }
        }

        logger.info("Connection pool is shut down");
    }
}
