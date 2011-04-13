<html>
<head>
    <title>Health Check</title>
    <link rel="stylesheet" type="text/css" href="/styles/healthCheck.css">
</head>
<body>
<h2>Application Health Check</h2>

$buildInfo:{
<div style="height: 38px; ">
    $it$
</div>
}$

$healthCheckItems: {
<div style="height: 38px;">
    <div style="float:left; padding-top:10px; width:150px;">
         $it.description$:
    </div>
    <div style="float:left; padding-top:10px; width: 300px;">
        $it.symptom$
    </div>
</div>
<br style="clear:both;" />
}$

<div>
    $healthCheckTests:{

    <div style="float:left; padding-top:10px; width:150px;">
        $it.description$:
        </div>
    <div style="float:left; padding-top:10px; width: 300px;">
        $it.symptom$
    </div>
    <div>
        $if(it.healthy)$
        <img src="images/tick_icon.png" width="2%"/>
        $else$
        <img src="images/warning.png" width="2%"/>
        $endif$
    </div>
    }$
</div>

</body>


</html>