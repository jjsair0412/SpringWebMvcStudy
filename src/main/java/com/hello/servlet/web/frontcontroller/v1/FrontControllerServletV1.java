package com.hello.servlet.web.frontcontroller.v1;

import com.hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * url 매핑정보에 *을 작성해주면, v1 하위 경로로 들어오는 모든 클라이언트는
 * 무조건 해당 FrontController를 거친다.
 */
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    /**
     * 해당 Map을 이용해서 어떤 컨트롤러를 실행할지 판단한다.
     * key: 매핑 URL
     * value: 호출될 컨트롤러
     */
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1(){
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        /**
         * getRequestURI를 사용하면, url로 입력한 부분을 그대로 받을 수 있다.
         * 만약 /front-controller/v1/members
         * 해당 주소가 requestURI에 들어오면
         * 생성해놓은 인스턴스인 new MemberListControllerV1() 얘를 꺼내서 ControllerV1에 넣어준다.
         *
         * 컨트롤러를 찾고 controller.process(request, response); 을 호출해서 해당 컨트롤러를 실행한다
         */
        String requestURI = request.getRequestURI();

        /**
        ControllerV1 controllerV1 = new MemberListControllerV1();
         위의 구문과 바로 밑 구문은 같다.
         **/
        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller==null){
            /**
             * Map에 입력해놓은 주소를 찾지 못한다면 404오류를 뱉게끔 한다.
             **/
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response);

    }
}
