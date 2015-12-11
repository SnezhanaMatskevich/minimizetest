/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.group172301.matskevich.tour.dao;

import by.bsuir.group172301.matskevich.tour.connection.ConnectionPool;
import by.bsuir.group172301.matskevich.tour.entity.Report;
import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.entity.TourType;
import by.bsuir.group172301.matskevich.tour.exception.ConnectionPoolException;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Sniazhana_Matskevich
 */
public class ReporDAO extends AbstractDAO<Integer, Report> {

    private final String SELECT_ALL = "SELECT id, col1, col2, perc, report FROM percent";
    private final String FIND_BY_ID = "SELECT id, col1, col2, perc, report FROM percent WHERE id = ?";
    private final String CREATE_TOUR = "INSERT INTO tour(tourname, details, hot, price, regular_discount, type) VALUES(?, ?, ?, ?, ?, ?)";
    private final String UPDATE_BY_ID = "UPDATE tour SET tourname=?, details=?, hot=?, price=?, regular_discount=?, type=? WHERE id=?";
    private final String DELETE_TOUR_BY_ID = "DELETE FROM percent WHERE id = ?";
    private final String FIND_BY_NAME = "SELECT id, tourname, details, hot, price, regular_discount, type FROM tour WHERE tourname = ?";

    private static final Logger logger = Logger.getRootLogger();

    private ReporDAO() {
    }

    private static ReporDAO instance;

    public static ReporDAO getInstance() {
        if (instance == null) {
            instance = new ReporDAO();
        }
        return instance;
    }

    @Override
    public List<Report> findAll() throws DAOLogicalException, DAOTechnicalException {
        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DAOTechnicalException(e);
        }

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;

        LinkedList<Report> reports = new LinkedList<Report>();

        if (connection != null) {
            try {
                statement = connection.prepareStatement(SELECT_ALL);
                ResultSet set = statement.executeQuery();

                while (set.next()) {
                    Report rep = createEntity(set);
                    reports.add(rep);
                }

                return reports;
            } catch (SQLException e) {
                throw new DAOLogicalException(e);
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
        } else {
            throw new DAOTechnicalException(NO_CONNECTION);
        }
    }

 @Override
    public Report findById(Integer id) throws DAOLogicalException, DAOTechnicalException {
        if (id != null) {
            ConnectionPool pool = null;
            try {
                pool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e) {
                throw new DAOLogicalException(e);
            }

            Connection connection = pool.getConnection();
            PreparedStatement statement = null;

            if (connection != null) {
                try {
                    statement = connection.prepareStatement(FIND_BY_ID);
                    statement.setInt(1, id);

                    ResultSet set = statement.executeQuery();

                    if (set.next()) {
                        return createEntity(set);
                    } else {
                        throw new DAOLogicalException(ENTITY_WAS_NOT_FOUND);
                    }
                } catch (SQLException e) {
                    throw new DAOTechnicalException(e);
                } finally {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }
                        pool.release(connection);
                    }
                }

            } else {
                throw new DAOTechnicalException(NO_CONNECTION);
            }
        } else {
            throw new DAOLogicalException(INVALID_DATA);
        }
    }

 @Override
    public boolean delete(Integer id) throws DAOLogicalException, DAOTechnicalException {
        if (null != id) {

            ConnectionPool connectionPool = null;
            try {
                connectionPool = ConnectionPool.getInstance();
            } catch (ConnectionPoolException e) {
                throw new DAOTechnicalException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
            if (connection != null) {
                try {
                    statement = connection.prepareStatement(DELETE_TOUR_BY_ID);
                    statement.setInt(1, id);
                    int affected = statement.executeUpdate();
                    if (affected > 0) {
                        return true;
                    } else {
                        throw new DAOLogicalException("No such record in database");
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
            } else {
                throw new DAOTechnicalException(NO_CONNECTION);
            }
        } else {
            throw new DAOLogicalException(INVALID_DATA);
        }
    }

   
    @Override
    public boolean create(Report entity) throws DAOLogicalException, DAOTechnicalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Report entity) throws DAOLogicalException, DAOTechnicalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Report createEntity(ResultSet set) throws SQLException {
        Report tour = new Report();

        tour.setId(set.getInt("id"));
        tour.setCol1(set.getInt("col1"));

        

        tour.setCol2(set.getInt("col2"));
        tour.setPerc(set.getDouble("perc"));
        tour.setReport(set.getString("report"));

 

        return tour;
    }

    @Override
    public boolean delete(Report entity) throws DAOLogicalException, DAOTechnicalException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}