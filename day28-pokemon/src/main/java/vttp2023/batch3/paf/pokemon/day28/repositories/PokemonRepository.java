package vttp2023.batch3.paf.pokemon.day28.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Repository;

@Repository
public class PokemonRepository {


	@Autowired
	private MongoTemplate template;

	/*
	 * db.mons.aggregate([
	 * 	{ $unwind: 'type' },
	 * 	{ $group: { _id: '$type' } },
	 * 	{ $sort: { _id: 1 } }
	 * ])
	 */
	public List<String> getTypes() {

		UnwindOperation unwindType = Aggregation.unwind("type");

		GroupOperation groupType = Aggregation.group("type");

		SortOperation sortType = Aggregation.sort(Sort.by(Direction.ASC, "_id"));

		Aggregation pipeline = Aggregation.newAggregation(unwindType, groupType, sortType);

		return template.aggregate(pipeline, "mons", Document.class)
			.getMappedResults().stream()
			.map(d -> d.getString("_id"))
			.toList();
	}
}
