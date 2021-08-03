package com.hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 요청이 오면, viewPath에 적혀진 경로로 dispatch해주는 세줄.
         * dispatcher.forward(request,response);
         * 얘는 다른 서블릿이나 JSP로 이동할 수 있는 기능인데, 서버 내부에서 다시 호출이 발생한다.
         * 서버 안에서 호출이 발생한다는것이다.
         *
         * redirect가 아니다.
         */
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);

    }
}
