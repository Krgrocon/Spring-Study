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
}