package sion.bookstore.front.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.domain.utils.FileUploadUtil;
import sion.bookstore.domain.utils.SHA256Util;
import sion.bookstore.front.ResponseData;

@Controller
@RequiredArgsConstructor
public class MemberRegisterController {
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
}
