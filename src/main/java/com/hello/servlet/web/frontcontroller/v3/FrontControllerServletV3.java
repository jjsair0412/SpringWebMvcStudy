package com.hello.servlet.web.frontcontroller.v3;

import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v2.ControllerV2;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);

        /**
         * 만약 viewName에 new-form이 들어왓다면
         * /WEB-INF/views/new-form.jsp 이렇게 주소가 바뀜
         */
        String viewName = mv.getViewName();// 논리이름만 반환됨
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    /**
     * 모든 request를 다 뽑이온다.
     **/
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
