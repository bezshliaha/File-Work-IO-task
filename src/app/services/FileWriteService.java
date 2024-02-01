package app.services;

import app.utils.Constants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriteService {
    public void writeToFile(Path path, String content)
            throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            fos.write(content.getBytes());
        }
    }

    public Path createFile(String fileName) throws IOException {
        return Files.createFile(Path.of(Constants.BASE_PATH_IN, fileName));
    }
}