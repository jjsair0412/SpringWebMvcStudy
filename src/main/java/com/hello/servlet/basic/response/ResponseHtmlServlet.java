package com.hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         첫번째로 Content-type을 text/html으로 지정해줘야 한다.
         Content-Type : text/html;charset=utf-8
        **/
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        /**
         * 그리고 html코드를 writer를 활용해서
         * 그대로 그냥 다 적어준다 ;
         */
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<div>안녕?</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
