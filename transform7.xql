xquery version "1.0";

<html><body>
<h2>Jogadores por posicao:</h2>
<ul>
{
for $x in distinct-values(doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/ID_XMLFile.xml")//Posicao)
let $nome := doc("D:/Universidade/2020-2021/2Semestre/ID/TP/TPID_2020/ID_XMLFile.xml")//Jogador[Posicao=$x]/Nome
order by $x
return (<li> {$x}</li>,
			<ul>
			{
			for $p in $nome
			order by $p
			return <li>{$p/text()}</li>
			}
			</ul>)
}
</ul>
</body></html>
