xquery version "1.0";

(: Generated with EditiX XML Editor (http://www.editix.com) at Thu Jun 03 17:07:02 BST 2021 :)

for $x in doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/ID_XMLFile.xml")//Jogador
,
	$z in doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/valor.xml")//Valor
	
where $x/Nacionalidade eq $z
order by $x/Nome 
return ("Jogador: ",
		$x/Nome/text(),"&#10;")
