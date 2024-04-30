<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/SOZIOAK">
      <html>
          <head>
              <title>Sozioak</title>
          </head>
            <style>
                * {
                    font-family: Calibri;
                }
                .header .cover {
                    position: relative;
                    margin-bottom: 70px;
                    height: 200px; 
                    background: cover;
                    width: 100%;
                }
                body {
                    background: linear-gradient(to right, #FBC703, #FACE04, #FCA701); 
                    margin: 0; 
                    padding: 0;
                }
                .logo {
                    position: absolute;
                    top: 10px;
                    left: 10px;
                    width: 12%;
                }
                h1 {
                    font-size: 50px;
                    position: absolute;
                    width: 50%;
                    text-align: center;
                    top: 10px;
                    left: 50%;
                    transform: translateX(-50%);   /*KONTU HONEKIN*/
                    background-color: rgba(255, 255, 255, 0.5);
                    padding: 10px;
                    margin: 0;
                }
                table {
                    margin-left: 10%;
                    width: 30%;
                    border-collapse: collapse;
                    margin-bottom: 40px;
                    background-color: white;
                    float: left;
                }

                td {
                    padding: 10px;
                }

                label {
                    font-weight: bold;
                    font-size: 22px;
                }

                .table-container {
                    margin-bottom: 40px;
                }
                .biltegi {
                    width: 30%;
                    float: right;
                    box-sizing: border-box;
                    padding: 0 5px;
                }
            </style>
          <body>
          <div class="header">
            <img src="../IRUDIAK/cover.PNG" alt="" class="cover"/>
            <img src="../IRUDIAK/irudi.png" alt="" class="logo"/>
            <h1>ERLEZAIN ELKARTEA</h1>
          </div> 
              <center>                 
                    <xsl:for-each select="DATA_RECORD">
                        <div class="biltegi">
                        <br/>
                            <table border="1">
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
                            </table>
                        </div>
                  </xsl:for-each>
              </center>
          </body>
      </html>
  </xsl:template>
</xsl:stylesheet>
