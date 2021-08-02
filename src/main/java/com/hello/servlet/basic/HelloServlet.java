package com.hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @WebServlet 어노테이션을 활용해서 서블릿을 지정한다.
 * name: 서블릿 이름
 * urlPatterns: URL 매핑 ,
 * hello가 value로들어오면 service가 호출됨..
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("reqest = " + reqest);
        System.out.println("response = " + response);


        /**
         * request를 사용해 파라미터값을 편리하게 가져올 수 있다.
         * response를 이용해 응답을 편리하게 보낼 수 있다.
         *
         * response에서 타입과 인코딩타입을 정해주고
         * 보내줄 값을 정해준다.
         * 값을 보내줄때는 response.getWriter().write(String 값)을 넣어주면 된다
         */
        String username = reqest.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
