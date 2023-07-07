package vttp2023.batch3.paf.day27.bgg.models;

public record Comment(
	String cId, String user, Integer rating, String text, Integer gid) {

	public Comment withId(String cId) {
		return new Comment(cId, user(), rating(), text(), gid());
	}
}
