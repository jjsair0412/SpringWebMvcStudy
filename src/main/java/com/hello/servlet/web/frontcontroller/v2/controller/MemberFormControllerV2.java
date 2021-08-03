package com.hello.servlet.web.frontcontroller.v2.controller;

import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 반환타입을 MyView로 줫기 떄문에, 아까 forward해줫던 3줄을
         * 한줄로 구성핳 수 있다.
         * 공통된코드를 제거할 수 있다.
         */
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
