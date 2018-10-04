<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add user</title>
</head>
<body>
<table>
    <#if users?has_content>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
        </tr>
        <#list users as user>
            <tr>
                <td>
                    ${user.name}
                </td>
                <td>
                    ${user.surname}
                </td>
                <td>
                    ${user.email}
                </td>
            </tr>
        </#list>
    <#else>
        There are no users yet
    </#if>
</table>
</body>
</html>