<!DOCTYPE html>
<html>
    <head>       
        <title>TestSystem</title>
        <meta charset="UTF-8">
        <style>
            .block1 { 
                width:95%;
                padding: 10px;  
                font-size: 11pt;
                text-align: right
            }
            .block2{    
                
                color: #191970; 
                font-size: 550%;
            }
            .c {
                border: 1px solid #333; /* Рамка */
                display: inline-block;
                padding: 5px 15px; /* Поля */
                text-decoration: none; /* Убираем подчёркивание */
                color:#191970; /* Цвет текста */
                background-color:white;
  
        </style>
        
    </head>
    <body>
        
        <div class="block1">
            <a href="/">${login} Выход</a>
        </div>
        <body bgcolor="#ADD8E6">
        <#if err??>
                <H1 align="center"><font color="red">${err}</h3>
                <H1 align="center"><font color="red">Ваша оценка:</h3>
                <div class="block2">
                     <h1 align="center">${mark}</h1>
                </div>  
                <p style="text-align: center">
                <input type="button" onclick="history.back();" value="Назад"/>
                </p>
        <#else>
                
                <h1 align="center"><font color="#191970">Теория</font></h1>
                <h1 align="center"><font color="#191970">${subject.name}</font></h1>
                <div class="block1">
                    <h1 align="justify"><font color="#191970">${subject.teory}</font></h1>
                </div>
                <p style="text-align: center">
                    <a href="/testPage" class="c">Пройти тест</a>
                </p>
        
        </#if>

    </body>
</html>
