<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/BULEGOAK">
      <html>
          <head>
              <title>Bulegoak</title>
          </head>
          <body>
              <center>
                  <xsl:for-each select="DATA_RECORD">
                      <div>
                          <br/>
                              <table border="1">
                                  <tr>
                                      <td><label>ID_Bulegoa<xsl:value-of select="ID_BULEGOA"/></label></td>
                                  </tr>
                                   <tr>
                                      <td><label>ID_Asoziazioa<xsl:value-of select="ID_ASOZIAZIOA"/></label></td>
                                  </tr>
                                   <tr>
                                      <td><label>Herria<xsl:value-of select="HERRIA"/></label></td>
                                  </tr>
                              </table>
                      </div>
                  </xsl:for-each>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>



