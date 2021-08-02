package com.hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RequestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * json형식으로 받아올때도 Stream으로 받은다음 String으로 변환시켜주면 된다.
         * json도 어쨋던 문자이기 때문이다.
         */
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");

        /**
         * 해당 기능을 사용하려면 Jackson, Gson같은 라이브러리를 추가해서 사용해야 한다.
         * 스프링 부트로 Spring MVC를 선택한다면 기본으로 Jackson 라이브러리를 제공한다.
         *
         * HelloData는 username과 age를 getset해주는 객체이다.
         * 보내주는 json 데이터는 {"username": "hello", "age": 20} 이런 형식일 경우
         * 받아온 json형식 데이터를 객체로 바로 변환해서 get으로 사용할 수 있다..
         *
         * 스프링에서 지원하는 ObjectMapper라는애의 인스턴스를 생성해주고
         * objectMapper.readValue()로 바로 set주입시켜줄 수 있다..
         * 파라미터값으로는 순서대로 스트림형식으로 받아온 값을 String으로 변환해준 값을 넣어주고,
         * 그다음 getset객체를 넣어준다음 받아주는 데이터 형식은
         * getset객체를 해주면 된다.
         *
         * 그다음 .get으로 걍 꺼내서 쓰면 된다.
         */
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        System.out.println("helloData username = "+helloData.getUsername());
        System.out.println("helloData age = "+helloData.getAge());
    }
}
