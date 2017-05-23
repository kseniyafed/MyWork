<!DOCTYPE html>
<html>
    <head>
        <title>TestSystem</title>
        <meta charset="UTF-8">
        <style>
            .block1 { 
                width:100%;
                height:25%;
                padding: 15px;  
                background-color: #191970;
                color: #ADD8E6; 
                font-size: 250%;   
            }
            .block2{    
                width: 70%;
                height: 100px;
                padding: 10px;
                position:relative;
                left:30%;
                top:25px;
                color: #191970; 
                font-size: 250%;
            }
        </style>
    </head>
    <body>
        <body bgcolor="#ADD8E6">
        <div class="block1">
            <h1 align="center">Вход</h1>
        </div>
        <div style="padding: 15px">
          
             <H2 align="center"><font color="#191970">Введите логин и пароль</h2></font>

             <#if err??>
                <H3 align="center"><font color="red">${err}</h3>
             
             </#if>
        </div>
        <form action="/authorize" method="POST">
            <div class="block2">
                <label for="login">Логин: &nbsp</label>
                <input type="text" class="form-control" id="login" name="login" placeholder="Enter login" style="font-size: 30px"></input>
            </div>
            <div class="block2">
                <label for="password">Пароль:</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" style="font-size: 30px"></input>
            </div>
            <p style="text-align: center">
                <button type="submit" >Войти</button>
            </p>
         
        </form>

    </body>
</html>