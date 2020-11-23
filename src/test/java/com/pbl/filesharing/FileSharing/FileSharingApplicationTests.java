package com.pbl.filesharing.FileSharing;

import com.pbl.filesharing.FileSharing.entity.Document;
import com.pbl.filesharing.FileSharing.repository.DocumentRepository;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FileSharingApplicationTests {

	@Autowired
	private DocumentRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	@Rollback(false)
	void testInsertDocument() throws IOException {
		File file = new File("D:\\archit.jpg");

		Document document = new Document();
		document.setName(file.getName());

		byte[] bytes = Files.readAllBytes(file.toPath());
		document.setContent(bytes);
		long fileSize = bytes.length;
		document.setSize(bytes.length);
		document.setUploadTime(new Date());

		Document saveDoc = repository.save(document);

		Document existDoc = entityManager.find(Document.class, saveDoc.getId());

		assertThat(existDoc.getSize()).isEqualTo(fileSize);
	}

}
