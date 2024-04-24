<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/MATERIALA">
      <html>
          <head>
              <title>Materialak</title>
          </head>
          <body>
              <center>
                  <table>
                      <xsl:for-each select="DATA_RECORD">
                          <tr>
                              <td><label>Materialaren ID: <xsl:value-of select="ID_MATERIALA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Fabrikatzailearen ID: <xsl:value-of select="ID_FABRIKATZAILEA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Material mota:  <xsl:value-of select="MATERIALA_IZENA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Prezioa: <xsl:value-of select="PREZIOA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Deskribapena: <xsl:value-of select="DESKRIBAPENA"/></label></td>
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
