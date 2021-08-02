package com.hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper =  new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Content-Type을 먼저 지정해준다.
         * Type은 application/json으로 들어가야 한다.
         */
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        /**
         * getset객체에 set해준후
         * ObjectMapper를 사용해서 json형식으로 바꿔준다.
         * objectMapper.writeValueAsString로 json으로 가공할 수 있는데,
         * 파라미터값으로는 아까 set해줫던 객체를 그대로 넣어주면 된다.
         *
         * 그 후 response.getWriter를 가지고 그냥 넣어버리면 됀다.
         */

        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);

    }
}
