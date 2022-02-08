xquery version "1.0";

for $x in doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/ID_XMLFile.xml")//Jogador
,
	$z in doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/valor.xml")//Valor
where $x/@estadoAtual eq $z
order by $x/Nome 
return ("Jogador: ",
		$x/Nome/text(), "--Data Nascimento:", $x/DataNascimento/text(), ", ", $x/Idade/text(), "&#10;")

