<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/Data_record">
      <html>
          <head>
              <title>Sozioak</title>
          </head>
          <body>
              <center>
                  <table border="1">
                      <tr>
                          <td>ID_Sozioa<xsl:value-of select="ID_sozioa"/></td>
                      </tr>
                      <tr>
                          <td>ID_zuzendaria<xsl:value-of select="ID_zuzendaria"/></td>
                      </tr>
                      <tr>
                          <td>Erle kantitatea<xsl:value-of select="Erle_kantitatea"/></td>
                      </tr>
                      <tr>
                          <td>Kolmena kantitatea<xsl:value-of select="Kolmena_kantitatea"/></td>
                      </tr>
                      <tr>
                          <td>Sozio_izena<xsl:value-of select="Sozio_izena"/></td>
                      </tr>
                      <tr>
                          <td>Sozio_abizena<xsl:value-of select="Sozio_abizena"/></td>
                      </tr>
                      <tr>
                          <td>NAN<xsl:value-of select="NAN"/></td>
                      </tr>
                      <tr>
                          <td>Telefonoa<xsl:value-of select="Telefonoa"/></td>
                      </tr>
                      <tr>
                          <td>Jaioteguna<xsl:value-of select="Jaioteguna"/></td>
                      </tr>
                      <tr>
                          <td>Email<xsl:value-of select="Email"/></td>
                      </tr>
                      <tr>
                          <td>Pasahitza<xsl:value-of select="Pasahitza"/></td>
                      </tr>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
