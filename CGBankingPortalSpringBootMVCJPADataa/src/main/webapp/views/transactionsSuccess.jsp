
<html>
<head>
<style>
div {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
</head>
<body>
<div> 
<font color="green" size=10><b>All Transactions are:-</b></font><br> 
<table>
<tr><th>Transaction ID</th>
	<th>Transaction Amount</th>
	<th>Transaction Type</th>
</tr>
<s:forEach var="comm" items="${trans}">
<tr><td>${comm.transactionId}</td>
	<td> ${comm.amount}</td>
	<td>${comm.transacriotnType}</td>
</tr>
	</s:forEach>
	</table>
</div>
<br><div align="center"><a href="index">||Home||</a></div>
</body>
</html>

		  

