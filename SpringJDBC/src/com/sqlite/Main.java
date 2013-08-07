package com.sqlite;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

class Main {
  public static void main(String args[]) throws Exception {
    ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml", Main.class);
    DataSource dataSource = (DataSource) ac.getBean("dataSource");

    SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(dataSource);

    List<People> customers = jdbcTemplate.query("select * from people",
        ParameterizedBeanPropertyRowMapper.newInstance(People.class));
    System.out.println(customers);

    GetTableNames getTableNames = new GetTableNames();
    try {
        Object o = JdbcUtils.extractDatabaseMetaData(dataSource, getTableNames);
        System.out.println(o);
    }catch(MetaDataAccessException e) {
        System.out.println(e);
    }
    
    Object result = jdbcTemplate.getJdbcOperations().query("SELECT occupation FROM people",new ResultSetExtractor() {
		
		@Override
		public Object extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			List<String> l = new ArrayList<String>();
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=1;i<rsmd.getColumnCount()+1;i++){
				l.add(rsmd.getColumnName(i));
			}
			return l;
		}
	});  

    System.out.println(result);
    
  }
}

class People{
	private String name;
	private String occupation;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+" "+occupation;
	}
	
}

class Customer {
  private Long id;

  private String firstName;

  private String lastName;

  private Date lastLogin;

  private String comments;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Customer");
    sb.append("{id=").append(id);
    sb.append(", firstName='").append(firstName).append('\'');
    sb.append(", lastName='").append(lastName).append('\'');
    sb.append(", lastLogin=").append(lastLogin);
    sb.append(", comments='").append(comments).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
