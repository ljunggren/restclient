<?xml version="1.0"?>
       
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    version="1.0">
 <xsl:output method="html" indent="yes"/>
    
 <xsl:param name="thisStory" select="'all'"/>
    
 <xsl:template match="/">
       
   <html>
   <head>
	   <title>Scotiabank.com</title>
   </head>
   <body>
     <center>
       <img src="images/logo.gif" alt="logo" width="420" height="71" /></center>
       
     <h2 align="center">New At Scotiabank.com</h2>
       
     <table border="0">
       <xsl:if test="$thisStory='all'">
         <xsl:apply-templates select="/news/story"/>
       </xsl:if>
       <xsl:if test="$thisStory!='all'">
         <xsl:call-template name="singleStory"/>
       </xsl:if>
     </table>
   </body>
   </html>
       
 </xsl:template>
 <xsl:template match="story">
   <tr>
     <td>
       <xsl:element name="a">
         <xsl:attribute name="href">/MultiUse/XSLServlet?storyid=<xsl:value-of 
           select="@storyid"/></xsl:attribute>
         <b><xsl:value-of select="headline" /></b>
       </xsl:element>
     </td>  
     <td><xsl:value-of select="blurb" /></td>
   </tr>
 </xsl:template>  
       
 <xsl:template name="singleStory">
   <h3><xsl:value-of select="/news/story[@storyid=$thisStory]/headline" /></h3>
   <p><xsl:value-of select="/news/story[@storyid=$thisStory]/blurb" /></p>
   <p>Get the full story 
     <xsl:element name="a">
       <xsl:attribute name="href"><xsl:value-of 
         select="/news/story[@storyid=$thisStory]/permalink"/></xsl:attribute>
         here.
     </xsl:element>
   </p>
 </xsl:template>  
       
       
</xsl:stylesheet>