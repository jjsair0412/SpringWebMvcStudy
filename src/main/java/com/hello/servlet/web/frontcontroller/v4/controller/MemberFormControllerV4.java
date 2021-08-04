package com.hello.servlet.web.frontcontroller.v4.controller;
import com.hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        /**
         * 반환하는 값을 viewName의 논리적 이름만 반환하면 된다.
         */
        return "new-form";
    }
}
