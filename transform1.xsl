<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="Jogadores">
		<html><body>
			<h1>Listagem de Jogadores</h1>
			<table border="1">
				<tr><th>Foto do Jogador</th><th>Nome do Jogador</th></tr>
				<xsl:apply-templates select="Jogador">
					<xsl:sort select="Nome"/>
				</xsl:apply-templates>
			</table>
		</body></html>
	</xsl:template>
	
	<xsl:template match="Jogador">
		<tr>
			<td><img src="{Foto}" width="100"/></td>
			<td><xsl:value-of select="Nome"/></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>


