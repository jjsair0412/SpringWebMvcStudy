package com.hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * 파라미터를 GET방식으로 url에 파라미터로 정보를 전달한 경우
 * 서버에서 받아오는 방법들
 * http://localhost:7060/request-param?username=hello&age=20
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        /**
         * 첫번째 방법
         * 모든 파라미터를 전부다 받아오는 방법
         * request.getParameterNames를 사용하면 된다.
         * 그러내 해당 예제에서는. 이터레이터를 사용해서
         * 지정한 이름들을 getParameter에 넣어줌으로써
         * 전체 파라미터를 가지고 올 수 있었다.
         */
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName+"="+request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - stop");

        System.out.println("[단일 파라미터 조회] - start");
        /**
         * 두번쨰 방법
         * 각각의 파라미터를 따로따로 받야오는 방법
         * request.getParameter를 사용하면 된다.
         */
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - stop");

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        /**
         * http://localhost:7060/request-param?username=hello&age=20&username=kim
         * 이렇게 이름이 같은 파라미터들이 복수로 전달될 경우,
         * request.getParameterValues()로 한꺼번에 배열로 받아오면 된다.
         * 글고 for문으로 출력
         *
         * 참고로 중복일때 getParameter()를 사용하면 getParameterValues의 첫번째 값을 반환한다.
         * 근데 대부분 중복으로 사용하는 경우는 없다.
         **/
        String[] usernames = request.getParameterValues("username");
        for(String name : usernames){
            System.out.println("username = "+name);
        }

        response.getWriter().write("ok");
    }
}
