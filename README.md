#Projeto finalizado com autenticaçao e um Rest Service.

#Endpoints:

Login
http://localhost:8080/login 

- Retorna Token No Header do Response


#WS Crawler

http://localhost:8080/api/capturarFeed  

- Passando o token de acesso no Authorization do Header para recuperar a lista de feeds da url informada.

#Docker

Projeto encontra com conteinerizado, basta um mvn docker:build para que o mesmo gere a imagem para a execução no docker.