<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/HYMENOPTERA_EUROPAN">
  <html>
          <head>
              <title></title>
          </head>
          <body>
              <center>
                  <table border="1">
                      <xsl:for-each select="DATA_RECORD">
                          <tr>
                              <td><label>Hypnotera ID: <xsl:value-of select="ID_HYMENOPTERA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Produktuaren ID: <xsl:value-of select="ID_PRODUKTUA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Kantitatea: <xsl:value-of select="KANTITATEA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Europearrak(bai edo ez): <xsl:value-of select="EUROPEARRAK_EDO_EZ"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Kokapena: <xsl:value-of select="KOKAPENA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Izena: <xsl:value-of select="IZENA"/></label></td>
                          </tr>
                      </xsl:for-each>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
