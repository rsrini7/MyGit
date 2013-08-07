package com.sqlite;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.support.DatabaseMetaDataCallback;

class GetTableNames implements DatabaseMetaDataCallback {

public Object processMetaData(DatabaseMetaData dbmd) throws SQLException {
    //ResultSet rs  = dbmd.getTables(dbmd.getUserName(),null,null,new String[]{("TABLE")});
    ResultSet rs = dbmd.getColumns(null,null,"PEOPLE",null);
    List<String> l = new ArrayList<String>();
    while (rs.next()) {
    	l.add(rs.getString("COLUMN_NAME"));
    }
    return l;
	}
}