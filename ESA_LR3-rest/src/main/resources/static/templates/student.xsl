<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Students List</title>
            </head>
            <body>
                <h1>Students</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                    </tr>
                    <xsl:for-each select="Students/Student">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="firstName"/></td>
                            <td><xsl:value-of select="lastName"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>