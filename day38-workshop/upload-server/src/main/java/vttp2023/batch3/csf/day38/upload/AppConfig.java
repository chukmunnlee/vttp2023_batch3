package vttp2023.batch3.csf.day38.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AppConfig {

	@Value("${s3.key.access}")
	private String accessKey;

	@Value("${s3.key.secret}")
	private String secretKey;

	@Bean
	public AmazonS3 createS3() {
		BasicAWSCredentials cred = new BasicAWSCredentials(accessKey, secretKey);
		EndpointConfiguration epConfig = new EndpointConfiguration(
				"sgp1.digitaloceanspaces.com", "vttpcsf");
		return AmazonS3ClientBuilder.standard()
			.withEndpointConfiguration(epConfig)
			.withCredentials(new AWSStaticCredentialsProvider(cred))
			.build();
	}
}
