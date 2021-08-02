package com.hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * request.inputStream을 사용하면 메시지 body의 내용을
         * bite코드로 바로 얻을 수 있다.
         * 얻은 Bite 코드를 문자로 변환할때
         * String에서 지원하는  StreamUtils.copyToString을 사용하면 편리하게 변환할 수 있다.
         * copyToString의 파라미터로는, 받아온 bite값, 그리고 인코딩 형식을 넣어준다.
         */
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
