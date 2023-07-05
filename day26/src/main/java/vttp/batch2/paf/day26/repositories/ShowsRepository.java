package vttp.batch2.paf.day26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShowsRepository {

   public static final String F_NAME = "name";
   public static final String F_GENRES = "genres";
   public static final String F_ID = "_id";
   public static final String C_TVSHOW = "tvshow";

   @Autowired
   private MongoTemplate template;

	/*
	 * db.tvshows.find({
	 * 	genres: { $in: [ g0, g1, ... ] }
	 * })
	 */
	public List<String> findShowsByGenre(Object... genreList) {

		// Create the filter
		Criteria criteria = Criteria.where(F_GENRES)
			.in(genreList);

		Query query = Query.query(criteria);
		query.fields()
			.exclude(F_ID)
			.include(F_NAME);

		return template.find(query, String.class, C_TVSHOW);
	}

   /*
    * db.tvshow.find({ name: 'the name' })
    */
   public List<Document> findShowsByName(String programName) {

      // Set the filter
      Criteria criteria = Criteria.where(F_NAME).is(programName);

      // Create a Mongo query with the filter
      Query query = Query.query(criteria);

      // Perform the query
      return template.find(query, Document.class, C_TVSHOW);

   }
   
}
