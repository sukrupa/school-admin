
<div id="login">
    <form id="loginForm" name="loginForm" action="/j_security_check" method="post">
        <div class="loginEntry">
            <div class="label">Username:<span class="mandatory">*</span></div>
            <input type="text" id="j_username" name="j_username" class="loginEntry"></input>
        </div>
        <div class="loginEntry">
            <div class="label">Password:<span class="mandatory">*</span></div>
            <input type="password" id="j_password" name="j_password" class="loginEntry"></input>
        </div>
        <div id="errorMessages">
            $if (!success)$
                Invalid username and password
            $endif$
        </div>
        <div id="update">
            <input type="submit" id="loginButton" value="Login"/>
        </div>
    </form>
</div>
