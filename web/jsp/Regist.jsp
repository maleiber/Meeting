<%@ page import="com.HelloClient" %>
<%@ page import="com.Itest" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="sun.security.provider.MD5" %><%--
  Created by IntelliJ IDEA.
  User: zhangxuri
  Date: 2017/6/26
  Time: 下午3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    String names;
    HelloClient helloClient;
    Itest itest;
%>
<%
    helloClient =new HelloClient();
    itest=helloClient.gettest();
    request.setCharacterEncoding("utf-8");
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("username",request.getParameter("username"));
    jsonObject.put("name",request.getParameter("name"));
    jsonObject.put("password",request.getParameter("password"));
    jsonObject.put("telephone",request.getParameter("telephone"));
    jsonObject.put("email",request.getParameter("email"));
    jsonObject.put("departmentid",Integer.parseInt(request.getParameter("departmentid")));
    jsonObject.put("position",request.getParameter("position"));
    System.out.println(jsonObject.toString());
    if (!itest.regist(jsonObject.toString())){
        response.sendError(-1);
    }
    else{
        response.getWriter().write("zhangxuri");
    }
%>