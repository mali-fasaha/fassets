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

import io.github.fasset.fasset.DomainModel;
import io.github.fasset.fasset.kernel.util.Jsonable;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * This object represents a row of record of an Excel file that has been uploaded to the server
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Entity(name = "FileUpload")
@Audited
public class FileUpload extends DomainModel<String> implements Serializable, Jsonable {

    private static final long serialVersionUID = -593481318028396924L;

    /* the name and location of file*/
    private String fileName;

    /* month to which the file relates*/
    private YearMonth month;

    /* time of upload */
    private LocalDateTime timeUploaded;

    private boolean deserialized;

    /**
     * <p>Constructor for FileUpload.</p>
     */
    public FileUpload() {
    }

    /**
     * <p>Constructor for FileUpload.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @param month a {@link java.time.YearMonth} object.
     * @param timeUploaded a {@link java.time.LocalDateTime} object.
     */
    public FileUpload(String fileName, YearMonth month, LocalDateTime timeUploaded) {
        this.fileName = fileName;
        this.month = month;
        this.timeUploaded = timeUploaded;
    }

    /**
     * <p>isDeserialized.</p>
     *
     * @return a boolean.
     */
    public boolean isDeserialized() {
        return deserialized;
    }

    /**
     * <p>Setter for the field <code>deserialized</code>.</p>
     *
     * @param deserialized a boolean.
     */
    public void setDeserialized(boolean deserialized) {
        this.deserialized = deserialized;
    }

    /**
     * <p>Getter for the field <code>fileName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * <p>Setter for the field <code>fileName</code>.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @return a {@link io.github.fasset.fasset.model.files.FileUpload} object.
     */
    public FileUpload setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * <p>Getter for the field <code>month</code>.</p>
     *
     * @return a {@link java.time.YearMonth} object.
     */
    public YearMonth getMonth() {
        return month;
    }

    /**
     * <p>Setter for the field <code>month</code>.</p>
     *
     * @param month a {@link java.time.YearMonth} object.
     * @return a {@link io.github.fasset.fasset.model.files.FileUpload} object.
     */
    public FileUpload setMonth(YearMonth month) {
        this.month = month;
        return this;
    }

    /**
     * <p>Getter for the field <code>timeUploaded</code>.</p>
     *
     * @return a {@link java.time.LocalDateTime} object.
     */
    public LocalDateTime getTimeUploaded() {
        return timeUploaded;
    }

    /**
     * <p>Setter for the field <code>timeUploaded</code>.</p>
     *
     * @param timeUploaded a {@link java.time.LocalDateTime} object.
     * @return a {@link io.github.fasset.fasset.model.files.FileUpload} object.
     */
    public FileUpload setTimeUploaded(LocalDateTime timeUploaded) {
        this.timeUploaded = timeUploaded;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public String toJson() {

        return "{\n" + "\"fileName\":\"" + fileName + "\",\n" + "\"month\":\"" + month.toString() + "\",\n" + "\"timeUploaded\":\"" + timeUploaded.toString() + "\",\n" + "\"deserialized\":\"" +
            deserialized + "\"\n}";
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        FileUpload that = (FileUpload) o;

        if (deserialized != that.deserialized) {
            return false;
        }
        if (!fileName.equals(that.fileName)) {
            return false;
        }
        if (!month.equals(that.month)) {
            return false;
        }
        return timeUploaded.equals(that.timeUploaded);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + fileName.hashCode();
        result = 31 * result + month.hashCode();
        result = 31 * result + timeUploaded.hashCode();
        result = 31 * result + (deserialized ? 1 : 0);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return MessageFormat.format("FileUploadNotification'{'fileName=''{0}'', month=''{1}'', deserialized=''{2}'', timeUploaded={3}'}'", fileName, month, deserialized, timeUploaded);
    }
}
