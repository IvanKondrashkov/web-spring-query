<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Boot</title>
</head>
<body>
<h1>Spring Boot - MVC web application example</h1>
<hr>
<div class="form">
    <form action="hello" method="post" onsubmit="return validate()">
        <table>
            <tr>
                <td>Введите вашу заметку</td>
                <td><input id="header" name="note" value="Это я, господи!"></td>
            <tr>
                <td><input type="submit" value="Принять"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>