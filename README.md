<h1>Api para consulta de frete de acordo com a região</h1>
<hr>
<p>Através desta api é possível buscar os valores dos fretes de cada região do Brasil,
     também é possível alterar o preço dos fretes e buscar todos os valores.</p>
     
<hr>
<h2>Tecnologias utilizadas</h2>
<ul>
    <li>Java 11</li>
    <li>Spring</li>
    <li>H2 DataBase</li>
    <li>RestTemplate</li>
    <li>Swagger</li>
    <li>Junit 5 e Mockito</li>
    <li>Cucumber</li>
    <li>FeignClient</li>
    <li>Docker</li>
</ul>

<hr>
<h2>Como Rodar aplicação:</h2>
<h3>Sem o docker</h3>
<p>Clone o projeto deste repositório, depois inicie a aplicação e acesse os end-points aravés do postman, por exemplo.</p>

<h3>Com o docker</h3>
<p>Clone o repositório, abra a aplicação na pasta raiz onde possui o file dockerfile e execulte os seguintes comandos:</p>
<li>"docker build ."</li>
<li>"docker run {codigo da imagem aqui} -p 8081:8081"</li>
<li>Espere a aplicação subir e acesse os end points</li>


<hr>

<h2>Como acessar swagger</h2>
<p>Acesso no navegador o link com a aplicação levantada <a href="http://localhost:8081/swagger-ui/index.html">http://localhost:8081/swagger-ui/index.html</a></p>
