package com.hello.servlet.web.springmvc.v1;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 얘네 둘 써도 됨.
 * 그냥 @Controller 써도 되ㅣ고
 * 자바로 그냥 스프링 빈으로 등록해도 됌.
 */
//@Controller
@Component
@RequestMapping
public class SpringMemberFormControllerV1 {

    /**
     * 클라이언트가 URI로 /springmvc/v1/members/new-form 얘를 넘겨주면
     * 해당 메서드가 실행된다.
     * 메서드이름은 아무거나 지어줘도 된다.
     */
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}
