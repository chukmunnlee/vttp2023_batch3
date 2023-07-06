package vttp2023.batch3.paf.day27.services;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.paf.day27.models.TvShow;
import vttp2023.batch3.paf.day27.respositories.ShowRepository;

@Service
public class ShowService {

	@Autowired
	private ShowRepository showRepo;

	public List<TvShow> getShowsByType(String type, int limit, int skip) {
		return showRepo.getShowsByType(type, limit, skip).stream()
			.map(d -> {
				return new TvShow(
						d.getInteger("id"),
						d.getString("name"), 
						d.getString("summary"), 
						d.get("image", Document.class).getString("original")
				);
			})
			.toList();
	}
}
