<#import "userInfo.ftl" as ui>

<#macro comm titleValue >
    <html>
    <head>
        <title> ${titleValue} </title>
    </head>
    <body>
    <#nested/>
    </body>
    </html>
</#macro>