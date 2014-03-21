package edu.ucdavis.ss.lmsreports.data;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.ucdavis.ss.lmsreports.domain.Domain;
import edu.ucdavis.ss.lmsreports.rowmapper.DomainRowMapper;



@Repository("DomainDao")
@Transactional
public class DomainDaoImpl  implements DomainDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
  
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}






	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}






	@Override
	public List<Domain> getFullyQualifiedDomainForLoaction(String orgId) {
	       
			StringBuilder sql = new StringBuilder();
			sql.append(" with n( level, org_pk, org_name, FullyQualifiedName) as " );
			sql.append(" (select 0, org_pk, org_name, CAST('.'+ org.Org_name+ '.'  as varchar(MAX)) ");
			sql.append(" from org where Org_PK = " );
			sql.append(orgId);
			sql.append(" union all ");
			sql.append(" select n.level + 1, nplus1.org_pk, nplus1.org_name, CAST(n.FullyQualifiedName + '.' + nplus1.Org_Name + '.' as varchar(MAX)) ");
			sql.append(" from org as nplus1, n" );
			sql.append(" where n.org_pk = nplus1.org_prntorgfk)" );
			sql.append(" select Space(level*4) + n.org_name as padded_name,  level,  n.org_pk, n.org_name, n.level from n ");
			sql.append(" order by n.FullyQualifiedName");
						
			return jdbcTemplate.query(sql.toString(), new DomainRowMapper());
	}

	@Override
	public Domain getDomainByLMSKey(String lmsKey) {
	       
			StringBuilder sql = new StringBuilder();
			sql.append(" with n( level, org_pk, org_name, FullyQualifiedName) as " );
			sql.append(" (select 0, org_pk, org_name, CAST('.'+ org.Org_name+ '.'  as varchar(MAX)) ");
			sql.append(" from org where Org_PK = " );
			sql.append(lmsKey);
			sql.append(" union all ");
			sql.append(" select n.level + 1, nplus1.org_pk, nplus1.org_name, CAST(n.FullyQualifiedName + '.' + nplus1.Org_Name + '.' as varchar(MAX)) ");
			sql.append(" from org as nplus1, n" );
			sql.append(" where n.org_pk = nplus1.org_prntorgfk)" );
			sql.append(" select Space(level*4) + n.org_name as padded_name,  level,  n.org_pk, n.org_name, n.level from n ");
			sql.append(" order by n.FullyQualifiedName");
			
			List<Domain> domains = jdbcTemplate.query(sql.toString(), new DomainRowMapper());
			
			if(domains != null && domains.size() > 0)
				return domains.get(0);
			else
				return null;
	}


}
