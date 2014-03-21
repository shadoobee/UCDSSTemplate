package edu.ucdavis.ss.lmsreports.data;

import java.util.List;
import edu.ucdavis.ss.lmsreports.domain.Status;

public interface StatusDao {
	public List<Status> getAll();
}
