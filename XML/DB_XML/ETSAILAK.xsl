<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/ETSAILAK">
      <html>
          <head>
              <title>Etsaiak</title>
          </head>
          <body>
              <center>
                  <table>
                      <xsl:for-each select="DATA_RECORD">
                          <tr>
                              <td><label>Etsaiaren ID:<xsl:value-of select="ID_ETSAIA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Izena: <xsl:value-of select="IZENA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Kanpotarra(bai edo ez): <xsl:value-of select="KANPOTARRA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Deskribapena: <xsl:value-of select="DESKRIBAPENA"/></label></td>
                          </tr>
                          <tr>
                              <td><label>Hildako_kan: <xsl:value-of select="HILDAKO_KAN"/></label></td>
                          </tr>
                      </xsl:for-each>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
