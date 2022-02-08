xquery version "1.0";

<queryResult>
{
for $x in doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/ID_XMLFile.xml")//Jogador
,
	$z in doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/valor.xml")//Valor
where $x/Empresario = $z
order by $x/Nome 
return <Jogador>
			<Nome>{$x/Nome/text()}</Nome>
			<Empresario>{$x/Empresario/text()}</Empresario>
	   </Jogador>
}
</queryResult>
