<!DOCTYPE html>
<html lang="fr-be">
<head>
<meta charset="utf-8">
<style>
.billet{
border : 2px dashed #AAAAAA;
margin : 5 px;
padding : 5px;
background-color : #EEEEFF; 
text-align : center;
}
</style>
</head>
<body>
<#list spectacles as spectacle>
	<div class="billet" >
	<h1>Spectacle ${spectacle.num}</h1>
	<h2>Le ${spectacle.date?date}</h2>
	<h4>Place n°${spectacle.place}</h4>
	<p>
	${spectacle.description}
	</p>
	</div>
</#list>

<br/>
</body>
</html>