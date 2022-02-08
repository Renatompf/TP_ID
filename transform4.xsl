<?xml version="1.0" encoding="UTF-8" ?>

<!-- New XSLT document created with EditiX XML Editor (http://www.editix.com) at Tue Jun 01 14:01:22 BST 2021 -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="Jogadores">
		
		<Jogadores>
			<xsl:for-each select="Jogador">
				<xsl:sort select="ValorContrato" data-type="number" order="descending"/>
				<xsl:if test="ValorContrato and position()&#60;6">
					<Jogador ValorContrato="{ValorContrato}€"><xsl:value-of select="Nome"/></Jogador>
				</xsl:if>
			</xsl:for-each>
		</Jogadores>
		
	</xsl:template>
</xsl:stylesheet>


