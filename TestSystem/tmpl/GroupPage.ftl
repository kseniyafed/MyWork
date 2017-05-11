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
            <h1><font color="#191970">Отчет по группе ${group}</font><h1>
        </div>

        <table class="table table-hover">
            <thead>
                
                <tr>
                    <th>Имя</th>

                    <#list subjects as subject>
                            <th>Тема №${subject.number}</th>
                    </#list>
                </tr>
            
            </thead>
            <tbody>
                 <#list results as result>
                    <tr>
                            <th>${result.name}</th> 
                            <#list result.marks as mark>
                                     <th>${mark}</th>
                            </#list>
                    </tr>
                 </#list>
            </tbody>
        </table>
        <p style="text-align: center">
                <input type="button" onclick="history.back();" value="Назад"/>
        </p>
    </body>
</html>               


