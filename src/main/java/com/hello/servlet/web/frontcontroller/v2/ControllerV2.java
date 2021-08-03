package com.hello.servlet.web.frontcontroller.v2;

import com.hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    /**
     * 인터페이스를 구현한 객체가 MyView를 반환하게끔 구성.
     * MyView는 v1에서의 단점이었던 jsp로 forward해주는 부분을 대신 처리해준다.
     * MyView의 파라미터로는 viewPath경로가 들어가게 된다.
     * */
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
