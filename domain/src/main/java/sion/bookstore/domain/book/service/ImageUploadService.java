package sion.bookstore.domain.book.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

@Service
public class ImageUploadService {
    public String upload(String imageUrl, String fileName, String fileType) {
//        String fileType = "jpeg";
        String thumbnailPath = "D:/book-store/thumbnail/"  + fileName + "." + fileType;

        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            File file = new File(thumbnailPath);

            ImageIO.write(image, fileType, file);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return thumbnailPath;
    }
}
