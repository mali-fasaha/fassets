package io.github.fasset.fasset.model.files;

import io.github.fasset.fasset.DomainModel;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity(name="FileUpload")
@Audited
public class FileUpload extends DomainModel<String> implements Serializable{

    private static final long serialVersionUID = 2657188181025701641L;

    /* the name and location of file*/
    private String fileName;

    /* month to which the file relates*/
    private YearMonth month;

    /* time of upload */
    private LocalDateTime timeUploaded;

    public FileUpload() {
    }

    public FileUpload(String fileName, YearMonth month, LocalDateTime timeUploaded) {
        this.fileName = fileName;
        this.month = month;
        this.timeUploaded = timeUploaded;
    }

    public String getFileName() {
        return fileName;
    }

    public FileUpload setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public YearMonth getMonth() {
        return month;
    }

    public FileUpload setMonth(YearMonth month) {
        this.month = month;
        return this;
    }

    public LocalDateTime getTimeUploaded() {
        return timeUploaded;
    }

    public FileUpload setTimeUploaded(LocalDateTime timeUploaded) {
        this.timeUploaded = timeUploaded;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileUpload that = (FileUpload) o;
        return Objects.equals(fileName, that.fileName) &&
                Objects.equals(month, that.month) &&
                Objects.equals(timeUploaded, that.timeUploaded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, month, timeUploaded);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FileUploadNotification{");
        sb.append("fileName='").append(fileName).append('\'');
        sb.append(", month='").append(month).append('\'');
        sb.append(", timeUploaded=").append(timeUploaded);
        sb.append('}');
        return sb.toString();
    }
}
