package edu.ucdavis.ss.lmsreports.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucdavis.ss.lmsreports.domain.Domain;

public class DomainRowMapper implements RowMapper<Domain> {

	@Override
	public Domain mapRow(ResultSet rs, int rowNum) throws SQLException {
		Domain domain = new Domain();
		domain.setDomainId(rs.getInt("org_pk"));
		domain.setDomainName(rs.getString("org_name"));
		domain.setLevel(rs.getInt("level"));
		domain.setDomainNamePadded(rs.getString("padded_name"));
		return domain;
	}

}
