package com.hello.servlet.web.springmvc.v2;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 하나의 컨트롤러에 다 모아놓고 실행할 수 있다.
 * 이렇게되면 내가 예전에들었던 mvc강의와 똑같아진다.
 * 매핑이 메서드단위로 찾아지기때문에
 * 핸들러매핑이 컨트롤러를찾아주고, 매핑이 메서드단위로 찾게 된다.
 */
@Controller
/**
 * 전체 리퀘스트매핑에다가 uri리소스를 넣어준다면
 * 해당 주소랑 매서드별로 매핑된 주소랑 조합해서 uri를 만들어준다.
 */
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member",member);
        return mv;
    }


    // 해당 findmembers 메서드의 주소는 /springmvc/v2/members
    @RequestMapping
    public ModelAndView findmembers(){

        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",members);

        return mv;
    }
}
