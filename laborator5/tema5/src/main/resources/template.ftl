<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalog Report</title>
</head>
<body>
<h1>Catalog Report</h1>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Location</th>
        <th>Tags</th>
    </tr>
    </thead>
    <tbody>
    <#list documents as document>
        <tr>
            <td>${document.id}</td>
            <td>${document.name}</td>
            <td>${document.location}</td>
            <td>
                <table>
                    <#list document.tags?keys as key>
                        <tr>
                            <td>${key}</td>
                            <td>${document.tags[key]}</td>
                        </tr>
                    </#list>
                </table>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
