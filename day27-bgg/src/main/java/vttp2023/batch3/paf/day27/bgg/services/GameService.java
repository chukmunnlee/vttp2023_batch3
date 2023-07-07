package vttp2023.batch3.paf.day27.bgg.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.paf.day27.bgg.models.Comment;
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

	public void postComment(Comment comment) {
		String cId = UUID.randomUUID().toString().substring(0, 8);

		ObjectId objId = commentRepo.insertComment(comment.withId(cId));

		System.out.printf(">>> objectid: %s\n", objId);
	}
}
