package io.github.fasset.fasset.kernel.util;

public class FileSecurityChecks {

    public static void relativePathCheck(String fileName){

        if (fileName.contains("..")) {

            throw new StorageException("Cannot store file with relative path outside current directory " + fileName);
        }
    }
}
