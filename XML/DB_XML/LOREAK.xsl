<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/">
      <html>
          <head>
              <title>Loreak</title>
          </head>
          <body>
              <center>
                  <table>
                      <xsl:for-each select="DATA_RECORD">
                          <tr>
                              <td><label>Lorearen ID: <xsl:value-of select="ID_LOREA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Erlearen ID: <xsl:value-of select="ID_ERLEA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Izena: <xsl:value-of select="IZENA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Kokapena: <xsl:value-of select="KOKAPENA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Kantitatea: <xsl:value-of select="KANTITATEA"/></label></td>
                          </tr>
                      </xsl:for-each>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
