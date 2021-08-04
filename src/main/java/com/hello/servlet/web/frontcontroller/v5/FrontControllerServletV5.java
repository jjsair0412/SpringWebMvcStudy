package com.hello.servlet.web.frontcontroller.v5;


import com.hello.servlet.web.frontcontroller.ModelView;
import com.hello.servlet.web.frontcontroller.MyView;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {


    /**
     * 모든 컨트롤러들을 지원해야 하기 때문에 , Map의 value값을 컨트롤러 인터페이스가 아닌 Object로 설정
     * private Map<String, ControllerV4> controllerMap = new HashMap<>();
     * 기존 코드
     */
    private final Map<String,Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5(){
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // v4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
        핸들러를 찾아주는 부분
         MemberFormControllerV3 반환
         **/
        Object handler = getHandler(request);
        if(handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // ControllerV3HandlerAdapter 반환
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);

        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            /**
             * for문을 돌면서 받아온 헨들러가 어뎁터가 지원 한다면
             * 핸들러어뎁터를 반환해준다.
             *
             * 사용할수있는 헨들러가 없다면 예외처리
             *
             * ControllerV3HandlerAdapter를 반환한다.
             */
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다.");
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
