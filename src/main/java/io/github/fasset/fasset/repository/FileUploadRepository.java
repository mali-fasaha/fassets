package io.github.fasset.fasset.repository;

import io.github.fasset.fasset.model.files.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileUploadRepository extends JpaRepository<FileUpload,Integer>{

    int countAllByFileName(String fileName);
}
