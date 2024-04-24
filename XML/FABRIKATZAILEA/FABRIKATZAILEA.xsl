<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/FABRIKATZAILEA">
      <html>
          <head>
              <title>Fabrikatzaileak</title>
          </head>
          <body>
              <center>
                  <xsl:for-each select="DATA_RECORD">
                      <div>
                          <br/>
                          <table border="1">
                              <tr>
                                  <td><label>Fabrikatzailearen ID: <xsl:value-of select="ID_FABRIKATZAILEA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Izena: <xsl:value-of select="IZENA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Hiria: <xsl:value-of select="HIRIA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Helbidea: <xsl:value-of select="HELBIDEA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Telefonoa: <xsl:value-of select="TELEFONOA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Korreoa: <xsl:value-of select="KORREOA"/></label></td>
                              </tr>
                          </table>
                      </div>
                  </xsl:for-each>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
