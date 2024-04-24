<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/ASOZIOAZIOAK">
      <html>
          <head>
              <title>Asoziazioak</title>
          </head>
          <body>
              <center>
                  <table border="1">
                      <xsl:for-each select="DATA_RECORD">
                          <tr>
                              <td><label>ID_Asoziazioa<xsl:value-of select="ID_ASOZIAZIOA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Asoziazio izena<xsl:value-of select=ASOZIAZIO_IZENA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Herrialdea<xsl:value-of select="HERRIALDEA"/></label></td>
                          </tr>
                      </xsl:for-each>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
