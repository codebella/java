package org.codebella.sample.java.fileupload;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = FileUploadApplication.class)
@AutoConfigureMockMvc
public class FileUploadApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void uploadFileTest() throws Exception {
		MockMultipartFile requestFile = new MockMultipartFile("file", "Hello Bella".getBytes());

		mvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
				.file(requestFile))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("Successfully Uploaded"));
	}

	@Test
	public void uploadFileFailTest() throws Exception {
		MockMultipartFile requestFile = new MockMultipartFile("data", "data.txt", "text/plain", "Hello Bella".getBytes());

		mvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
				.file(requestFile))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
}
