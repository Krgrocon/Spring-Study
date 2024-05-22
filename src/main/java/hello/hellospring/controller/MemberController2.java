//package hello.hellospring.controller;
//
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.ui.Model;
//import hello.hellospring.domain.Member;
//import hello.hellospring.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//public class MemberController {
//
//    private MemberService memberService;
//
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//    @GetMapping("/members/new")
//    public String createForm()  {
//        return "members/createMemberForm";
//    }
//
//    @PostMapping("/members/new")
//    public String create(MemberForm form,Model model) {
//        Member member = new Member();
//        member.setUsername(form.getUsername());
//        member.setPassword(form.getPassword());
//        member.setEmail(form.getEmail());
//
//
//        System.out.println(member.getPassword() + "gg" + member.getEmail() +"55" + member.getUsername()) ;
//        try {
//            memberService.join(member);
//        } catch (IllegalStateException e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "members/createMemberForm"; // 회원 가입 폼으로 다시 이동
//        }
//        return "redirect:/";
//    }
//
//
//    @PostMapping(value = "/login")
//    public String login(@RequestParam String username ,@RequestParam String password , HttpSession session) {
//        Member member =  memberService.authenticate(username, password);
//
//    }
//
//
//    @GetMapping(value = "/members")
//    public String list(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//        return "members/memberList";
//    }
//
//
//
//}

