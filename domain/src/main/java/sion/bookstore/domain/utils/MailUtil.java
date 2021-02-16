package sion.bookstore.domain.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sion.bookstore.domain.email.Mail;
import sion.bookstore.domain.member.repository.Member;
import sion.bookstore.domain.member.service.MemberService;
import sion.bookstore.domain.order.repository.Order;

@RequiredArgsConstructor
@Component
public class MailUtil {
    private final MemberService memberService;

    public Mail getOrderCompletionMail(Order order) {
        Member member = memberService.findOneById(order.getMemberId());

        Mail mail = new Mail();
        mail.setAddress(member.getEmail());
        mail.setTitle(member.getName() +"님의 주문이 완료되었습니다.");
        mail.setMessage("주문 번호는 " + order.getId() + "입니다. \n");

        return mail;
    }

    public Mail getOrderCancellationMail(Order order) {
        Member member = memberService.findOneById(order.getMemberId());

        Mail mail = new Mail();
        mail.setAddress(member.getEmail());
        mail.setTitle(member.getName() +"님의 주문이 취소되었습니다.");
        mail.setMessage("주문 번호는 " + order.getId() + "입니다. \n");

        return mail;
    }
}
