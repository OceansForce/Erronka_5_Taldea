<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/EZTIA">
      <html>
          <head>
              <title>Eztiak</title>
          </head>
          <body>
              <center>
                  <table>
                      <xsl:for-each select="DATA_RECORD">
                          <tr>
                              <td><label>Produktuaren ID: <xsl:value-of select="ID_PRODUKTUA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Lorearen ID: <xsl:value-of select="ID_LOREA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Ezti mota: <xsl:value-of select="EZTIA_MOTA"/></label></td>
                          </tr>
                      </xsl:for-each>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
