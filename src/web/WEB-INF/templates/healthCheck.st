<html>
<head>
    <title>Application Health Check</title>
    <link rel="stylesheet" type="text/css" href="/styles/healthCheck.css">
</head>
<body>
<h3>Health Check Items</h3>
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

<hr />

<h3>System properties</h3>

    <table>
        <thead>
            <tr>
                <th>Key</th>
                <th>Value</th>
            </tr>
        </thead>
        <tbody>
            $systemProperties:{
                <tr>
                    <td>$it.key$</td>
                    <td>$it.value$</td>
                </tr>
            }$
        </tbody>
    </table>

<hr />

<h3>Class path</h3>

    <ul>
        $classPath:{
            <li>$it$</li>
        }$
    </ul>
</body>
</html>