package vttp2023.batch3.csf.day38.models;

public record UploadContent(Integer id, String description, 
		String contentType, byte[] content) { }
