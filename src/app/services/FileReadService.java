package app.services;

import app.utils.Constants;

import java.io.FileInputStream;
import java.io.IOException;

public class FileReadService {
    public static String read(String fileName) throws IOException {
        try (FileInputStream fis = new FileInputStream(Constants.BASE_PATH_IN + fileName)) {
            byte[] bytes = new byte[1024];
            int read;
            StringBuilder sb = new StringBuilder();
            while ((read = fis.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, read));
            }
            return sb.toString();

        }
    }
}
