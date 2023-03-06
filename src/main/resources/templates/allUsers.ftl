<#import "macros/comm.ftl" as c>
<#import "macros/userInfo.ftl" as usInf>

<@c.comm "All users info FTL">
    <#list users as user>
        <@usInf.userInfoName user/>
        <h3>-----------------------------</h3>
    </#list>
</@c.comm>