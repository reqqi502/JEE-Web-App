<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%------Jsp Servlet --------------%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JEE WEB CRUD</title>
	<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body style="background-color:black">
<br><br><br>
<h1 style="color:blue;font-size:30px;font-family: Optima;text-align: center">Web Application CRUD</h1>
<br><br><br><br>
<div style="border: none;">

	<form action="controleur" method="post" style="text-align:center">
		<input type="text" name="keyword" value="${model.getKeyword() }" style="width:60%;padding: 12px 20px;margin: 8px 0;box-sizing: border-box;" placeholder="Searche For ...">
		<input type="submit" value="search" name="action" style="padding: 12px 20px;margin: 8px 0;box-sizing: border-box;color:white;background: black;border:none">
	</form>

</div>
<div style="border: none">
	<form action="controleur.php" method="post" style="text-align: center;border: none">
		<input type="hidden" name="mode" value="${model.getMode()}">

		<table style="max-width:200px ; margin:auto" >

			<c:if test="${model.getMode()=='add'}">
				<tr style="text-align: center;color: white"><th></th><td><input type="text"  name="ref" value="${model.getProduit().getReference()}" placeholder="Reference..." style="padding:10px;width:150%"></td></tr>
			</c:if>
			<c:if test="${model.getMode()=='edit'}">
				<tr style="text-align: center"><th></th><td style="color:white">${model.getProduit().getReference()}<input type="hidden"  name="ref" value="${model.getProduit().getReference()}" placeholder="Reference ..."></td></tr></c:if>
			<tr style="padding:10px;width:150%"><th style="color:black;font-weight: 600"></th><td><input type="text" name="des" value="${model.getProduit().getDesignation()}" placeholder="description..." style="padding:10px;width:150%"></td></tr>
			<tr style="padding:10px;width:150%"><th></th><td><input type="number" name="prix" value="${model.getProduit().getPrix()}"  style="padding:10px;width:150%" placeholder="Prix ..."></td></tr>
			<tr style="padding:10px;width:150%"><th></th><td><input type="number" name="quantite" value="${model.getProduit().getQuantite()}"  style="padding:10px;width:150%" placeholder="Qantity..."></td></tr>
		</table>
		<input type="submit" value="save" name="action" style="max-width:200px;margin:auto; padding:10px;width:5%;color:white;background:blue;margin: 19px -7px 0px 346px;border: none">
	</form>
</div>
<div style="border: none">
	<p>${model.getErrors()}</p>
</div>
<div style="border:none">
	<center>
	<table class="table1">
		<tr>
			<th style="background: white;color: black">Reference</th>
			<th style="background: white;color: black">Description</th>
			<th style="background: white;color: black">Prix</th>
			<th style="background: white;color: black">Quantity</th>
		</tr>
		<c:forEach items="${model.listProduit }" var="p">
			<tr>
				<td>${p.getReference()}</td>
				<td>${p.getDesignation()}</td>
				<td>${p.getPrix()}</td>
				<td>${p.getQuantite()}</td>
				<td>
					<a style="padding:7px;color: red ; text-decoration: none" href="javascript:confirmer('controleur.php?action=delete&ref=${p.getReference()}')">delete</a>
				</td>
				<td>
					<a style="padding:7px;color: green;text-decoration: none" href="controleur.php?action=edit&ref=${p.getReference()}">edit</a>
				</td>
			</tr>

		</c:forEach>
	</table>
	</center>
</div>
</center>
<script type="text/javascript">
	function confirmer(url){
		var isConfirm=confirm("vous etes sur de supprimer cet element?");
		if(isConfirm){
			document.location=url;
		}

	}
</script>
</body>
</html>