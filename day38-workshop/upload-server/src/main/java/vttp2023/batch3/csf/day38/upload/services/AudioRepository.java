package vttp2023.batch3.csf.day38.upload.services;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class AudioRepository {

	@Autowired
	private AmazonS3 s3;

	public String upload(String contentType, InputStream is) {
		String id = UUID.randomUUID().toString().substring(0, 8);
		String key = "audio/%s".formatted(id);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);

		PutObjectRequest putReq = new PutObjectRequest("vttpcsf", key, is, metadata);
		putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);

		s3.putObject(putReq);

		return id;
	}

	public String getURL(String id) {
		String key = "audio/%s".formatted(id);
		return s3.getUrl("vttpcsf", key).toExternalForm();
	}
}
