package sion.bookstore.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sion.bookstore.domain.auth.UserContext;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.repository.MemberRepository;
import sion.bookstore.domain.utils.FileUploadUtil;
import sion.bookstore.domain.utils.SHA256Util;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AddressService addressService;
    private final FileUploadUtil fileUploadUtil;
    @Value("${profile.image.path}")
    private String imagePath;

    public Long register(Member member) {
        setPasswordAndSalt(member);
        member.setAdmin(false);
        member.setProfileImgPath(fileUploadUtil.uploadFile(member.getProfileImageFile(), imagePath));
        setDefaultInfo(member);

        Long createdMemberId = memberRepository.register(member);
        addressService.create(member);

        return createdMemberId;
    }

    private Member setPasswordAndSalt(Member member) {
        String password = member.getPassword();
        String salt  = SHA256Util.generateSalt();
        String encryptedPassword = SHA256Util.getEncrypt(password, salt);
        member.setPasswordSalt(salt);
        member.setPassword(encryptedPassword);

        return member;
    }

    private void setDefaultInfo(Member member) {
        member.setCreatedAt(new Date());
        member.setCreatedBy(member.getEmail());
        member.setModifiedAt(new Date());
        member.setModifiedBy(member.getEmail());
        member.setDeleted(false);
    }

    public Long update(Member member) {
        Member existingMember = findOneById(UserContext.get().getMemberId());

        setNotChangedInfo(member, existingMember);
        compareAndSetNewPassword(member, existingMember);

        fileUploadUtil.deleteExistingFile(existingMember.getProfileImgPath());
        member.setProfileImgPath(fileUploadUtil.uploadFile(member.getProfileImageFile(), imagePath));

        Long updatedMemberId = memberRepository.update(member);
        addressService.update(member);

        return updatedMemberId;
    }

    private void compareAndSetNewPassword(Member member, Member existingMember) {
        String salt = existingMember.getPasswordSalt();
        String newPassword = SHA256Util.getEncrypt(member.getPassword(), salt);

        if (!newPassword.equals(existingMember.getPassword())) {
            member.setPassword(newPassword);
        }
    }

    private void setNotChangedInfo(Member newMember, Member existingMember) {
        newMember.setId(existingMember.getId());
        newMember.setEmail(existingMember.getEmail());
        newMember.setPasswordSalt(existingMember.getPasswordSalt());
        newMember.setAdmin(existingMember.isAdmin());
        newMember.setCreatedAt(existingMember.getCreatedAt());
        newMember.setCreatedBy(existingMember.getEmail());
        newMember.setModifiedAt(new Date());
        newMember.setModifiedBy(UserContext.get().getUserEmail());
        newMember.setDeleted(false);
    }

    @Transactional(readOnly = true)
    public Member findOneById(Long id) {
        return memberRepository.findOneById(id);
    }

    @Transactional(readOnly = true)
    public Member findOneByEmail(String email) {
        return memberRepository.findOneByEmail(email);
    }

    @Transactional(readOnly = true)
    public Page<Member> findAll(MemberSearchCondition condition) {
        List<Member> memberList = memberRepository.findAll(condition);
        Long totalCount = memberRepository.countAll(condition);
        Page<Member> memberPage = new PageImpl<>(memberList, condition.getPageable(), totalCount);

        return memberPage;
    }
}
