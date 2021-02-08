package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.auth.User;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.domain.utils.FileUploadUtil;
import sion.bookstore.domain.utils.SHA256Util;
import sion.bookstore.front.ResponseData;
import sion.bookstore.front.login.LoginRequired;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final FileUploadUtil fileUploadUtil;
    private final MemberValidator memberValidator;

    @Value("${profile.image.path}")
    private String imagePath;

    @PostMapping("/member/register")
    @ResponseBody
    public ResponseData register(Member member) {
        memberValidator.validate(member, "member");

        String password = member.getPassword();
        String salt  = SHA256Util.generateSalt();
        String encryptedPassword = SHA256Util.getEncrypt(password, salt);
        member.setPasswordSalt(salt);
        member.setPassword(encryptedPassword);

        member.setProfileImgPath(fileUploadUtil.uploadFile(member.getProfileImageFile(), imagePath));

        memberService.create(member);
        return ResponseData.success(member.getId());
    }

    @LoginRequired
    @PostMapping("/member/update")
    @ResponseBody
    public ResponseData update(Member member, String originalPassword) {
        // hidden으로 넘어와야 하는 값 : 원래 패스워드(key: originalPassword),
        //  Member 모델에 매핑되도록 : email, salt(key: passwordSalt),  기존 프로필이미지 path(key: profileImgPath), cratedAt, createdBy

        memberValidator.validate(member, "member");

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

    @GetMapping("/my-info")
    @ResponseBody
    @LoginRequired
    public ResponseData getMyInfo() {
        User user = UserContext.get();
        Member member = memberService.findOneById(user.getMemberId());

        return ResponseData.success(member);
    }



}
