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

<h3>Requisitos para rodar a aplicação</h3>
<ul>
    <li>Java 11</li>
    <li>Maven</li>
    <li>Docker (Caso queira, é possível inicia-la sem o docker também)</li>
    <li>Qualquer Ide como InteliJ ou eclipse</li>
</ul>

<hr>

<h2>Como Rodar aplicação:</h2>

<h3>Sem o docker</h3>
<p>Clone o projeto deste repositório, depois inicie a aplicação e acesse os end-points através do postman, por exemplo.</p>

<h3>Com o docker</h3>
<p>Clone o repositório, abra a aplicação na pasta raiz onde possui o file dockerfile e execulte os seguintes comandos:</p>
<li>mvn clean package</li>
<li>"docker build -t {nome da imagem aqui}  ."</li>
<li>"docker run --name {nome do container aqui} -p 8081:8081 { IMAGE ID  aqui}"</li>
<li>Espere a aplicação subir e acesse os end points</li>


<hr>

<h2>Como acessar swagger</h2>
<p>Acesso no navegador o link <a href="http://localhost:8081/swagger-ui/index.html">http://localhost:8081/swagger-ui/index.html</a></p>
