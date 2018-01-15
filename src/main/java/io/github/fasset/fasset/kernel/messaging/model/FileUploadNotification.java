package io.github.fasset.fasset.kernel.messaging.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FileUploadNotification implements Serializable{

    private static final long serialVersionUID = 2657188181025701641L;

    /* the name and location of file*/
    private final String fileName;

    /* month to which the file relates*/
    private final String month;

    /* time of upload */
    private final LocalDateTime timeUploaded;

    public FileUploadNotification(String fileName, String month, LocalDateTime timeUploaded) {
        this.fileName = fileName;
        this.month = month;
        this.timeUploaded = timeUploaded;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMonth() {
        return month;
    }

    public LocalDateTime getTimeUploaded() {
        return timeUploaded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileUploadNotification that = (FileUploadNotification) o;
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
