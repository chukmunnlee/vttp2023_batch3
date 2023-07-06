package vttp2023.batch3.paf.day27.bgg.repositories;

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
public class CommentRepository {

	@Autowired
	private MongoTemplate template;

	/*
	 * db.comments.find({ gid: gid })
	 * 	.sort({ rating: -1 })
	 * 	.limit(5)
	 */
	public List<Document> findCommentsByGid(Integer gid) {
		Criteria criteria = Criteria.where(Constants.A_GID);

		Query query = Query.query(criteria)
				.with(Sort.by(Direction.DESC, Constants.A_RATING))
				.limit(5);

		return template.find(query, Document.class, Constants.C_COMMENTS);
	}
}
