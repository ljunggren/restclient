<?xml version="1.0" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:output method="xml" omit-xml-declaration="no" />

	<xsl:template match="html">
	<html>
		<xsl:apply-templates select="head"/>
		<xsl:apply-templates select="body"/>
	</html>
	</xsl:template>

	<xsl:template match="head">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
		</xsl:copy>
	</xsl:template>

    <xsl:template match="body">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
		</xsl:copy>
	</xsl:template>	

  <xsl:template match="header1">
		<h1><xsl:value-of select="." />	</h1>	
 	</xsl:template>

<xsl:template match="header2">
		<h2><xsl:value-of select="." />	</h2>		
 	</xsl:template>

<xsl:template match="header3">
		<h3><xsl:value-of select="." />	</h3>		
 	</xsl:template>

<xsl:template match="header4">
		<h4><xsl:value-of select="." />	</h4>		
 	</xsl:template>


	<xsl:template match="list">
		<xsl:apply-templates select="header" mode="h4" />
		<xsl:apply-templates select="listitem" mode="break" />
	</xsl:template>

	<xsl:template match="listitem">
		<xsl:apply-templates select="." />
	</xsl:template>
	
	<xsl:template match="textblock">
		<xsl:apply-templates select="textblocktitle" />
		<xsl:apply-templates select="text" />
	</xsl:template>

	<xsl:template match="text">
				<xsl:value-of select="." />		
	</xsl:template>
	
	<xsl:template match="textblocktitle">
				<h4><xsl:value-of select="." /></h4>		
	</xsl:template>
	

	<xsl:template match="header" mode="h1">
				<h1><xsl:value-of select="." /></h1>			
	</xsl:template>
	
		<xsl:template match="header" mode="h2">
				<h2><xsl:value-of select="." /></h2>			
	</xsl:template>
	
		<xsl:template match="header" mode="h3">
				<h3><xsl:value-of select="." /></h3>			
	</xsl:template>
	
		<xsl:template match="header" mode="h4">
				<h4><xsl:value-of select="." /></h4>			
	</xsl:template>
	
	<xsl:template match="listitem" mode="column">
		<xsl:value-of select="."/>
	</xsl:template>

	<xsl:template match="listitem" mode="two_column">
		<xsl:choose>
			<xsl:when test="position() mod 2 = 0">
				<xsl:value-of select="."/>
				<br/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="."/>
				<xsl:text> </xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	
	<xsl:template match="listitem" mode="break">
		<xsl:value-of select="." /><br/>
	</xsl:template>

</xsl:stylesheet>
<!-- Stylus Studio meta-information - (c) 2004-2008. Progress Software Corporation. All rights reserved.

<metaInformation>
	<scenarios>
		<scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="..\xml\tablesample.xml" htmlbaseurl="" outputurl="" processortype="saxon8" useresolver="no" profilemode="0" profiledepth="" profilelength="" urlprofilexml=""
		          commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator="">
			<advancedProp name="sInitialMode" value=""/>
			<advancedProp name="bXsltOneIsOkay" value="true"/>
			<advancedProp name="bSchemaAware" value="false"/>
			<advancedProp name="bXml11" value="false"/>
			<advancedProp name="iValidation" value="0"/>
			<advancedProp name="bExtensions" value="true"/>
			<advancedProp name="iWhitespace" value="0"/>
			<advancedProp name="sInitialTemplate" value=""/>
			<advancedProp name="bTinyTree" value="true"/>
			<advancedProp name="bWarnings" value="true"/>
			<advancedProp name="bUseDTD" value="false"/>
			<advancedProp name="iErrorHandling" value="fatal"/>
		</scenario>
	</scenarios>
	<MapperMetaTag>
		<MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/>
		<MapperBlockPosition></MapperBlockPosition>
		<TemplateContext></TemplateContext>
		<MapperFilter side="source"></MapperFilter>
	</MapperMetaTag>
</metaInformation>
-->