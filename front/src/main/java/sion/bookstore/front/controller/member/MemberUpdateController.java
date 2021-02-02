package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.domain.utils.FileUploadUtil;
import sion.bookstore.domain.utils.SHA256Util;
import sion.bookstore.front.ResponseData;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberUpdateController {
    private final MemberService memberService;
    private final FileUploadUtil fileUploadUtil;
    @Value("${profile.image.path}")
    private String imagePath;


//    @LoginRequired
    @PostMapping("/member/update")
    @ResponseBody
    public ResponseData update(Member member, String originalPassword) {
        // TODO memberValidator.validate(member);
        // TODO hidden으로 넘어와야 하는 값 : 원래 패스워드(key: originalPassword),
        //  Member 모델에 매핑되도록 : email, salt(key: passwordSalt),  기존 프로필이미지 path(key: profileImgPath), cratedAt, createdBy

        compareAndSetNewPassword(member, originalPassword);
        fileUploadUtil.deleteExistingFile(member.getProfileImgPath());
        member.setProfileImgPath(fileUploadUtil.uploadFile(member.getProfileImageFile(), imagePath));
        memberService.update(member);

        return ResponseData.success(member.getId());
    }

    private void compareAndSetNewPassword(Member member, String originalPassword) {
        String userInputPassword = member.getPassword();
        String salt = member.getPasswordSalt();

        if (!userInputPassword.equals(originalPassword)) {
            String encryptedPassword = SHA256Util.getEncrypt(userInputPassword, salt);
            member.setPassword(encryptedPassword);
        }
    }

}
