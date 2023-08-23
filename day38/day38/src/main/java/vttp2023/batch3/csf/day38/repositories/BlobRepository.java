package vttp2023.batch3.csf.day38.repositories;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.sql.RowSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.csf.day38.models.UploadContent;

@Repository
public class BlobRepository {

	public static final String SQL_INSERT_INTO_UPLOADS = """
		insert into uploads(description, media_type, content) 
			values (?, ?, ?)
	""";

	public static final String SQL_SELECT_UPLOADS_BY_ID = """
		select * from uploads where id = ?
	""";

	@Autowired
	private JdbcTemplate template;

	public Optional<UploadContent> selectUploadsById(Integer id) {

		List<UploadContent> result = template.query(SQL_SELECT_UPLOADS_BY_ID,
			(ResultSet rs) -> {
				List<UploadContent> results = new LinkedList<>();
				while (rs.next()) {
					UploadContent uc = new UploadContent(
							id, rs.getString("description"),
							rs.getString("media_type"),
							rs.getBytes("content"));
					// Forgot to add record to list
					results.add(uc);
				}
				return results;
			},
			id
		);

		if (result.isEmpty())
			return Optional.empty();

		return Optional.of(result.get(0));
	}

	public void upload(String description, String mediaType, InputStream is) {

		template.update(SQL_INSERT_INTO_UPLOADS, description, mediaType, is);
	}
}
