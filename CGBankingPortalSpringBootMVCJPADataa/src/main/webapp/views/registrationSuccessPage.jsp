<!DOCTYPE html>
<html>
<head>
<style >
div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
</head>
<body>
<div>
<table>
<tr><th>Account Number</th>
	<th>Pin Number</th>
	<th>Account Type</th>
	<th>Account Status</th>
	<th>Account Balance</th>
</tr>
<tr><td>${account.accountNo}</td>
	<td> ${account.pinNumber}</td>
	<td>${account.accountType}</td>
	<td>${account.accountStatus}</td>
	<td>${account.accountBalance}</td>
</tr>
</table>
</div>				  
<br><div align="center"><a href="index">||Home||</a></div>

</body>
</html>
