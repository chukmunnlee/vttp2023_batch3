package vttp2023.batch3.paf.day27.bgg.repositories;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.paf.day27.bgg.models.Comment;

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
		Criteria criteria = Criteria.where(Constants.A_GID).is(gid);


		Query query = Query.query(criteria)
				.with(Sort.by(Direction.DESC, Constants.A_RATING))
				.limit(5);

		return template.find(query, Document.class, Constants.C_COMMENTS);
	}

	/*
	 * db.comments.insert({ ... })
	 */
	public ObjectId insertComment(Comment comment) {
		Document doc = new Document();
		doc.put(Constants.A_CID, comment.cId());
		doc.put(Constants.A_USER, comment.user());
		doc.put(Constants.A_RATING, comment.rating());
		doc.put(Constants.A_TEXT, comment.text());
		doc.put(Constants.A_GID, comment.gid());

		Document newDoc = template.insert(doc, Constants.C_COMMENTS);
		return newDoc.getObjectId(Constants.A_ID);
	}
}
