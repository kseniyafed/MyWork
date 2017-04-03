<!DOCTYPE html>
<html>
    <head>
        <title>TestSystem</title>
        <style>
            .block1 { 
                width:95%;
                padding: 10px;  
                font-size: 11pt;
                text-align: right
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
        <body bgcolor="#ADD8E6">
        <h1 align="center"><font color="#191970">Теория</font></h1>
        <h1 align="center"><font color="#191970">${subject.name}</font></h1>
        <div class="block1">
            <h1 align="justify"><font color="#191970">${subject.teory}</font></h1>
        </div>
        <p style="text-align: center">
                <a href="/" class="c">Пройти тест</a>
            </p>
        
    </body>
</html>
