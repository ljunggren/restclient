<?xml version="1.0" ?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" omit-xml-declaration="no" />

	<xsl:template match="html">
	<html>
		<xsl:apply-templates select="head" />
		<xsl:apply-templates select="body" />
	</html>
	</xsl:template>

	<xsl:template match="head">
		<head>
			<title>Class2CSS</title>
			<link rel="stylesheet" type="text/css" href="css/class2.css" />
		</head>
	</xsl:template>

	<xsl:template match="node()|@*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
		</xsl:copy>
	</xsl:template>

</xsl:stylesheet>