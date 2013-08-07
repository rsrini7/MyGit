package norris.shelton.spring3junit4;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Data access object for the application data.  This is a spring @Repository class and will receive automatic exception
 * translation.
 * @author Norris Shelton
 * @see Repository
 * @see JdbcTemplate
 * @see NamedParameterJdbcTemplate
 */
@Repository
public class ApplicationDao {
    private static Logger log = LoggerFactory.getLogger(ApplicationDao.class);
    /** These are just the fields that would be inserted (e.g. no id). */
    private static final String SQL_INSERT_FIELDS =
        " name, description, entity_ids, acronym, aka1, aka2, aka3, status, helpGroup, version, date_updated, vendor, approved, display ";

    /** these are the fields that would be selected from. */
    private static final String SQL_FIELDS =
    " id, " + SQL_INSERT_FIELDS;

    /** SQL select statement. */
    private static final String SQL_SELECT_FROM =
        " SELECT " + SQL_FIELDS + " FROM dirApp ";

    /** SQL order by clause. */
    private static final String SQL_ORDER_BY = " ORDER BY status DESC, name ";

    /**
     * Inject the spring object with this name.  This is a jsr-250 annotation.  You can accomplish nearly the same thing
     * with @Autowired to find the objects of the correct type along with @Qualifier("name") to indicate which specific
     * bean you want.
     */
    @Resource(name = "bhsAuxCmsJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * Inject the spring object with this name.  This is a jsr-250 annotation.  You can accomplish nearly the same thing
     * with @Autowired to find the objects of the correct type along with @Qualifier("name") to indicate which specific
     * bean you want.
     */
    @Resource(name = "bhsAuxCmsNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Get an application by id.
     * @param id the application
     * @return the desired application
     * @see JdbcTemplate#queryForObject(String, Object[], RowMapper)   
     */
    public Application get(int id) {
        // use spring jdbc template to query database using a prepared statement and a user provided row mapper to
        // return a domain object
        return jdbcTemplate.queryForObject(SQL_SELECT_FROM + " WHERE id = ? " + SQL_ORDER_BY,
                                  new Integer[]{id},
                                  new ApplicationRowMapper());
    }

    /**
     * Get all applications.
     * @return list of application objects
     * @see JdbcTemplate#query(String, RowMapper) 
     */
    public List<Application> getAll() {
        // use spring jdbc template to query database using a user provided row mapper to return a list of domain
        // objects
        return jdbcTemplate.query(SQL_SELECT_FROM + SQL_ORDER_BY,
                                  new ApplicationRowMapper());
    }


    /**
     * Get all active applications.
     * @return list of active application objects
     * @see JdbcTemplate#query(String, RowMapper)
     */
    public List<Application> getDisplayable() {
        // use spring jdbc template to query database using a user provided row mapper to return a list of domain
        // objects
        return jdbcTemplate.query(SQL_SELECT_FROM + " WHERE display = 1 " + SQL_ORDER_BY,
                                  new ApplicationRowMapper());
    }

    /**
     * Get all active applications.
     * @return list of active application objects
     * @see JdbcTemplate#query(String, RowMapper)
     */
    public List<Application> getActive() {
        // use spring jdbc template to query database using a user provided row mapper to return a list of domain
        // objects
        return jdbcTemplate.query(SQL_SELECT_FROM + " WHERE status = 1 " + SQL_ORDER_BY,
                                  new ApplicationRowMapper());
    }

    /**
     * Creates a new application record.
     * @param application object to be inserted
     * @return the number of rows affected
     * @see NamedParameterJdbcTemplate#update(String, Map)
     * @see BeanPropertySqlParameterSource
     */
    public int insert(Application application) {
        // use spring named parameter jdbc template to insert data into the database using a bean property sql parameter
        // source to retrieve the property values from the domain object by bean property name
        return namedParameterJdbcTemplate.update("INSERT INTO dirApp (" + SQL_INSERT_FIELDS + ") " +
                                                 " values(:name, " +
                                                 "        :description, " +
                                                 "        :entityIds, " +
                                                 "        :acronym, " +
                                                 "        :aka1, " +
                                                 "        :aka2, " +
                                                 "        :aka3, " +
                                                 "        :status, " +
                                                 "        :helpGroup, " +
                                                 "        :version, " +
                                                 "        NOW(), " +
                                                 "        :vendor, " +
                                                 "        :approved, " +
                                                 "        :display) ",
                                                 new BeanPropertySqlParameterSource(application));
    }


    /**
     * Updates the given application object.
     * @param application object to be updated
     * @return the number of rows affected
     * @see NamedParameterJdbcTemplate#update(String, Map)
     * @see BeanPropertySqlParameterSource
     */
    public int update(Application application) {
        // use spring named parameter jdbc template to update the database using a bean property sql parameter source to
        // retrieve the property values from the domain object by bean property name
        return namedParameterJdbcTemplate.update("UPDATE dirApp SET name = :name, " +
                                                 "    description = :description, " +
                                                 "    entity_ids = :entityIds, " +
                                                 "    acronym = :acronym, " +
                                                 "    aka1 = :aka1, " +
                                                 "    aka2 = :aka2, " +
                                                 "    aka3 = :aka3, " +
                                                 "    status = :status, " +
                                                 "    helpGroup = :helpGroup, " +
                                                 "    version = :version, " +
                                                 "    date_updated = NOW(), " +
                                                 "    vendor = :vendor, " +
                                                 "    display = :display, " +
                                                 "    approved = :approved " +
                                                 " WHERE id = :id ",
                                                 new BeanPropertySqlParameterSource(application));
    }
}
