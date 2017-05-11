<!DOCTYPE html>
<html>
    <head>
        <title>TestSystem</title>
        <meta charset="UTF-8">
     <style>
            .block1 { 
                width:95%;
                padding: 10px;  
                font-size: 80%; 
                text-align: right
            }
            .block2 { 
                width:95%;
                padding: 10px;  
                font-size: 100%; 
                text-align: center
            }
            
        </style>
    </head>
    <body>
        <body bgcolor="#ADD8E6">
        <div class="block1">
            <a href="/">${login} Выход</a>
        </div>
        
        <div class="block2">
            <h1 align="center"><font color="#191970">Группы</font></h1>
            <#list groups as group>          
                <h2><a href="/groupPage/${group.idGroup}">${group.name}</a></h2>
            </#list>
        </div>   
  
    </body>
</html>               

