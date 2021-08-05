package com.hello.servlet.web.springmvc.v3;

import com.hello.servlet.domain.member.Member;
import com.hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    /**
     * 어노테이션 기반의 스프링에선 ModelAndView를 반환해도 무관하지만
     * 그냥 String을 반환시켜서 논리주소를 return에 박아버려도 문제가 없다.
     * RequestMapping에서 method를 선택해 줄 수 있고
     * method를 get으로 걸어놓으면 newForm이라는 메서드는 get일때만 동작하게 된다.
     *
     * @GetMapping 어노테이션을 사용해서 method를 따로 설정해주지 않아도 자동으로 get으로 받게끔 할 수 있다.
     */

//    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping(value = "/new-form")
    public String newForm(){
        return "new-form";
    }

    /**
     * @RequestParam 이라는 어노테이션을 파라미터에 넣고 사용해서, 받아온 값을 그대로 받을 수 있다.
     * 형변환도 자동으로 해준다.
     *
     * request.getParameter("username")과 @RequestParam("username") String username
     * 이둘은 같은 코드이다.
     *
     * age는 넘어올때 String인데 int로 바꿔준다.
     *
     * model.addAttribute("member",member);
     * 로 model에 속성들을 넣어줄 수 있다.
     *
     * 그리고 jsp에서 ${member.id} 이런식으로 꺼내다 쓰면 된다.
     *
     * @PostMapping 어노테이션을 사용해서 method를 따로 설정해주지 않아도 자동으로 post로 받게끔 할 수 있다.
     **/
//    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @PostMapping(value = "/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member",member);
        return "save-result";
    }


    // 해당 findmembers 메서드의 주소는 /springmvc/v3/members
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String findmembers(Model model){

        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);

        return "members";
    }
}
