package vttp2023.batch3.csf.day37.server.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.csf.day37.server.models.BookSummary;

@Repository
public class BookRepository {

	@Autowired
	private JdbcTemplate template;

	public static final String SQL_BOOK_SUMMARY = """
			select book_id, title from book2018 order by title limit ?
		""";

	public List<BookSummary> getBookSummary(int limit) {

		List<BookSummary> result = new LinkedList<>();
		SqlRowSet rs = template.queryForRowSet(SQL_BOOK_SUMMARY, limit);
		while (rs.next()) {
			BookSummary b = new BookSummary(rs.getString("book_id"), rs.getString("title"));
			result.add(b);
		}
		return result;
	}

}
