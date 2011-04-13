<html>
<head>
    <title>Health Check</title>
    <link rel="stylesheet" type="text/css" href="/styles/healthCheck.css">
</head>
<body>
<h2>Application Health Check</h2>

<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Value</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>

    $healthCheckItems: {
    <tr>
        <td>$it.description$</td>
        <td>$it.symptom$</td>
        <td></td>
    </tr>
    }$

    $healthCheckTests:{
    <tr>
        <td>$it.description$</td>
        <td>$it.symptom$</td>
        <td>
            $if(it.healthy)$
            <img src="images/tick_icon.png" width="30px"/>
            $else$
            <img src="images/warning.png" width="30px"/>
            $endif$
        </td>
    </tr>
    }$

    </tbody>
</table>

</body>


</html>