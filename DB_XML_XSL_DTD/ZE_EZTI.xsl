<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/ZE_EZTI">
      <html>
              <body>
                  <center>
                      <table>
                          <xsl:for-each select="DATA_RECORD">
                              <tr>
                                  <td><label>Sozioaren ID: <xsl:value-of select="ID_SOZIOA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Produktuaren ID: <xsl:value-of select="ID_PRODUKTUA"/></label></td>
                              </tr>
                          </xsl:for-each>
                      </table>
                  </center>
              </body>
          </html>
  </xsl:template>
</xsl:stylesheet>
