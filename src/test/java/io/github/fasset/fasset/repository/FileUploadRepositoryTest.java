package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.files.FileUpload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileUploadRepositoryTest {


    @Qualifier("fileUploadRepository")
    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Test
    public void fileUploadRepositoryWorks() throws Exception {

        assertNotNull(fileUploadRepository.save(new FileUpload()));
    }
}