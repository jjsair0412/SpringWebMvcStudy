package com.hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * response.setStatus로 http 응답코드를 설정할 수 있다.
         * HttpServlet의 응답 코드가 ok라면 200.
         * 200을 그냥 넣어줘도 상관은 없지만 SC_OK를 넣어줌으로써
         * 좀더 잘 알아볼 수 있게끔 해주자.
         */
        response.setStatus(HttpServletResponse.SC_OK);
        /**
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        응답 실패시 400
        /**
         * 헤더도 직접 지정해 줄 수 있다.
         *
         * response.setHeader를 사용해서 헤더이름과 값을 지정해 줄 수 있는데,
         * 마지막 my-header처럼 헤더를 직접 작성해서 집어넣을수도 있다.
         */
        response.setHeader("Content-Type","text/plain");
        response.setHeader("Cache-Control","no-cahe, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("my-header","hello");

        PrintWriter writer = response.getWriter();
        writer.write("ok");
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        /**
         * response.setContentType을 이용해서 편하게 ContentType을 집어넣어줄 수 있다.
         * 인코딩도 setCharacterEncoding로 편하게 설정할 수 있다.
         */
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        /**
         * 쿠키도 response.setHeader로 다 작성해서 넣어도 무관하지만
         * Cookie라는 객체를 이용해서 편하게 설정할 수 있다.
         */
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        /**
         * redirect도 response.setStatuse로 그냥 넣어줘도 무관하지만
         * sendRedirect로 hello-form으로 넘어가게끔 할 수 있다.
         */
        response.sendRedirect("/basic/hello-form.html");
    }
}
