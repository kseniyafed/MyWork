<!DOCTYPE html>
<html>
 <head>
    <meta charset="utf-8">
    <title>TestSystem</title>
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
                left:5%;
                top:25px;
                color: #191970; 
                font-size: 150%;
            }
    </style>
  
 </head>
 <body>
   <body bgcolor="#ADD8E6">
    
   <h1 align="center"><font color="#191970">Тест</font></h1>
    <form action="/resultPage" method="POST">
        <#list questions as question>
            <div class="block2">
                <label for=${question.idQuestion}}>${question.number}.${question.formulation}
                </label>
                <input type="text" class="form-control" id=${question.idQuestion} 
                            name=${question.idQuestion} autocomplete="off" style="font-size: 20px">
                </input>
            </div>
        </#list>
        <p style="text-align: center">
                <button type="submit" >Завершить тест</button>
        </p>
            
    </form>      
 </body>
</html>