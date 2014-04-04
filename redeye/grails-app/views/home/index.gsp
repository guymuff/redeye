<%--
  Created by IntelliJ IDEA.
  User: sungjoon.ma
  Date: 4/3/14
  Time: 6:10 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title> Home for Red Eyes</title>
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'home.css')}" type="text/css">
</head>
<body>

<table>
    <thead>
    <tr>
        <td colspan="2">
            <g:form name="testForm" controller="home" action="index">
                <g:textField name="UserIdInput" value="${UserIdInput}">  </g:textField>
                <g:actionSubmit value="Accio Data"  action="populateData"/>
            </g:form>
        </td>
    </tr>
    </thead>
    <tbody>
    <tr><td>meow</td><td>meow</td></tr>
    <tr><td>meow</td><td>meow</td></tr>
    <tr><td>meow</td><td>meow</td></tr>
    <tr><td>meow</td><td>meow</td></tr>
    <tr><td>meow</td><td>meow</td></tr>
    <tr><td>meow</td><td>meow</td></tr>
    <tr><td>meow</td><td>meow</td></tr>
    <tr><td>meow</td><td>meow</td></tr>
    </tbody>
</table>



</body>
</html>