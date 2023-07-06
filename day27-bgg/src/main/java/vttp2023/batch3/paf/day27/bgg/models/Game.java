package vttp2023.batch3.paf.day27.bgg.models;

import java.util.List;

public record Game(
	Integer gid, String name, Integer year, String url, String image, 
	List<Comment> comments) {
}
