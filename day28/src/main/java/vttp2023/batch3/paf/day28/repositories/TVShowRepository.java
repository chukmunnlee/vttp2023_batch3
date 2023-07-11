package vttp2023.batch3.paf.day28.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class TVShowRepository {

	@Autowired
	private MongoTemplate template;

	/*
	 * db.tvshow.aggregate([
	 * 	{ $match: { language: { $regex: lang, $options: 'i' } } }
	 *    {
			  $group: {
			    _id: '$type',
			    titles: {
			      $push: {
			    	 title: '$name',
			    	 genres: '$genres',
			    	 runtime: '$runtime',
			    	 lang: '$language'
			      }
			    }
			 }
	 * ])
	 */
	public List<Document> findShowsByLanguage(String lang) {

		// { $match: { language: { $regex: lang, $options: 'i' } } }
		MatchOperation langMatch = Aggregation.match(
			Criteria.where("language").regex(lang, "i"));

		Document summary = new Document();
		summary.put("title", "$name");
		summary.put("genres", "$genres");
		summary.put("runtime", "$runtime");
		summary.put("language", "$language");

		GroupOperation groupByType = Aggregation.group("type")
			.push(summary).as("name");
			//.push("genres").as("genres")
			//.push("runtime").as("runtime")
			//.push("language").as("language");
			//
		SortOperation sortByLang = Aggregation.sort(
				Sort.by(Direction.ASC, "_id"));

		Aggregation pipeline = Aggregation.newAggregation(langMatch, groupByType, sortByLang);

		AggregationResults<Document> results = template.aggregate(pipeline, "tvshow", Document.class);

		return results.getMappedResults();
	}

	public List<Document> getTitleSummary() {

		ProjectionOperation summarizeTitle = Aggregation.project("_id", "id")
			.and(
				AggregationExpression.from(
					MongoExpression.create("""
						$concat: [ "$name", " - ", { $toString: "$runtime" } ]
					""")
				)
			 )
			.as("title");

		Aggregation pipeline = Aggregation.newAggregation(summarizeTitle);

		return template.aggregate(pipeline, "tvshow", Document.class).getMappedResults();
	}

	public List<Document> getGenresStats() {

		UnwindOperation unwindGenres = Aggregation.unwind("genres");

		GroupOperation groupByGenres = Aggregation.group("genres")
			.count().as("count")
			.avg("runtime").as("averageRuntime")
			.push("name").as("titles");

		SortOperation sortByGenres = Aggregation.sort(Sort.by(Direction.ASC, "_id"));

		Aggregation pipeline = Aggregation.newAggregation(unwindGenres, groupByGenres, sortByGenres);

		return template.aggregate(pipeline, "tvshow", Document.class).getMappedResults();
	}

}
