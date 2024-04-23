<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/Produktuak">
      <html>
          <head>
              <title>Produktuen lista</title>
          </head>
          <body>
              <center>
                  <table border="1">
                      <xsl:for-each select="Data_record">
                          <tr>
                              <td>l<label>ID_produktua<xsl:value-of select="ID_produktua"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Kantitatea<xsl:value-of select="Kantitatea"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Produktu_mota<xsl:value-of select="Produktu_mota"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Prezioa<xsl:value-of select="Prezioa"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Deskribapena<xsl:value-of select="Deskribapena"/></label></td>
                          </tr>
                      </xsl:for-each>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
