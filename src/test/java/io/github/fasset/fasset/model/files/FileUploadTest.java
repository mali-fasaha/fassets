/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.model.files;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.YearMonth;

import static org.junit.Assert.*;

public class FileUploadTest {
    private FileUpload fileUpload;
    private LocalDateTime uploadTime;
    private YearMonth month;

    @Before
    public void setUp() throws Exception {

        uploadTime = LocalDateTime.now();
        month = YearMonth.of(2018, 11);

        fileUpload = new FileUpload("testFile", month, uploadTime);

    }

    @Test
    public void toJson() {

        assertEquals("{\n\"fileName\":\"testFile\",\n\"month\":\"2018-11\",\n" + "\"timeUploaded\":\"" + uploadTime.toString() + "\",\n" + "\"deserialized\":\"false\"\n}", fileUpload.toJson());
    }

}