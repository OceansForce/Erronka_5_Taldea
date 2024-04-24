<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/ZENBAT_MATERIAL">
      <html>
              <body>
                  <center>
                      <table>
                          <xsl:for-each select="DATA_RECORD">
                              <tr>
                                  <td><label>Materialaren ID: <xsl:value-of select="ID_MATERIALA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Asoziazioaren ID: <xsl:value-of select="ID_ASOZIAZIOA"/></label></td>
                              </tr>
                          </xsl:for-each>
                      </table>
                  </center>
              </body>
          </html>
  </xsl:template>
</xsl:stylesheet>
