package vttp2023.batch3.paf.day27.respositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShowRepository {

	public static final String A_TYPE = "type";
	public static final String A_NAME = "name";
	public static final String A_OBJECTID = "_id";
	public static final String A_ID = "id";
	public static final String A_SUMMARY = "summary";
	public static final String A_IMAGE = "image.original";
	public static final String C_TVSHOW = "tvshow";

	public static final String SQL_FIND_NAME = "";
	public static final String SQL_FIND_SOMETHING_ELSE = "";

	@Autowired
	private MongoTemplate template;

	/*
	 * db.tvshow.distinct('type')
	 */
	public List<String> getAllTypes() {
		return template.findDistinct(new Query(), A_TYPE, C_TVSHOW, String.class);
	}

	/*
		db.tvshow.find(
			{ type: { $regex: 'reality', $options: 'i' } }
		)
		.limit(5)
		.sort({ name: 1 })
		.projection({ id: 1, name: 1, summary: 1, 'image.original': 1, _id: 0 })
	 */
	public List<Document> getShowsByType(String type, int limit, int skip) {
		// Create the filter
		Criteria criteria = Criteria.where(A_TYPE)
				.regex(type, "i");

		// Create the query
		Query query = Query.query(criteria)
				.limit(limit)
				.skip(skip)
				.with(Sort.by(Direction.ASC, A_NAME));
		
		// Perform projection
		query.fields()
			.exclude(A_OBJECTID)
			.include(A_ID, A_NAME, A_SUMMARY, A_IMAGE);

		return template.find(query, Document.class, C_TVSHOW);
	}
}
