# ProjetoDacc Maven With Db
- Este projeto é continuação da disciplina de Orientação e Objetos
- Agora vamos melhorá-lo com:
    - Projeto Maven
    - Estruturação da arquitetura
    - Banco de dados
    - Framework JPA-Hibernate

### Maven
- Por ser um projeto Maven (gerenciador de dependencias atual) fique atento a algumas configurações no arquivo POM.xml
- Todas as dependencias que seu projeto serão colocadas neste arquivo XML 
- Caso necessite de alguma outra busque no repositorio central 
- https://search.maven.org/ ou https://mvnrepository.com/

### Projeto Escola
- Como é um projeto didático:
- O cadastro de Professor:
    - Está usando arquivo texto
- O cadastro de Aluno: 
    - Está usando banco de dados sqlite
- O cadastro de Funcionario:
    - Está usando JPA-Hibernate
    - Usando o banco de dados Mysql
    - Por comodidade não coloquei Funcionario como descendente de Pessoa

### Padrão MVC
- Como estamos utilizando o padrão MVC, é possível perceber como as camadas VIEW e CONTROLLER permanecem 
inalteradas nas diferentes abordagens

### Padrão Factory
- Está sendo utilizado tanto na conexão com o DB Sqlite 
- Quanto na criação EntityManager do Hibernate

### Herança, interface e polimorfismo
- Estes conceitos estão sendo utilizados na camada MODEL, especificamente DAO
- Exatamente para que você veja como as camadas VIEW e CONTROLLER permanecem intáctas quando se tem uma camada MODEL padronizada;

### Hibernate e Mysql
- O hibernate está configurado para funcionar com classe funcionario.
- Este é um exemplo didático
- Atenção: Neste ponto estamos usando o banco de dados Mysql
	- Aqui o banco de dados foi alterado apenas por questões didáticas. Para que o aluno veja uma forma diferente de fazer a mesma coisa.
	- Para funcionar esta parte no seu computador, voce deve ter instalado e configurado o Mysql na sua maquina. 
	- É necessário criar um banco de dados com o nome 'db001' vazio, sem nada que o JPA-Hibernate construirá as tabelas necessárias para você :)

- Caso queira mudar o nome do banco de dados:
	- Basta alterar o nome no arquivo persistence.xml
	- 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence     http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
  <persistence-unit name="exemplo-jpa" transaction-type="RESOURCE_LOCAL">
    <properties>
      <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost/db0001?useSSL=false&amp;serverTimezone=UTC"/>
      <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
      <property name="javax.persistence.jdbc.user" value="root"/>
      <property name="javax.persistence.jdbc.password" value="password"/>
      <property name="hibernate.hbm2ddl.auto" value="update"/>
      <!-- https://docs.jboss.org/hibernate/orm/5.4/javadocs/org/hibernate/dialect/package-summary.html -->
      <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
      
      <property name="hibernate.format_sql" value="true"/>
      <property name="hibernate.show_sql" value="true"/>
      
    </properties>
  </persistence-unit>
</persistence>
```

