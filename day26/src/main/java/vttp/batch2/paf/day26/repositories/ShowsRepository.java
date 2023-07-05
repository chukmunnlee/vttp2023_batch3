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
   public static final String C_TVSHOW = "tvshow";

   @Autowired
   private MongoTemplate template;

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
