<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
        "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>$title$</title>
    <script type="text/javascript" src="/scripts/jquery.js"></script>
    $scripts: {
    <script type="text/javascript" src="/scripts/$it$.js"></script>
    }$
    <link rel="stylesheet" type="text/css" href="/styles/styles.css"/>
    $stylesheets: {
    <link rel="stylesheet" type="text/css" href="/styles/$it$.css"/>
    }$
</head>
<body>

<div id="page">
    <div id="header">
        $header()$
    </div>
    <div id="columns">

        <div id="basicBody">
            $body$
        </div>
    </div>
</div>

</body>
</html>