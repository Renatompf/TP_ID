xquery version "1.0";

<queryResult>
{
for $x in doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/ID_XMLFile.xml")//Jogador
,
	$z in doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/valor.xml")//Valor
where $x/Clubes/ClubeAtual eq $z
order by $x/Nome 
return <Jogador>
			<Nome>{$x/Nome/text()}</Nome>
			<Clube>{$x/Clubes/ClubeAtual/text()}</Clube>
	   </Jogador>
}
</queryResult>
