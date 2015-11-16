package by.bsuir.group172301.matskevich.tour.dao;

import by.bsuir.group172301.matskevich.tour.connection.ConnectionPool;
import by.bsuir.group172301.matskevich.tour.entity.Role;
import by.bsuir.group172301.matskevich.tour.entity.User;
import by.bsuir.group172301.matskevich.tour.exception.ConnectionPoolException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class UserDAO extends AbstractDAO<Integer, User>{

    private static final String SELECT_ALL = "SELECT user.id, user.username, user.password, user.role_id, role.rolename FROM user JOIN role ON user.role_id = role.id";
    private static final String FIND_BY_ID = "SELECT user.id, user.username, user.password, user.role_id, role.rolename FROM user JOIN role ON user.role_id = role.id WHERE user.id = ?";
    private static final String FIND_BY_LOGIN_PASSWORD = "SELECT user.id, user.username, user.password, user.role_id, role.rolename FROM user JOIN role ON user.role_id = role.id WHERE user.username = ? AND user.password = ?";
    private static final String DELETE_BY_ID = "DELETE user WHERE id = ?";
    private static final String CREATE_USER = "INSERT INTO user (username, password,firstname,lastname, date_of_birth,email,full_adress, role_id) VALUES(?, ?, ?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, role_id = ? WHERE id = ?";
	private static final String USER_NOT_FOUND = "User not found";
	private static final Logger logger = Logger.getRootLogger();
        
        private static UserDAO instance;
        public static UserDAO getInstance(){
            if (instance == null){
            instance = new UserDAO();
        }
        return instance;
    }
	/**
     * Find all users
     * @return
     * @throws DAOLogicalException
     * @throws DAOTechnicalException
     */
    @Override
    public List<User> findAll() throws DAOLogicalException, DAOTechnicalException {

        ConnectionPool connectionPool = null;
        try{
            connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e){
            throw new DAOTechnicalException(e);
        }

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;

        LinkedList<User> users = new LinkedList<User>();

        if (connection != null){
            try {
                statement = connection.prepareStatement(SELECT_ALL);
                ResultSet set = statement.executeQuery();

                while(set.next()){

                    User user = createEntity(set);
                    users.add(user);
                }

                return users;
            } catch (SQLException e) {
                throw new DAOTechnicalException(e);
            } finally {
                if (null != statement) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        logger.error(e.getMessage());
                    }
                }
                connectionPool.release(connection);
            }
        } else{
            throw new DAOTechnicalException(NO_CONNECTION);
        }
    }

    /**
     * Find a user by id
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) throws DAOLogicalException, DAOTechnicalException {

        User user = null;

        if (id != null){
            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
            if (connection != null) {
                try{
                    statement = connection.prepareStatement(FIND_BY_ID);
                    statement.setInt(1, id);

                    ResultSet set = statement.executeQuery();

                    if (set.next()){
                        user = createEntity(set);
                    } else {
						throw new DAOLogicalException(USER_NOT_FOUND);
					}

                } catch (SQLException e){
                    throw new DAOTechnicalException(e);
                } finally {
                    if (null != statement) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    connectionPool.release(connection);
                }
            } else{
                throw new DAOTechnicalException(NO_CONNECTION);
            }
        }

        return user;
    }

    /**
     * Find user by username and hashed password
     * @param login
     * @param password
     * @return
     */
    public User findByLoginAndPassword(String login, String password) throws DAOLogicalException, DAOTechnicalException{
        User user = null;

        if(login != null && password != null){
            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }
            Connection connection = connectionPool.getConnection();

            PreparedStatement statement = null;

            if (connection != null) {
                try {
                    statement = connection.prepareStatement(FIND_BY_LOGIN_PASSWORD);
                    statement.setString(1, login);
                    statement.setString(2, password);

                    ResultSet resultSet = statement.executeQuery();

                    if(resultSet.next()){
                        user = createEntity(resultSet);
                    } else{
						throw new DAOLogicalException(USER_NOT_FOUND);
					}

                } catch (SQLException e) {
                    throw new DAOTechnicalException(e);
                } finally {
                    if (null != statement) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    connectionPool.release(connection);
                }
            } else{
                throw new DAOTechnicalException(NO_CONNECTION);
            }
        }
        return user;
    }

    /**
     * Delete user by id
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) throws DAOLogicalException, DAOTechnicalException {

        if (null != id) {

            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
            if (connection != null) {
                try {
                    statement = connection.prepareStatement(DELETE_BY_ID);
                    statement.setInt(1, id);
                    int affected = statement.executeUpdate();
                    return (affected > 0);

                } catch (SQLException e) {
                    throw new DAOTechnicalException(e);
                } finally {
                    if (null != statement) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    connectionPool.release(connection);
                }
            } else{
                throw new DAOTechnicalException(NO_CONNECTION);
            }
        } else{
            throw new DAOLogicalException(INVALID_DATA);
        }
    }

    /**
     * Delete user
     *
     * @param entity
     * @return
     */
    @Override
    public boolean delete(User entity) throws DAOLogicalException, DAOTechnicalException {
        if (entity != null){
             return delete(entity.getId());
        } else{
            throw new DAOLogicalException(INVALID_DATA);
        }
    }

    /**
     * Create user
     *
     * @param entity
     * @return
     */
    @Override
    public boolean create(User entity) throws DAOLogicalException, DAOTechnicalException {
        if (entity != null){
            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e){
                throw new DAOTechnicalException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
            if (connection != null){
                try {
                    statement = connection.prepareStatement(CREATE_USER);
                    statement.setString(1, entity.getUsername());
                    statement.setString(2, entity.getPassword());
                    statement.setString(3, entity.getFirstName());
                    statement.setString(4, entity.getLastName());
                    statement.setString(5, entity.getDateOfBirth());
                    statement.setString(6, entity.getEmail());
                    statement.setString(7, entity.getFullAddress());
                    statement.setInt(8, 1);
                    int affected = statement.executeUpdate();
                    if (affected > 0){
                        return true;
                    } else{
                        throw new DAOLogicalException(NO_ROWS_AFFECTED);
                    }

                } catch (SQLException e) {
                    throw new DAOTechnicalException(e.getMessage());

                } finally {
                    if (null != statement) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    connectionPool.release(connection);
                }
            } else{
                throw new DAOTechnicalException(NO_CONNECTION);
            }
        } else{
            throw new DAOLogicalException(INVALID_DATA);
        }
    }

    /**
     * Update user
     *
     *
     *
     * @param entity
     * @return
     */
    @Override
    public boolean update(User entity) throws DAOLogicalException, DAOTechnicalException {


        if (entity != null){
            ConnectionPool connectionPool = null;
            try {
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e) {
                throw new DAOTechnicalException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;

            if (connection != null){
                try {
                    statement = connection.prepareStatement(UPDATE_USER);
                    statement.setString(1, entity.getUsername());
                    statement.setString(2, entity.getPassword());
					statement.setInt(3, entity.getRole().getId());
                    statement.setInt(4, entity.getId());

                    int affected = statement.executeUpdate();

                    if (affected > 0){
                        return true;
                    } else{
                        throw new DAOLogicalException(NO_ROWS_AFFECTED);
                    }

                } catch (SQLException e) {
                    throw new DAOTechnicalException(e);
                } finally {
                    if (null != statement){
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }

                        connectionPool.release(connection);
                    }
                }

            } else{
                throw new DAOTechnicalException(NO_CONNECTION);
            }
        } else{
            throw new DAOLogicalException(NO_ROWS_AFFECTED);
        }
    }

    /**
     * Create user from result set
     * @param set
     * @return
     * @throws SQLException
     */
	@Override
    public User createEntity(ResultSet set) throws SQLException{

        User user = new User();
        user.setId(set.getInt("id"));
        user.setUsername(set.getString("username"));
        user.setPassword(set.getString("password"));

		Role role = new Role();
		role.setId (set.getInt("role_id"));
		role.setRolename(set.getString("rolename"));

		user.setRole(role);

        return user;
    }
}
