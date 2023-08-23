package vttp2023.batch3.csf.day38.repositories;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Repository
public class S3Repository {

	@Autowired
	private AmazonS3 s3;

	public String saveImage(MultipartFile uploaFile) {
		Map<String, String> userData = new HashMap<>();
		userData.put("uploadeBy", "fred");
		userData.put("filename", uploaFile.getOriginalFilename());

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(uploaFile.getContentType());
		metadata.setContentLength(uploaFile.getSize());
		metadata.setUserMetadata(userData);

		String id = UUID.randomUUID().toString().substring(0, 8);

		try {
			PutObjectRequest putReq = new PutObjectRequest("vttpcsf"
					//, "images/%s".formatted(id)
					, "%s".formatted(id)
					, uploaFile.getInputStream(), metadata);
			// To allow for public access
			// https://vttpcsf.sgp1.digitaloceanspaces.com/images/<id>
			putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);
			PutObjectResult result = s3.putObject(putReq);
			System.out.printf(">>>> result: %s\n", result);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return id;
	}
}
