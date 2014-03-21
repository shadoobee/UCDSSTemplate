package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Role;

public class RoleRowMapper implements RowMapper<Role> {
	
	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		role.setRoleID(rs.getInt("role_id"));
		role.setRoleName(rs.getString("role_name"));
		role.setRoleDesc(rs.getString("role_desc"));
		return role;
	}
}