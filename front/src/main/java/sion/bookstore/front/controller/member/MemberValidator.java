package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.utils.validator.*;
import sion.bookstore.front.FrontConstants;
import sion.bookstore.front.controller.order.IllegalOperationException;

@RequiredArgsConstructor
@Component
public class MemberValidator implements Validator<Member> {
    private final EngKorStringValidator engKorStringValidator;
    private final HasValueValidator hasValueValidator;
    private final PhoneNumberValidator phoneNumberValidator;
    private final EmailValidator emailValidator;

    @Override
    public void validate(Member member, String type) {
        engKorStringValidator.validate(member.getName(), "name");
        emailValidator.validate(member.getEmail(), "email");
        hasValueValidator.validate(member.getPassword(), "password");
        hasValueValidator.validate(member.getPasswordSalt(), "password salt");
        phoneNumberValidator.validate(member.getPhone(), "phone number");

        MultipartFile profileImageFile = member.getProfileImageFile();
        if (!profileImageFile.isEmpty()) {
            validateUploadedFile(profileImageFile);
        }

        // TODO 주소는 어떻게 검증?
    }

    private void validateUploadedFile(MultipartFile file) {
        validateExtension(file);
        validateFileSize(file);
    }

    private void validateExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!"png".equals(extension) && !"jpeg".equals(extension) && !"jpg".equals(extension)) {
            throw new InvalidFileExtensionException("Only jpg/jpeg and png files are accepted");
        }
    }

    private void validateFileSize(MultipartFile file){
        if (file.getSize() >= FrontConstants.MAXIMUM_FILE_SIZE_ALLOWED) {
            throw new IllegalOperationException("File size cannot be greater than 8 Mb");
        }
    }
}
