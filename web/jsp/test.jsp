<%@ page import="com.HelloClient" %>
<%@ page import="com.Itest" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="net.sf.json.JSONString" %><%--
  Created by IntelliJ IDEA.
  User: zhangxuri
  Date: 2017/6/26
  Time: 下午12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    String names;
    HelloClient helloClient;
    Itest itest;
    JSONArray jsonArray=new JSONArray();
    List<String> MeetingRoomName=new ArrayList<String>();
%>
<%
    helloClient =new HelloClient();
    itest=helloClient.gettest();
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("name","zhang");
    jsonObject.put("telephone","13121510044");
    jsonObject.put("email","zhang");
    jsonObject.put("position","no");
    jsonObject.put("departmentid",1);
    jsonObject.put("username","zhangxuri");
    jsonObject.put("password","123456");
    JSONObject jsonObject1=JSONObject.fromObject(jsonObject.toString());
    itest.regist(jsonObject1.toString());
    System.out.println(jsonObject1.toString());

%>