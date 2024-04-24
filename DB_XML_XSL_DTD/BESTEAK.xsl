<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/BESTEAK">
      <html>
          <body>
              <table border="1">
                  <xsl:for-each select="DATA_RECORD">
                      <tr>
                          <td><label>ID_Produktua<xsl:value-of select="ID_PRODUKTUA"/></label></td>
                      </tr>
                  </xsl:for-each>
              </table>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
