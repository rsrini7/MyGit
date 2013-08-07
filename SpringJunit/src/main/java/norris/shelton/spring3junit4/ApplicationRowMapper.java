package norris.shelton.spring3junit4;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Application row mapper
 * @author Norris Shelton
 */
public class ApplicationRowMapper implements RowMapper<Application> {
    /**
     * Implementations must implement this method to map each row of data in the ResultSet. This method should not call
     * <code>next()</code> on the ResultSet; it is only supposed to map values of the current row.
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row
     * @throws SQLException if a SQLException is encountered getting column values (that is, there's no need to catch
     *                      SQLException)
     */
    public Application mapRow(ResultSet rs, int rowNum)
    throws SQLException {
        Application application = new Application();
        application.setId(rs.getInt("id"));
        application.setName(rs.getString("name"));
        application.setDescription(rs.getString("description"));
        application.setEntityIds(rs.getString("entity_ids"));
        application.setAcronym(rs.getString("acronym"));
        application.setAka1(rs.getString("aka1"));
        application.setAka2(rs.getString("aka2"));
        application.setAka3(rs.getString("aka3"));
        application.setStatus(rs.getBoolean("status"));
        application.setHelpGroup(rs.getString("helpGroup"));
        application.setVersion(rs.getString("version"));
        application.setDateUpdated(rs.getDate("date_updated"));
        application.setVendor(rs.getString("vendor"));
        application.setDisplay(rs.getBoolean("display"));
        application.setApproved(rs.getBoolean("approved"));
        return application;
    }
}
