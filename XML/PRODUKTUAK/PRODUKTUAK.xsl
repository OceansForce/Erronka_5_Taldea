<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/PRODUKTUAK">
      <html>
          <head>
              <title>Produktuak</title>
          </head>
          <body>
              <center>
                  <xsl:for-each select="DATA_RECORD">
                      <div>
                          <br/>
                          <table border="1">
                              <tr>
                                  <td><label>Produktuaren ID: <xsl:value-of select="ID_PRODUKTUA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Kantitatea: <xsl:value-of select="KANTITATEA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Produktu mota:  <xsl:value-of select="PRODUKTU_MOTA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Prezioa: <xsl:value-of select="PREZIOA"/></label></td>
                              </tr>
                              <tr>
                                  <td><label>Deskribapena: <xsl:value-of select="DESKRIBAPENA"/></label></td>
                              </tr>
                          </table>
                      </div>
                  </xsl:for-each>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
