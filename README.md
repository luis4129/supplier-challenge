# Informaçoes sobre o projeto

*Descrição:*

- Cadastro de fornecedores, desenvolvido utilizando Java com SpringBoot, e AngularJS.

# Pré-requisitos

- Maven. Link para download: https://www-us.apache.org/dist/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz
- Git. Link para downlaod: https://git-scm.com/download/win

# Baixando o repositório

Abra o Git Bash no diretório que deseja baixar o repositório e digite o comando abaixo:
- $ git clone https://github.com/luis4129/supplier-challenge-api.git

# Gerando o JAR

Caso ainda estiver com o Git Bash utilizado anteriormente, execute o comando abaixo para entrar no diretório do repositório clonado:
- $ cd supplier-challenge

Caso contrário, basta abrir outro Git Bash no diretório do repositório clonado.

Após isso, basta executar o comando abaixo para gerar o JAR do repositório (Nesse momento os testes de integração serão executados):
- $ mvn package

# Executando os testes de integração manualmente (Opcional)

Caso deseje rodar os testes de integração separadamente, basta rodar o comando abaixo:
- $ mvn test

# Executando o JAR

Agora só falta executar, execute o comando abaixo para entrar no diretório do JAR criado:
- $ cd target

Agora basta executar o JAR, com o comando abaixo:
- $ java -jar supplier-challenge-0.0.1-SNAPSHOT.jar

# Pronto! Agora é só acessar o link: http://localhost:8080/