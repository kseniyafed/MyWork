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
               
            }
            
        </style>
    </head>
    <body>
        <body bgcolor="#ADD8E6">
        <div class="block1">
            <a href="/">${login} Выход</a>
        </div>
        <h1 align="center"><font color="#191970">Темы</font></h1>
        <div class="block2">
            
            <#list subjects as subject>
                <h2> 
                    <font color="#191970">${subject.number}.
                        <a href="/teoryPage/${subject.name}">${subject.name}</a>
                    </font>
                </h2>     
            </#list>

        </div>    
    </body>
        
</html>
