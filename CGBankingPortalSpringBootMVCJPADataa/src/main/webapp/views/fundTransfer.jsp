<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<html>
<head>
<style>
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
</head>
<body>
<div align="center">
		<h2>Withdraw Amount</h2>
		<form:form action="fundT" method="post" modelAttribute="transaction">
		<table>
				<tr>
					<td>Sender's Account Number:</td>
					<td><form:input path="account.accountNo" size="30" /></td>
					<td><form:errors path="account.accountNo" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Reciever's Account Number:</td>
					<td><form:input path="account.accountNo" size="30" /></td>
					<td><form:errors path="account.accountNo" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Amount:</td>
					<td><form:input path="amount" size="30" /></td>
					<td><form:errors path="amount" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Sender's Pin Number:</td>
					<td><form:input path="account.pinNumber" size="30" /></td>
					<td><form:errors path="account.pinNumber" cssClass="error" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>	
		</table>
		</form:form>
	</div>
</body>
</html>