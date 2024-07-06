package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
     // MemberService 는 인스턴스르 여러 개 생성할 필요가 없어서 스프링 컨테이너에 하나만 등록한다
//    private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    @Autowired // 스프링이 memberService를 연결해줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }
//    @GetMapping("/members")
//    public String members() {
//        return "members";
//    }
}
