<%@ page import="com.hello.servlet.domain.member.MemberRepository" %>
<%@ page import="com.hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 최상단은 import부분 -->
<%
    /**
     * request나 response 사용이 가능하다.
     * 또한 해당 태그 내부에는 자바구문이 들어가고
     * html도 같이 넣을 수 있다.
     */

    MemberRepository memberRepository = MemberRepository.getInstance();
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username,age);
    memberRepository.save(member);


%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>name=<%=member.getUsername()%></li>
    <li>Age=<%=member.getAge()%></li>
</ul>
<a href="/index.html"> 메인 </a>
</body>
</html>
