package com.shamsid.demouploadfiles;

import com.shamsid.demouploadfiles.model.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoUploadFilesApplicationTests {
	
	private String FILE_PATH=""; // Add you file path here
	RestTemplate restTemplate() throws Exception {
		return new RestTemplate();
	}

	@Test
	void contextLoads() throws Exception {
		File file = new File(FILE_PATH);
		FileSystemResource fileSystemResource = new FileSystemResource(file);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", fileSystemResource);
		HttpEntity<MultiValueMap<String, Object>> requestEntity
				= new HttpEntity<>(body, headers);
		ResponseEntity<ResponseMessage> response = restTemplate().postForEntity("http://localhost:9090/shamsid/app/upload",
				requestEntity, ResponseMessage.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.hasText(response.getBody().getMessage(),"Uploaded the file successfully:");
	}



}
