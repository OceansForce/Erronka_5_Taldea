<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/SOZIOAK">
      <html>
          <head>
              <title>Sozioak</title>
          </head>
          <body>
              <center>
                  <table>
                      <xsl:for-each select="DATA_RECORD">
                          <tr>
                              <td><label>Sozioaren ID: <xsl:value-of select="ID_SOZIOA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Zuzendariaren ID: <xsl:value-of select="ID_ZUZENDARIA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Erle kantitatea:  <xsl:value-of select="ERLE_KANTITATEA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Kolmena kantitatea: <xsl:value-of select="KOLMENA_KANTITATEA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Sozio izena: <xsl:value-of select="SOZIO_IZENA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Sozio abizena: <xsl:value-of select="SOZIO_ABIZENA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>NAN: <xsl:value-of select="NAN"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Telefonoa: <xsl:value-of select="TELEFONOA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Jaiotze eguna: <xsl:value-of select="JAIOTE_EGUNA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Posta: <xsl:value-of select="EMAIL"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Pasahitza: <xsl:value-of select="PASAHITZA"/></label></td>
                          </tr>
                      </xsl:for-each>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
