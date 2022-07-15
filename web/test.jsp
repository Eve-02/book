<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/pages/common/head.jsp"%>
    <script>
        $(function () {
            $("button").click(function () {
                $.getJSON("http://localhost:8080/book/testServlet","action=test",function () {

                })
            })
        })
    </script>
</head>
<body>
    <button>提交</button>
</body>
</html>
