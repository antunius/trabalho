<html>
<head>
    <meta charset="UTF-8"/>
    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: sans-serif;
        }

        .header {
            width: 100%;
            background: linear-gradient(to right, #3b78e7, #3b78e7);
        }

        .headerTitulo {
            font-weight: bold;
            padding: 35px;
            color: #FFF;
        }

        .headerImg {
            padding: 0;
            font-family: sans-serif;
            position: relative;
            height: 25%;
            float: left;
            margin-top: 30px;
            margin-right: 30px;
        }

        .headerDiv {
            height: 87px;
            float: right;
            padding: 10px;
        }

        .article {
            padding: 30px;
            background-color: #f5f5f5;
            color: #333;
        }

        .link {
            text-decoration: none;
            color: #3b78e7;
        }

        .footer {
            padding: 30px;
        }

        .footerP {
            font-size: 12px;
            font-family: sans-serif;
            color: #666;
        }
    </style>
</head>
<body>
<div class="header">
    <h1 class="headerTitulo">Definir senha</h1>
</div>
<div>
    <div class="article">
        <p>Olá, ${nome}</p>
        <br/><br/>
        <p>
            Para redefinir sua senha, utilize o código:
        </p>
        <br/>
        <p style="padding: 10px; font-size: 26px; background-color: #CCC; width: 500px; text-align: center; font-weight: bold;">
            ${codigo}
        </p>
        <br/><br/>
    </div>
</div>
<div class="footer"><p class="footerP">Atenção! Este e-mail não pode receber respostas.</p></div>
</body>
</html>
