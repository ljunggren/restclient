<?xml version="1.0" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" omit-xml-declaration="yes" />

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="book">
		<html>
			<body>
				There are
				<xsl:value-of select="quantity" />
				copies of
				<i>
					<xsl:value-of select="title" />
				</i>
				available.
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
 
<?xml version="1.0" ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" omit-xml-declaration="no" />

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="book">
		<xsl:element name="book">
			<xsl:attribute name="isbn">
			  <xsl:value-of select="isbn" />
			</xsl:attribute>
			<xsl:element name="quantity">
				<xsl:value-of select="quantity" />
			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
