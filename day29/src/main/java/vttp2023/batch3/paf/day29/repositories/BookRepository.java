package vttp2023.batch3.paf.day29.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

	public static final String SQL_GET_ALL_TITLES = """
		select title from book2018 order by title
	""";

	@Autowired
	private JdbcTemplate template;

	public List<String> getAllTitles() {
		SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_TITLES);
		List<String> results = new LinkedList<>();
		while (rs.next())
			results.add(rs.getString("title"));
		return results;
	}

}
