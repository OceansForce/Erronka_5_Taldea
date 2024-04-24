<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/ASOZIAZIO_PARTE">
      <html>
          <head>
              <title>Asoziazio parte</title>
          </head>
          <body>
              <center>
                  <table>
                      <xsl:for-each select="DATA_RECORD">
                          <tr>
                              <td><label>ID_Asoziazioa: <xsl:value-of select="ID_ASOZIAZIOA"/></label>l</td>
                          </tr>
                          <tr>
                              <td><label>ID_Sozioa: <xsl:value-of select="ID_SOZIOA"/></label></td>
                          </tr>
                      </xsl:for-each>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
