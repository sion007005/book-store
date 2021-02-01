package sion.bookstore.domain.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileUploadUtil {
    /** 업로드 경로 */
    @Value("${image.root.path}")
    private String uploadPath;

    @Value("${basic.img.root.path}")
    private String basicImagePath;

    /**
     * 서버에 생성할 파일명을 처리할 랜덤 문자열 반환
     * @return 랜덤 문자열
     */
    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 서버에 첨부 파일을 생성 후 업로드 성공 시 이미지가 저장된 경로를,
     * 업로드 할 파일이 없을 경우 기본 이미지 경로를 반환한다.
     * @param file - 파일
     * @return 이미지 저장 경로 / 실패 시 failed
     */
    public String uploadFile(MultipartFile file) {

        /* 파일이 없으면 failed 문자열을 반환 */
        if (Objects.isNull(file)) {
            return "failed";
        }

        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }

        /* 파일 개수만큼 forEach 실행 */
        try {
            /* 파일 확장자 */
            final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
            final String fileName = getRandomString() + "." + extension;

            File target = new File(uploadPath, fileName);
            file.transferTo(target);

            return uploadPath + fileName;
        } catch (IOException e) {
            throw new FileUploadException("failed to save file...");

        } catch (Exception e) {
            throw new FileUploadException("failed to save file...");
        }
    }

    public void deleteExistingFile(String originalImagePath) {
        File file = new File(originalImagePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
