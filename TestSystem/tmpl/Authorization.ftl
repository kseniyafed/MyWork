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
        width: 100%;
        height: 100px;
        padding: 10px;
        position:relative;
        left:27%;
        top:25px;
        font-size: 250%;
}
.myButton {
	-moz-box-shadow: 0px 0px 0px 2px #9fb4f2;
	-webkit-box-shadow: 0px 0px 0px 2px #9fb4f2;
	box-shadow: 0px 0px 0px 2px #9fb4f2;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #7892c2), color-stop(1, #476e9e));
	background:-moz-linear-gradient(top, #7892c2 5%, #476e9e 100%);
	background:-webkit-linear-gradient(top, #7892c2 5%, #476e9e 100%);
	background:-o-linear-gradient(top, #7892c2 5%, #476e9e 100%);
	background:-ms-linear-gradient(top, #7892c2 5%, #476e9e 100%);
	background:linear-gradient(to bottom, #7892c2 5%, #476e9e 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#7892c2', endColorstr='#476e9e',GradientType=0);
	background-color:#7892c2;
	-moz-border-radius:10px;
	-webkit-border-radius:10px;
	border-radius:10px;
	border:1px solid #4e6096;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
        position:relative;
        left:77%;
        top:140px;
	font-family:Arial;
	font-size:19px;
	padding:12px 37px;
	text-decoration:none;
	text-shadow:0px 1px 0px #283966;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #476e9e), color-stop(1, #7892c2));
	background:-moz-linear-gradient(top, #476e9e 5%, #7892c2 100%);
	background:-webkit-linear-gradient(top, #476e9e 5%, #7892c2 100%);
	background:-o-linear-gradient(top, #476e9e 5%, #7892c2 100%);
	background:-ms-linear-gradient(top, #476e9e 5%, #7892c2 100%);
	background:linear-gradient(to bottom, #476e9e 5%, #7892c2 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#476e9e', endColorstr='#7892c2',GradientType=0);
	background-color:#476e9e;
}
.myButton:active {
	position:relative;
	top:1px;
}
}
   
</style>
    </head>
    <body>
        <div class="block1">
            <h1 align="center">Вход</h1>
        </div>
        <div style="padding: 15px">
             <H2 align="center"><font color="#191970">Введите логин и пароль</h3></font>
        </div>
        <div class="block2">
             <label>Логин: &nbsp</label>
             <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Enter login" style="font-size: 30px"></input>
        </div>
        <div class="block2">
            <label>Пароль:</label>
              <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Enter password" style="font-size: 29px"></input>
        </div>
        <div>
             <button class="myButton">Войти</button>
        </div>
    </body>
</html>