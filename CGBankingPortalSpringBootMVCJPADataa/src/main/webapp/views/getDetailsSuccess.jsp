<html>
<body>
	<div>
		Account Number:- ${account.accountNo}<br> Pin Number:-
		${account.pinNumber}<br> Account Type:- ${account.accountType}<br>
		Account Status:- ${account.accountStatus}<br> Account Balance:-
		${account.accountBalance}<br> <font color="green" size=5><b>Account
				Transactions:-</b></font><br>
		<table>
			<tr>
				<th>Transaction ID</th>
				<th>Transaction Amount</th>
				<th>Transaction Type</th>
			</tr>
			<s:forEach var="account" items="${account.transactions.values()}">
				<tr>
					<td>${account.transactionId}</td>
					<td>${account.amount}</td>
					<td>${account.transacriotnType}</td>
				</tr>
			</s:forEach>
		</table>
	</div>
	<br>
	<div align="center">
		<a href="index">Home</a>
	</div>
</body>
</html>
