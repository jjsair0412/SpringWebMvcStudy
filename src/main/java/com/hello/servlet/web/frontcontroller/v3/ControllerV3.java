package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    /**
     * 서블릿에 종속적이지 않다.
     **/
    ModelView process(Map<String, String> paramMap);
}
