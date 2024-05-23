package hello.hellospring.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm()  {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form, Model model) {
        Member member = new Member();
        member.setUsername(form.getUsername());
        member.setPassword(form.getPassword());
        member.setEmail(form.getEmail());
        boolean isPassword= memberService.PasswordVerification(member.getPassword());
        boolean isUsername= memberService.usernameVerification(member.getUsername());

        if(!isUsername) {
            model.addAttribute("errorMessage", "아이디를 형식에 맞춰 입력해주세요");
            return "members/createMemberForm";
        }
        if(!isPassword){
            model.addAttribute("errorMessage", "패스워드를 형식에 맞춰 입력해주세요");
            return "members/createMemberForm";
        }


        try {
            memberService.save(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", "이미 가입된 아이디 입니다.");
            return "members/createMemberForm"; // 회원 가입 폼으로 다시 이동
        }
        return "redirect:/";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        Member member = memberService.authenticate(username, password);
        if (member != null) {
            session.setAttribute("loggedInUser", member);
            return "/home";
        } else {
            return "/home"; // 로그인 실패 시 로그인 폼으로 다시 이동
        }
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}