package vttp2023.batch3.paf.day27.bgg.services;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.paf.day27.bgg.models.Game;
import vttp2023.batch3.paf.day27.bgg.models.Utils;
import vttp2023.batch3.paf.day27.bgg.repositories.CommentRepository;
import vttp2023.batch3.paf.day27.bgg.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepo;

	@Autowired
	private CommentRepository commentRepo;

	public List<Game> searchGameByName(String name) {
		return gameRepo.findGameByName(name).stream()
			.map(Utils::toGameSummary)
			.toList();
	}

	public Optional<Game> findGameByGid(Integer gid) {

		// Find the game, if it does not exists, exit
		Optional<Document> gameOpt = gameRepo.findGameByGid(gid);
		if (gameOpt.isEmpty())
			return Optional.empty();

		// Get the comments, and add the comments to the game, if any
		List<Document> commentDocs = commentRepo.findCommentsByGid(gid);
		return Optional.of(Utils.toGame(gameOpt.get(), commentDocs));
	}
}
