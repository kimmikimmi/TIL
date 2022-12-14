- [Materials](#materials)
- [Basics](#basics)
  - [RDBMS and Java](#rdbms-and-java)
  - [ORM](#orm)
  - [JPA Programming: Setting JPA project](#jpa-programming-setting-jpa-project)
  - [JPA Programming: Entity mapping](#jpa-programming-entity-mapping)
  - [JPA Programming: Value type mapping](#jpa-programming-value-type-mapping)
  - [JPA Programming: 1 to n mapping](#jpa-programming-1-to-n-mapping)
  - [JPA Programming: Cascade](#jpa-programming-cascade)
  - [JPA Programming: Fetch](#jpa-programming-fetch)
  - [JPA Programming: Query](#jpa-programming-query)
  - [Introduction of JPA](#introduction-of-jpa)
  - [Core concepts](#core-concepts)
- [Advanced](#advanced)
  - [Introduction of JPA](#introduction-of-jpa-1)
  - [Spring Data Common: Repository](#spring-data-common-repository)
  - [Spring Data Common: Repository Interface](#spring-data-common-repository-interface)
  - [Spring Data Common: Handling Null](#spring-data-common-handling-null)
  - [Spring Data Common: Making a query](#spring-data-common-making-a-query)
  - [Spring Data Common: Async Query](#spring-data-common-async-query)
  - [Spring Data Common: Custom Repository](#spring-data-common-custom-repository)
  - [Spring Data Common: Basic Repository Customizing](#spring-data-common-basic-repository-customizing)
  - [Spring Data Common: Domain Event](#spring-data-common-domain-event)
  - [Spring Data Common: QueryDSL](#spring-data-common-querydsl)
  - [Spring Data Common: Web: Web Support Features](#spring-data-common-web-web-support-features)
  - [Spring Data Common: Web: DomainClassConverter](#spring-data-common-web-domainclassconverter)
  - [Spring Data Common: Web: Pageable and Sort Parameters](#spring-data-common-web-pageable-and-sort-parameters)
  - [Spring Data Common: Web: HATEOAS](#spring-data-common-web-hateoas)
  - [Spring Data Common: Summary](#spring-data-common-summary)
  - [Spring Data JPA: JPA Repository](#spring-data-jpa-jpa-repository)
  - [Spring Data JPA: Saving Entity](#spring-data-jpa-saving-entity)
  - [Spring Data JPA: Query method](#spring-data-jpa-query-method)
  - [Spring Data JPA: Query method Sort](#spring-data-jpa-query-method-sort)
  - [Spring Data JPA: Named Parameter and SpEL](#spring-data-jpa-named-parameter-and-spel)
  - [Spring Data JPA: Update query method](#spring-data-jpa-update-query-method)
  - [Spring Data JPA: EntityGraph](#spring-data-jpa-entitygraph)
  - [Spring Data JPA: Projection](#spring-data-jpa-projection)
  - [Spring Data JPA: Specifications](#spring-data-jpa-specifications)
  - [Spring Data JPA: Query by Example](#spring-data-jpa-query-by-example)
  - [Spring Data JPA: Transaction](#spring-data-jpa-transaction)
  - [Spring Data JPA: Auditing](#spring-data-jpa-auditing)
  - [Spring Data JPA: Summary](#spring-data-jpa-summary)

----

# Materials

* [????????? ??????????????? ?????? ?????? @ inflearn](https://www.inflearn.com/course/spring-framework_core/)
* [?????? ORM ?????? JPA ???????????????:????????? ????????? ?????? ??????????????? ????????? ???????????? ?????? ?????????????????? ????????? - ?????????](https://www.coupang.com/vp/products/20488571?itemId=80660090&vendorItemId=3314421212&q=%EA%B9%80%EC%98%81%ED%95%9C+JPA&itemsCount=4&searchId=13ac45f1095144b5bd41dfc0783f0478&rank=0&isAddedCart=)
  * [src](https://github.com/holyeye/jpabook)

# Basics

## RDBMS and Java

* dependency

```xml
<dependency>
  <groupId>org.postgresql</groupId>
<artifactId>postgre
```

* run postgres

```bash
$ docker run -p 5432:5432 -e POSTGRES_PASSWORD=xxxx -e POSTGRES_USER=iamslash -e POSTGRES_DB=basicdb --name my-postgres -d postgres

$ docker exec -i -t my-postgres

$ su - postgres

$ psql basicdb
\list
\dt
SELECT * FROM account;
```

* Java

```java
public class Appliation {
  public static void main(String[] args) throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/basicdb";
    String username = "iamslash";
    String password = "xxxx";

    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      System.out.println("Connection created: " + connection);
      String sql = "INSERT INTO ACCOUNT VALUES(1, 'iamslash', 'xxxx')";
      try (PreparedStatement statement = connection.prepareStatement(
        statement.execute());)
    }
  }
}
```

* Cons
  * Have to handle connection pools.
  * SQL is different depends on RDMBS server.
  * It's not easy to use lazy query.

## ORM

* Using Domain models

```java
Account account = new Account("iamslash", "xxxx");
accountRepository.save(account);
```

* Pros
  * Can use OOP.
  * Can use design pattern.
  * Can reuse codes.

* In a nutshell, object/relational mapping is the automated (and transparent) persistence of objects in a Java application to the tables in an SQL database, using metadata that describes the mapping between the classes of the application and the schema of the SQL database.
  * Java Persistence with Hibernate, Second Edition

Using JPA is better than Using JDBC.

## JPA Programming: Setting JPA project

## JPA Programming: Entity mapping

## JPA Programming: Value type mapping

## JPA Programming: 1 to n mapping

## JPA Programming: Cascade

## JPA Programming: Fetch

## JPA Programming: Query

## Introduction of JPA

## Core concepts

# Advanced

## Introduction of JPA

## Spring Data Common: Repository

## Spring Data Common: Repository Interface

## Spring Data Common: Handling Null

## Spring Data Common: Making a query

## Spring Data Common: Async Query

## Spring Data Common: Custom Repository

## Spring Data Common: Basic Repository Customizing

## Spring Data Common: Domain Event

## Spring Data Common: QueryDSL

## Spring Data Common: Web: Web Support Features

## Spring Data Common: Web: DomainClassConverter

## Spring Data Common: Web: Pageable and Sort Parameters

## Spring Data Common: Web: HATEOAS

## Spring Data Common: Summary

## Spring Data JPA: JPA Repository

## Spring Data JPA: Saving Entity

## Spring Data JPA: Query method

## Spring Data JPA: Query method Sort

## Spring Data JPA: Named Parameter and SpEL

## Spring Data JPA: Update query method

## Spring Data JPA: EntityGraph

## Spring Data JPA: Projection

## Spring Data JPA: Specifications

## Spring Data JPA: Query by Example

## Spring Data JPA: Transaction

## Spring Data JPA: Auditing

## Spring Data JPA: Summary




