<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/Produktuak">
      <html>
          <head>
              <title>Produktuak</title>
          </head>
          <body>
              <center>
                  <table>
                      <tr>
                          <td>ID_produktua<xsl:value-of select="Data_record/ID_produktua"/></td>
                      </tr>
                      <tr>
                          <td>Kantitatea<xsl:value-of select="Data_record/Kantitatea"/></td>
                      </tr>
                      <tr>
                          <td>Produktu_mota<xsl:value-of select="Data_record/Produktu_mota"/></td>
                      </tr>
                      <tr>
                          <td>Prezioa<xsl:value-of select="Data_record/Prezioa"/></td>
                      </tr>
                      <tr>
                          <td>Deskribapena<xsl:value-of select="Data_record/Deskribapena"/></td>
                      </tr>
                  </table>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
