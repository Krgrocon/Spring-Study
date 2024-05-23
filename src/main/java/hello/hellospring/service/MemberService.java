package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //회원인증 , 로그인
    public Member authenticate(String username, String password) {
        Optional<Member> member = memberRepository.findByUsernameAndPassword(username, password);
        return member.orElse(null);
    }

    // 회원등록
    public Member save(Member member) {
        validateDuplicateMember(member);
        System.out.println("확인용");
        return memberRepository.save(member);
    }
    // 아이디 찾기
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }
    // 유저네임 찾기
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
    // 전부조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 중복검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByUsername(member.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 회원가입시 아이디 영어 + 숫자 검증
    public boolean usernameVerification(String username) {
        return username != null && username.matches("[a-zA-Z0-9!@_]{6,12}$");
    }


    // 회원가입시 패스워드 영어 + 숫자 + 특수문자 ! @ _ 및 8자 검증
    public boolean PasswordVerification(String password){
        return password != null && password.matches("[a-zA-Z0-9!@_]{6,12}$");

    }
}