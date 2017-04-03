<!DOCTYPE html>
<html>
    <head>
        <title>TestSystem</title>  
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
                font-size: 180%; 
               
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
            <table class="table table-hover">
                <tbody>
                    <#list subjects as subject>
                        <tr>
                            <td> <font color="#191970">${subject.number}.</font></td><td><a href="/teoryPage/${subject.name}">${subject.name}</a></td>
                        </tr>
                    </#list>
                </tbody>
            </table>
                </body>
        </div>
</html>
