<?xml version="1.0"?>
       
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    version="1.0">
 <xsl:output method="html" indent="yes"/>
    
 <xsl:param name="thisStory" select="'all'"/>
    
 <xsl:template match="/">
       
   <html>
   <head><title>Scotiabank Mobile</title></head>
   <body>
     <ul>
       <xsl:if test="$thisStory='all'">
          <xsl:apply-templates select="/news/story"/>
       </xsl:if>
       <xsl:if test="$thisStory!='all'">
          <xsl:call-template name="singleStory"/>
       </xsl:if>
     </ul>
   </body>
   </html>
       
 </xsl:template>
 <xsl:template match="story">
   <li>
     <xsl:element name="a">
       <xsl:attribute 
          name="href">/MultiUse/XSLServlet?storyid=<xsl:value-of 
          select="@storyid"/></xsl:attribute>
       <xsl:value-of select="headline" />
     </xsl:element>
   </li>
 </xsl:template>  
       
 <xsl:template name="singleStory">
   <p><b><xsl:value-of 
       select="/news/story[@storyid=$thisStory]/headline" /></b></p>
   <p>
     <xsl:element name="a">
       <xsl:attribute name="href"><xsl:value-of 
          select="/news/story[@storyid=$thisStory]/permalink"/></xsl:attribute>
       Full Story
     </xsl:element>
   </p>
 </xsl:template>  
       
</xsl:stylesheet>