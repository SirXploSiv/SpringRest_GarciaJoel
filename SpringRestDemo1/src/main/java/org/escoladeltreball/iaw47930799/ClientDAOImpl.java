package org.escoladeltreball.iaw47930799;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientDAOImpl implements ClientDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public ClientDAOImpl (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Client findById(long clientId) {
		String sql = "SELECT * FROM clients WHERE id = " + clientId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Client>(){
			@Override
			public Client extractData(ResultSet rs) throws SQLException, DataAccessException {
				return (rs.next()) ? new Client(rs.getInt("id"),rs.getString("name")) : null;
			}			
		});
	}

	@Override
	public Client findByName(String clientName) {
		String sql = "SELECT * FROM clients WHERE name = \"" + clientName+"\"";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Client>(){
			@Override
			public Client extractData(ResultSet rs) throws SQLException, DataAccessException {
				return (rs.next()) ? new Client(rs.getInt("id"),rs.getString("name")) : null;
			}			
		});
	}

	@Override
	public List<Client> findAll() {
		 String sql = "SELECT * FROM clients";
		 List<Client> listClient = jdbcTemplate.query(sql, new RowMapper<Client>(){
			@Override
			public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Client(rs.getInt("id"),rs.getString("name"));
			}			 
		 });
		 return listClient;
	}

	@Override
	public void insertOrUpdateClient(Client client) {
		if (client.getId() > 0) {
			//Update
			String sql = "UPDATE clients SET name=? WHERE id=?";
			jdbcTemplate.update(sql,client.getName(),client.getId());
		} else {
			//Insert
			String sql = "INSERT INTO clients (name) VALUES (?)";
			jdbcTemplate.update(sql,client.getName());
		}
	}

	@Override
	public void deleteClient(long clientId) {
		String sql = "DELETE FROM clients WHERE id=?";
	    jdbcTemplate.update(sql, clientId);
	}

}
