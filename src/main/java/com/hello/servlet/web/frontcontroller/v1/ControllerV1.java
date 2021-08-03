package com.hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {
    /**
     * 각 컨트롤러는 해당 인터페이스를 구현해서 만든다
     */
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
