<h1 class="pageheader">
    <div class="headerImage">
        <a class="headerImage" "href="/students"><img width="185" src="/images/SUKRUPA-LOGO-final.png"
                                                      alt="Sukrupa"></a>
    </div>

    $if (titleLine2)$
    <div class="twoLinesTitle">
        $title$
        <br/>
        $titleLine2$
    </div>
    $else$
    <div class="oneLineTitle">
        $title$
    </div>
    $if (!login)$
    <div class="logout">
        <a href="/authentication/logout">Logout</a>
    </div>
    $endif$
    $endif$
</h1>
