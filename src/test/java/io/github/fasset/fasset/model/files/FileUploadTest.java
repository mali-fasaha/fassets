package io.github.fasset.fasset.model.files;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.YearMonth;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUploadTest {
    private FileUpload fileUpload;
    private LocalDateTime uploadTime;
    private YearMonth month;

    @Before
    public void setUp() throws Exception {

        uploadTime = LocalDateTime.now();
        month  = YearMonth.of(2018,11);

        fileUpload = new FileUpload("testFile", month, uploadTime);

    }

    @Test
    public void toJson() {

        assertEquals("{\n\"fileName\":\"testFile\",\n\"month\":\"2018-11\",\n" +
            "\"timeUploaded\":\""+uploadTime.toString()+"\",\n"+"\"deserialized\":\"false\"\n}", fileUpload.toJson());
    }

}