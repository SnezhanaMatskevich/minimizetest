package by.bsuir.group172301.matskevich.tour.dao;

import by.bsuir.group172301.matskevich.tour.entity.Entity;
import by.bsuir.group172301.matskevich.tour.exception.DAOLogicalException;
import by.bsuir.group172301.matskevich.tour.exception.DAOTechnicalException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract Data Access Object
 * @param <K>
 * @param <T>
 */
public abstract class AbstractDAO <K, T extends Entity> {
	protected final String NO_CONNECTION = "Unable to get connection with database";
	protected final String ENTITY_WAS_NOT_FOUND = "No entity with such id";
	protected final String INVALID_DATA = "Null or invalid parameter(s)";
    protected final String NO_ROWS_AFFECTED = "No rows affected";

	/**
	 * Find all entites
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract List<T> findAll() throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Find entity by id
	 * @param id
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract T findById(K id) throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Delete entity by id
	 * @param id
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract boolean delete(K id) throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Delete entity
	 * @param entity
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract boolean delete(T entity) throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Create entity
	 * @param entity
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract boolean create(T entity) throws DAOLogicalException, DAOTechnicalException;

	/**
	 * Update entity
	 * @param entity
	 * @return
	 * @throws DAOLogicalException
	 * @throws DAOTechnicalException
	 */
    public abstract boolean update(T entity) throws DAOLogicalException, DAOTechnicalException;

	public abstract T createEntity(ResultSet set) throws SQLException;
}