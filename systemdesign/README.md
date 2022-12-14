- [Abstract](#abstract)
- [References](#references)
- [Materials](#materials)
- [Prerequisites](#prerequisites)
- [Principles](#principles)
  - [How to approach a system design interview question](#how-to-approach-a-system-design-interview-question)
  - [Distributed System](#distributed-system)
  - [Scalability](#scalability)
  - [Performance vs scalability](#performance-vs-scalability)
  - [Latency vs throughput](#latency-vs-throughput)
  - [Availability vs consistency](#availability-vs-consistency)
    - [CAP (Consistency Availability Partition tolerance)](#cap-consistency-availability-partition-tolerance)
    - [PACELC (Partitioning Availability Consistency Else Latency Consistency)](#pacelc-partitioning-availability-consistency-else-latency-consistency)
  - [Consistency patterns](#consistency-patterns)
  - [Availability patterns](#availability-patterns)
  - [Domain name system](#domain-name-system)
  - [Content delivery network](#content-delivery-network)
  - [Load balancer](#load-balancer)
  - [Reverse proxy](#reverse-proxy)
  - [Application layer](#application-layer)
    - [MSA (Micro Service Architecture)](#msa-micro-service-architecture)
    - [Service Mesh](#service-mesh)
    - [Service discovery](#service-discovery)
  - [Database](#database)
  - [Cache](#cache)
  - [Asynchronism](#asynchronism)
  - [Communication](#communication)
    - [TCP](#tcp)
    - [UDP](#udp)
    - [RPC](#rpc)
    - [REST (REpresentational State Transfer) API](#rest-representational-state-transfer-api)
    - [RPC VS REST](#rpc-vs-rest)
  - [Security](#security)
    - [WAF (Web Application Fairewall)](#waf-web-application-fairewall)
    - [XSS (Cross Site Scripting)](#xss-cross-site-scripting)
    - [CSRF (Cross Site Request Forgery)](#csrf-cross-site-request-forgery)
    - [XSS vs CSRF](#xss-vs-csrf)
    - [CORS (Cross Origin Resource Sharing)](#cors-cross-origin-resource-sharing)
  - [Database Primary Key](#database-primary-key)
  - [Idempotency](#idempotency)
  - [80/20 rule](#8020-rule)
  - [70% Capacity model](#70-capacity-model)
- [Grokking the System Design Interview Practices](#grokking-the-system-design-interview-practices)
- [System Design Primer Practices](#system-design-primer-practices)
- [Essential Micro Services](#essential-micro-services)
- [Additional System Design Interview Questions](#additional-system-design-interview-questions)
- [Real World Architecture](#real-world-architecture)
- [Company Architectures](#company-architectures)
- [company engineering blog](#company-engineering-blog)
- [Cloud Design Patterns](#cloud-design-patterns)
- [Cracking The Coding Interview Quiz](#cracking-the-coding-interview-quiz)

----

# Abstract

- ????????? ???????????? ?????? ????????????. [system deisgn primer](https://github.com/donnemartin/system-design-primer#federation)
  ??? ?????? ??? ?????? ??? ????????? ????????? ?????? ???????????? ????????? ??????.

<p align="center">
  <img src="http://i.imgur.com/jj3A5N8.png"/>
  <br/>
</p>

# References

* [cloudcraft](https://cloudcraft.co/app)
  * aws architcture diagram tool
* [draw.io](https://www.draw.io/)
  * architecture ??? ????????? ????????? diagram tool
* [webwhiteboard](https://www.webwhiteboard.com/)
  * web white board for system design interview 

# Materials

* [A pattern language for microservices](https://microservices.io/patterns/index.html)
  - microservices ??? ????????????
  - [src](https://github.com/gilbutITbook/007035) 
* [system deisgn primer](https://github.com/donnemartin/system-design-primer#federation)
  - ????????? ?????? ?????? ????????? ???????????? ??? ?????????.
* [Grokking the System Design Interview](https://www.educative.io/collection/5668639101419520/5649050225344512)
  - ?????? ????????? ????????? ?????????
* [Grokking the Object Oriented Design Interview](https://www.educative.io/collection/5668639101419520/5692201761767424)
  - ?????? OOD ????????? 
* [Here are some of the favorite posts on HighScalability...](http://highscalability.com/all-time-favorites/)
  * great case studies
* [FullStack cafe](https://www.fullstack.cafe/)
* [AWS @ TIL](/aws/README.md)
* [Mastering Chaos - A Netflix Guide to Microservices](https://www.youtube.com/watch?v=CZ3wIuvmHeM)
* [cracking the coding interview](http://www.crackingthecodinginterview.com/)
* [Designing Data-Intensive Applications](https://dataintensive.net/)
- [Azure Cloud Patterns](https://docs.microsoft.com/en-us/azure/architecture/patterns/)
  - [infographic](https://azure.microsoft.com/en-us/resources/infographics/cloud-design-patterns/)
- [AWS Architect](https://aws.amazon.com/ko/architecture/)
- [GCP Solutions](https://cloud.google.com/solutions/)

# Prerequisites

- Numbers
  - [Names of large numbers](https://en.wikipedia.org/wiki/Names_of_large_numbers)
  - [SI ?????? ??????](https://ko.wikipedia.org/wiki/SI_%EA%B8%B0%EB%B3%B8_%EB%8B%A8%EC%9C%84)
  - SI ??? System International ??? ???????????? ?????? ??????
  
| Value | short-scale | SI-symbol | SI-prefix |
| ----- | ----------- | --------- | --------- |
| 10^3  | Thousand    | K         | Killo-    |
| 10^6  | Million     | M         | Mega-     |
| 10^9  | Trillion    | G         | Giga-     |
| 10^12 | Quadrillion | T         | Tera-     |
| 10^15 | Quintillion | P         | Peta-     |
| 10^18 | Sextillion  | E         | Exa-      |
| 10^21 | Septillion  | Z         | Zeta-     |
| 10^24 | Octillion   | Y         | Yota-     |

- powers of two table

```
Power           Exact Value         Approx Value        Bytes
---------------------------------------------------------------
7                             128
8                             256
10                           1024   1 thousand           1 KB
16                         65,536                       64 KB
20                      1,048,576   1 million            1 MB
30                  1,073,741,824   1 billion            1 GB
32                  4,294,967,296                        4 GB
40              1,099,511,627,776   1 trillion           1 TB
```

- latency numbers every programmer should know

```
Latency Comparison Numbers
--------------------------
L1 cache reference                           0.5 ns
Branch mispredict                            5   ns
L2 cache reference                           7   ns                      14x L1 cache
Mutex lock/unlock                          100   ns
Main memory reference                      100   ns                      20x L2 cache, 200x L1 cache
Compress 1K bytes with Zippy            10,000   ns       10 us
Send 1 KB bytes over 1 Gbps network     10,000   ns       10 us
Read 4 KB randomly from SSD*           150,000   ns      150 us          ~1GB/sec SSD
Read 1 MB sequentially from memory     250,000   ns      250 us
Round trip within same datacenter      500,000   ns      500 us
Read 1 MB sequentially from SSD*     1,000,000   ns    1,000 us    1 ms  ~1GB/sec SSD, 4X memory
Disk seek                           10,000,000   ns   10,000 us   10 ms  20x datacenter roundtrip
Read 1 MB sequentially from 1 Gbps  10,000,000   ns   10,000 us   10 ms  40x memory, 10X SSD
Read 1 MB sequentially from disk    30,000,000   ns   30,000 us   30 ms 120x memory, 30X SSD
Send packet CA->Netherlands->CA    150,000,000   ns  150,000 us  150 ms

Notes
-----
1 ns = 10^-9 seconds
1 us = 10^-6 seconds = 1,000 ns
1 ms = 10^-3 seconds = 1,000 us = 1,000,000 ns
```

![](img/latency_numbers_every_programmer_should_know.png)

- time

| years | days | hours |    mins |       secs |
| ----: | ---: | ----: | ------: | ---------: |
|     1 |  365 | 8,760 | 525,600 | 31,536,000 |
|       |    1 |    24 |   1,440 |     86,400 |
|       |      |     1 |      60 |      3,600 |
|       |      |       |       1 |         60 |

# Principles

## How to approach a system design interview question

* Outline use cases, constraints, and assumptions
* Create a high level design
* Design core components
* Scale the design

## Distributed System

* [Distributed System](/distributedsystem/README.md)

## Scalability

- vertical scaling
- horizontal scaling
- caching
- load balancing
- database replication
- database partitioning
- asynchronism

## Performance vs scalability

performance ??? ????????? ????????? single user ??? ?????? ???????????? ????????? ?????????. scalability ??? ????????? ????????? single user ??? ?????? ???????????? ????????? ????????? multi user ??? ?????? ???????????? ????????? ??? ?????????

## Latency vs throughput

Latency ??? ?????? action ??? ???????????? ????????? ??????????????? ????????? ????????????. Throughput ??? ?????? ????????? ???????????? ?????? ?????? ????????? ?????????.

## Availability vs consistency

### CAP (Consistency Availability Partition tolerance)

* [CAP Theorem @ medium](https://medium.com/system-design-blog/cap-theorem-1455ce5fc0a0)

----

![](/aws/img/1_rxTP-_STj-QRDt1X9fdVlA.jpg)

Brewer's theorem ???????????? ??????. Distributed System ??? Consistency, Availability, Partition tolerance ??? 3 ?????? ?????? ????????? ??? ??????. 2 ???????????? ???????????? ??????.

* Consistency
  * ?????? ?????????????????? ?????? ???????????? ?????????.
* Availability
  * ?????? ????????? ????????? ???????????? ???????????? ????????? ??????.
* Partition tolerance
  * ????????? ?????? ???????????? ???????????? ????????? ??????????????? ?????????????????? ???????????? ????????? ??????.

### PACELC (Partitioning Availability Consistency Else Latency Consistency)

* [CAP Theorem, ????????? ??????](http://eincs.com/2013/07/misleading-and-truth-of-cap-theorem/)

----

![](/aws/img/truth-of-cap-theorem-pacelc.jpg)

???????????? Partitioning ?????? ??? ???????????? ?????? ????????? ?????? Availability ?????? Consistency ??? ????????? ???????????? ???????????? ????????? ?????? Latency ?????? Consistency ??? ????????? ??????????????? ????????????. 

????????? ?????? ?????? ???????????? ?????????. ???????????? ?????? ????????? ??? ?????????????????? ???????????? ???????????? ????????? ?????? ???????????? ????????? ?????? ??????????????? ????????? ????????? ????????? ???????????? ?????????. ???????????? ????????? ?????? ????????? ????????? ??? ?????????????????? ???????????? ???????????? ?????? ????????? ?????? ??????????????? ????????? ????????? ?????? ???????????? ?????????.

* HBase ??? PC/EC ??????. ???????????? ??????????????? ??? ????????? ??????????????? ???????????? ????????? ????????? ????????? ?????? ????????? ??????????????? ???????????? ?????????. ???????????? ????????? ???????????????.
* Cassandra ??? PA/EL ??????. ???????????? ?????? ???????????? ??????. ???????????? ??????????????? ??? ???????????? ???????????? ???????????? ?????? ?????????. ????????? ????????? ??? ?????? ???????????? ???????????? ????????? ?????? ?????? ???????????? ?????????.

## Consistency patterns

* Weak consistency
  * write operation ?????? ??? ?????? read ??? ??? ????????? ????????? ??? ??????.
  * memcached ??? ????????????.
* Eventual consistency
  * write operation ?????? ????????? ???????????? ????????? ??? ?????? read ??? ??? ??????.
  * DNS, email ??? ????????????.
* Strong consistency
  * write operation ?????? ??? ?????? ?????? read ??? ??? ??????.
  * RDBMS

## Availability patterns

* Fail-over
  * Active-passive
    * LB ??? active ??? passive ??? health check ??????. acitve ??? ????????? ???????????? passive ??? active ?????????.
  * Active-active
    * active ??? ????????? ???????????? ????????? load ??? ????????????. DNS ??? ?????? active ??? IP ??? ???????????? ?????? ??????.
* Disadvanges of Fail-over 
  * Active ??? passive ??? data ??? replication ?????? ?????? ????????? ???????????? ?????? data ??? ????????? ??? ??????.
* Replication
  * Master-slave replication
  * Master-master replication
* Availabilty in numbers
  * availability ??? uptime ?????? downtime ??? percent ????????? ????????????. ?????? ?????? 99.9% (three 9s) ?????? 99.99% (four 9s) ????????? ????????????.

* 99.9% availability - three 9s

| Duration           | Acceptable downtime |
| ------------------ | ------------------- |
| Downtime per year  | 8h 45min 57s        |
| Downtime per month | 43m 49.7s           |
| Downtime per week  | 10m 4.8s            |
| Downtime per day   | 1m 26.4s            |

* 99.99% availability - four 9s

| Duration           | Acceptable downtime |
| ------------------ | ------------------- |
| Downtime per year  | 52min 35.7s         |
| Downtime per month | 4m 23s              |
| Downtime per week  | 1m 5s               |
| Downtime per day   | 8.6s                |

## Domain name system

<p align="center">
  <img src="http://i.imgur.com/IOyLj4i.jpg"/>
  <br/>
  <i><a href=http://www.slideshare.net/srikrupa5/dns-security-presentation-issa>Source: DNS security presentation</a></i>
</p>

????????? ?????? ??? ?????? domain name ??? ????????? ????????? ??? ?????? IP address ??? ???????????? ???????????????.

* **NS record (name server)** - Specifies the DNS servers for your domain/subdomain.
* **MX record (mail exchange)** - Specifies the mail servers for accepting messages.
* **A record (address)** - Points a name to an IP address.
* **CNAME (canonical)** - Points a name to another name or `CNAME` (example.com to www.example.com) or to an `A` record.

| name      | type  | value      |
| --------- | ----- | ---------- |
| a.foo.com | A     | 192.1.1.15 |
| b.foo.com | CNAME | a.foo.com  |

* [Online DNS Record Viewer](http://dns-record-viewer.online-domain-tools.com/)

## Content delivery network

<p align="center">
  <img src="http://i.imgur.com/h9TAuGI.jpg"/>
  <br/>
  <i><a href=https://www.creative-artworks.eu/why-use-a-content-delivery-network-cdn/>Source: Why use a CDN</a></i>
</p>

* Push CDNs
* Pull CDNs

## Load balancer

<p align="center">
  <img src="http://i.imgur.com/h81n9iK.png"/>
  <br/>
  <i><a href=http://horicky.blogspot.com/2010/10/scalable-system-design-patterns.html>Source: Scalable system design patterns</a></i>
</p>

* Active-passive
* Active-active
* Layer 4 load balancing
* Layer 7 load balancing
* Horizontal scaling

## Reverse proxy

* [Apache2 ?????? (Ubuntu 16.04)](https://lng1982.tistory.com/288)
  
-----

<p align="center">
  <img src="http://i.imgur.com/n41Azff.png"/>
  <br/>
  <i><a href=https://upload.wikimedia.org/wikipedia/commons/6/67/Reverse_proxy_h2g2bob.svg>Source: Wikipedia</a></i>
  <br/>
</p>

![](img/foward_reverse_proxy.png)

forward proxy ??? HTTP req ??? ???????????? ????????????. reverse proxy ??? HTTP ??? ????????? ????????????????????? HTTP req ??? ????????? back-end ??????????????? ????????????. L4, L7 ???????????? reverse proxy ?????? ??? ??? ??????. reverse ?????? ?????? ??? ?????????????????????

nginx, haproxy ??? ?????? `reverse proxy` ??? `L7` ?????? `load balaning` ?????? `SPOF (single point failure)` ??? ?????? ????????????.

## Application layer

<p align="center">
  <img src="http://i.imgur.com/yB5SYwm.png"/>
  <br/>
  <i><a href=http://lethain.com/introduction-to-architecting-systems-for-scale/#platform_layer>Source: Intro to architecting systems for scale</a></i>
</p>

???????????? ????????? ?????? layer ??? ?????? SPOF (Single Point of Failure) ??? ????????? ??? ??????.


### MSA (Micro Service Architecture)

* [Micro Service Architecture](msa.md)

-----

????????? ???????????? ???????????? ????????? ?????? ??????????????? ???????????? ???????????? software development technique ??? ????????????.

???????????? ?????? ?????? ?????? ??????????????? ????????? ?????? ??????. ????????? ?????? ?????? ??????????????? ???????????? ?????? ??????????????? ???????????? ?????? ??????????????? ???????????? ??????.

[A pattern language for microservices](https://microservices.io/patterns/index.html) ??? ???????????? pattern ?????? ????????????.

### Service Mesh

* [????????? ????????? ????????????????](https://www.redhat.com/ko/topics/microservices/what-is-a-service-mesh)

Service Mesh ??? MSA ??? ?????? ????????? ????????????. network ????????? ???????????? sidecar proxy ??? ?????? ????????????.

### Service discovery

* service ??? ip, port ?????? ???????????? ??????????????? ????????? ??? ??????.
* consul, etcd, zookeepr ??? ????????????.

## Database

<p align="center">
  <img src="http://i.imgur.com/Xkm5CXz.png"/>
  <br/>
  <i><a href=https://www.youtube.com/watch?v=w95murBkYmU>Source: Scaling up to your first 10 million users</a></i>
</p>

* RDBMS
  * ACID - set of properties of relational database transactions
    * Atomicity(?????????) - Each transaction is all or nothing
    * Consistency(?????????) - Any transaction will bring the database from one valid state to another
    * Isolation(?????????) - Executing transactions concurrently has the same results as if the transactions were executed serially
    * Durability(?????????) - Once a transaction has been committed, it will remain so.
  * Master-slave replication
  * Master-Master replication
* Federation
  * ???????????? ???????????? ??????. ??????????????? partitioning ??????.

<p align="center">
  <img src="http://i.imgur.com/U3qV33e.png"/>
  <br/>
  <i><a href=https://www.youtube.com/watch?v=w95murBkYmU>Source: Scaling up to your first 10 million users</a></i>
</p>

* Sharding
  * ???????????? ???????????? ??????. ????????? ???????????? ??????????????? partitioning ??????.
  * [consistent hashing @ TIL](/consistent_hasing/README.md)

<p align="center">
  <img src="http://i.imgur.com/wU8x5Id.png"/>
  <br/>
  <i><a href=http://www.slideshare.net/jboner/scalability-availability-stability-patterns/>Source: Scalability, availability, stability, patterns</a></i>
</p>

* Denormalization
  * [normalization @ TIL](/normalization/README.md)
* SQL Tuning
* NoSQL
  * Key-value store
  * Document store
  * Wide solumn store
  * Graph database

## Cache

<p align="center">
  <img src="http://i.imgur.com/Q6z24La.png"/>
  <br/>
  <i><a href=http://horicky.blogspot.com/2010/10/scalable-system-design-patterns.html>Source: Scalable system design patterns</a></i>
</p>

* Client caching
* CDN caching
* Web server caching
* Database caching
* Application caching
* Caching at the database query level
* CAching at the object level
* When to update the cache
  * Cache-Aside
    * ????????????????????? ?????? cache??? ????????????.

      ```python
      # reading values
      v = cache.get(k)
      if (v == null) {
        v = sor.get(k)
        cache.put(k, v)
      }

      # writing values
      v = newV
      sor.put(k, v)
      cache.put(k, v)
      ```
  * Read-through
    * `cache` ??? ?????? ???????????? `cache` ??? ???????????? ????????? ????????? ?????? ??? ??????
      `SOR(system of record)` ??? ?????? ???????????? ?????? ???????????? ????????????.
  
  * Write-through
    * `cache` ??? ?????? ???????????? `cache` ??? ???????????? `SOR(system of record)` ???
      ?????? ????????? ????????????.

  * Write-behind
    * `cache` ??? ?????? ???????????? ?????? ????????? ???????????? ????????? ????????????
      `SOR` ??? ????????????. `SOR` ????????? ???????????? ?????? ????????? ????????? ????????????.

## Asynchronism

<p align="center">
  <img src="http://i.imgur.com/54GYsSx.png"/>
  <br/>
  <i><a href=http://lethain.com/introduction-to-architecting-systems-for-scale/#platform_layer>Source: Intro to architecting systems for scale</a></i>
</p>

* Message Queues
  * Redis, RabbitMQ, Amazon SQS
* Task Queues
  * Celery
* Back pressure
  * MQ ??? ????????? client ?????? 503 Service Unavailable ??? ?????? ???????????? ??????????????? ????????????. ????????? circuit breaker ??????.

## Communication

* [network @ TIL](/network/README.md)

----

### TCP

TODO

### UDP

TODO

### RPC

TODO

### REST (REpresentational State Transfer) API 

* [1) Rest API???? @ edwith](https://www.edwith.org/boostcourse-web/lecture/16740/)
* [HTTP ???????????? ????????? ?????? GET, POST, PUT, PATCH, DELETE, TRACE, OPTIONS](https://javaplant.tistory.com/18)

----

2000 ????????? ?????? ?????? (Roy Fielding) ??? ???????????? ???????????? ????????? ???????????????. REST ????????? API ??? ?????????.

?????? ????????? ?????? ????????? REST API ?????? ???????????? ?????? ????????? REST API ??? ??????????????? ?????????. REST API ??? ????????? ?????? ????????? ???????????? ????????? ??????.

* client-server
* stateless
* cache
* uniform interface
* layered system
* code-on-demand (optional)

HTTP ??? ???????????? uniform interface ??? ??????????????? ?????? ?????? ??????. uniform interface ??? ????????? ????????????.

* ???????????? URI??? ???????????? ?????????.
* ???????????? ??????,??????,??????????????? ??? ??? HTTP???????????? ????????? ?????? ???????????? ?????????.
* ???????????? ????????? ????????? ??? ????????? ?????????. (Self-descriptive message)
* ????????????????????? ????????? Hyperlink??? ????????? ???????????? ?????????.(HATEOAS)

?????? ???????????? ?????? ??????????????? ????????? ???????????? HTTP ??? ???????????? ?????????. ?????? ?????? HTTP BODY ??? JSON ??? ???????????? ??? HTTP message ????????? body ??? ????????? ??????????????? ?????????. ????????? ??? ???????????? ????????? ???, ????????? ????????? ??????, ??????????????? ???????????? ????????? ??? ?????? ????????? ????????????.
????????????????????? ??? ???????????? ??? ????????? ??? ??? ?????? ????????? ????????????. ????????? ??? ???????????? ??????, ??? ????????? ????????? ????????? ????????? ???????????? ??? ??? ????????? ?????? HATEOAS (Hypermedia As The Engine Of Application State) ?????? ??????. HATEOAS ??? API ?????? ???????????? ?????? ?????????.

?????? HTTP ??? REST API ??? uniform interface ????????? ??? self-descriptive message, HATEOAS ??? ???????????? ???????????? ???????????? ???????????? ????????? ??? ??? ??????. ????????? REST API ?????? HTTP API ?????? WEB API ?????? ??????.

### RPC VS REST

| Operation                       | RPC                                                                                       | REST                                                         |
| ------------------------------- | ----------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| Signup                          | **POST** /signup                                                                          | **POST** /persons                                            |
| Resign                          | **POST** /resign<br/>{<br/>"personid": "1234"<br/>}                                       | **DELETE** /persons/1234                                     |
| Read a person                   | **GET** /readPerson?personid=1234                                                         | **GET** /persons/1234                                        |
| Read a person???s items list      | **GET** /readUsersItemsList?personid=1234                                                 | **GET** /persons/1234/items                                  |
| Add an item to a person???s items | **POST** /addItemToUsersItemsList<br/>{<br/>"personid": "1234";<br/>"itemid": "456"<br/>} | **POST** /persons/1234/items<br/>{<br/>"itemid": "456"<br/>} |
| Update an item                  | **POST** /modifyItem<br/>{<br/>"itemid": "456";<br/>"key": "value"<br/>}                  | **PUT** /items/456<br/>{<br/>"key": "value"<br/>}            |
| Delete an item                  | **POST** /removeItem<br/>{<br/>"itemid": "456"<br/>}                                      | **DELETE** /items/456                                        |

## Security

### WAF (Web Application Fairewall)

* [AWS WAF ??? ??? ?????????????????? ?????????](https://aws.amazon.com/ko/waf/)
* [???????????????????](https://www.pentasecurity.co.kr/resource/%EC%9B%B9%EB%B3%B4%EC%95%88/%EC%9B%B9%EB%B0%A9%ED%99%94%EB%B2%BD%EC%9D%B4%EB%9E%80/)

----
  
* ???????????? ???????????? ?????? ??? ????????????????????? ????????? ????????? ???????????????. 
* ????????????????????? ???????????? ????????? ?????????, SQL Injection, XSS (Cross Site Scripting) ??? ?????? ????????? ???????????????, ???????????? ???????????? ???????????? ??? ?????????????????? ??? ????????????????????? ???????????? ??? ????????? ??????.

### XSS (Cross Site Scripting)

* [??? ?????? ?????? ??? - XSS(Cross Site Scripting) ????????? ????????? ?????? (Web Hacking Tutorial #07) @ youtube](https://www.youtube.com/watch?v=DoN7bkdQBXU)

----

* ??? ???????????? javascript ??? ???????????? ????????? ????????? ??? ???????????? ???????????? ?????? ??? ????????? ??????????????? ???????????? ????????????

### CSRF (Cross Site Request Forgery)

* [??? ?????? ?????? ??? - CSRF(Cross Site Request Forgery) ?????? ?????? (Web Hacking Tutorial #10) @ youtube](https://www.youtube.com/watch?v=nzoUgKPwn_A)

----

* ?????? ???????????? ????????? ???????????? ????????? ?????????????????? ???????????? ????????? ?????? ??? ????????? ??? ?????? ?????? ??????. ???????????? ??????????????? ?????? ?????? ????????? ????????? ????????? ????????? ???????????? ????????? ??????(Request) ???????????? ?????????.

### XSS vs CSRF

* XSS ??? ??????????????? Client ?????? CSRF ??? ??????????????? Server ??????.
* XSS ??? ?????????????????? ???????????? ?????? Client ??? ????????????.
* CSRF ??? ????????? ???????????? ???????????? ????????? ????????? ????????? ????????????.

### CORS (Cross Origin Resource Sharing)

XMLHttpRequest ??? cross-domain ??? ????????? ??? ??????????????? ????????????. request ??? ???????????? Web Server ?????? ???????????? ??????.

## Database Primary Key

* [????????? <????????? ?????? ????????? ?????? Memcached??? Redis>](https://americanopeople.tistory.com/177)

----

?????? ?????? ????????? ???????????? ?????????????????? ?????????. User ??? Email ???????????? ???????????? ????????? ??????. 

| field           | type      | description                  |
| --------------- | --------- | ---------------------------- |
| user_id         | Long (8B) | unique id (??? DB ???)           |
| email           | String    | ????????? ??????                       |
| shard           | Long      | ????????? ?????? ???????????? ????????? DB server ?????? |
| type            | int       | ????????? ??????????????                   |
| created_at      | timestamp | ?????? ????????????                      |
| last_login_time | timestamp | ????????? ????????? ??????                   |

| field       | type           | description        |
| ----------- | -------------- | ------------------ |
| mail_id     | Long (8B)      | unique id (??? DB ???) |
| receiver    | String or Long | ?????????                |
| sender      | String or Long | ?????????                |
| subject     | String         | ????????????               |
| received_at | timestamp      | ????????????               |
| eml_id      | String or Long | ?????? ?????? ?????? id or url |
| is_read     | boolean        | ??????????????             |
| contents    | String         | ???????????? (????????? ??????)      |

eml ??? AWS S3 ??? ????????????. eml file ??? key ??? ???????????? ??????. 

* `{receiver_id}_{mail_id}` 
  * `mail_id` ??? ?????? shard ?????? ???????????? ????????????. ????????? `receiver_id` ??? ???????????? ????????????.
  * ???????????? `eml_id` ??? ????????????? `{receiver_id}_{mail_id}` ???????????? eml file ??? key ??? ????????? ??? ?????? ????????????. ?????? ??? key ??? ??? ????????? ?????? ????????????
* UUID (Universally Unique Identifier)
  * id ??? ?????? ????????? ???????????? ??????. id ??? ?????????????????? ???????????? ????????? ?????? ???????????? ????????? ??? ??????.
  * 16B (128b), 36 characters ??????. ?????? ??????.
  * ?????? ???????????? ?????? ????????? ????????? ??? ???????????? ?????????.
* `{timestamp:52bit}_{sequence:12bit}` 8 bytes
  * ?????? ???????????? ?????????????????? ?????????.
  * timestamp ??? 4 bytes ??????. ???, `1970/01/01` ?????? `2016/02/07/06/28` ????????? ?????? ????????????.  
* `{timestamp:52bit}_{shard_id:12bit}_{sequence:12bit}` 8 bytes 
  * IDC ????????? ?????????????????? ?????????.
* `{timestamp:42bits}_{datacenter_id:5bits}_{worker_id:5bits}_{sequence:12bits}` 8 bytes
  * ????????? twitter ??? id ??????.
* `{timetamp:4B}_{machine_id:3B}_{process_id:2B}_{counter:3B}` 12 bytes
  * ????????? mongoDB ??? ID ??????. 
* `{timestamp}_{shard_id}_{type}_{sequence}` 8 bytes

## Idempotency

* [RESTful API](https://lifeisgift.tistory.com/entry/Restful-API-%EA%B0%9C%EC%9A%94)

----

????????? ?????????????????? ??????. RESTful API ?????? ?????? ????????? ????????? ?????? ????????? ????????? ???????????? ?????? ?????????.

## 80/20 rule

????????? ???????????? 20% ??? ????????????????????? ????????????. ?????? Cache data size ??? estimate ??? ??? ????????????. ?????? ?????? total data size ??? 100GB ?????? cache data size ??? 20GB ??? ????????????. 

## 70% Capacity model

estimated data size ??? total data size ??? 70% ?????? ????????????. ?????? ?????? estimated data size ??? 70GB ?????? total data size ??? 100GB ?????? ??????????????? ????????????.

```
total data size : estimated data size = 100 : 70
```

# Grokking the System Design Interview Practices

| Basic                                                                                                                                                                                                                                                    |     |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --- |
| [Dynamo](https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf) - Highly Available Key-value Store                                                                                                                                       |
| [Kafka](http://notes.stephenholiday.com/Kafka.pdf) - A Distributed Messaging System for Log Processing                                                                                                                                                   |
| [Consistent Hashing](https://www.akamai.com/es/es/multimedia/documents/technical-publication/consistent-hashing-and-random-trees-distributed-caching-protocols-for-relieving-hot-spots-on-the-world-wide-web-technical-publication.pdf) - Original paper |
| [Paxos](https://www.microsoft.com/en-us/research/uploads/prod/2016/12/paxos-simple-Copy.pdf) - Protocol for distributed consensus                                                                                                                        |
| [Concurrency Controls](http://sites.fas.harvard.edu/~cs265/papers/kung-1981.pdf) - Optimistic methods for concurrency controls                                                                                                                           |
| [Gossip protocol](http://highscalability.com/blog/2011/11/14/using-gossip-protocols-for-failure-detection-monitoring-mess.html) - For failure detection and more.                                                                                        |
| [Chubby](http://static.googleusercontent.com/media/research.google.com/en/us/archive/chubby-osdi06.pdf) - Lock service for loosely-coupled distributed systems                                                                                           |
| [ZooKeeper](https://www.usenix.org/legacy/event/usenix10/tech/full_papers/Hunt.pdf) - Wait-free coordination for Internet-scale systems                                                                                                                  |
| [MapReduce](https://static.googleusercontent.com/media/research.google.com/en//archive/mapreduce-osdi04.pdf) - Simplified Data Processing on Large Clusters                                                                                              |
| [Hadoop](http://storageconference.us/2010/Papers/MSST/Shvachko.pdf) - A Distributed File System [hadoop @ TIL](/hadoop/README.md)                                                                                                                        |
| [Key Characteristics of Distributed Systems](Key_Characteristics_of_Distributed_Systems.md)                                                                                                                                                              |
| [Load Balancing](grokking/LoadBalancing.md)                                                                                                                                                                                                              |
| [Caching](grokking/Caching.md)                                                                                                                                                                                                                           |
| [Data Partitioning](grokking/DataPartitioning.md)                                                                                                                                                                                                        |
| [Indexes](grokking/Indexes.md)                                                                                                                                                                                                                           |
| [Proxies](grokking/Proxies.md)                                                                                                                                                                                                                           |
| [Redundancy and Replication](grokking/RedundancyandReplication.md)                                                                                                                                                                                       |
| [SQL vs. NoSQL](grokking/SQLvsNoSQL.md)                                                                                                                                                                                                                  |
| [CAP Theorem](grokking/CAPTheorem.md)                                                                                                                                                                                                                    |
| [Consistent Hashing](grokking/ConsistentHashing.md)                                                                                                                                                                                                      |
| [Long-Polling vs WebSockets vs Server-Sent Events](grokking/Long-PollingvsWebSocketsvsServer-SentEvents.md)                                                                                                                                              |

| Design                                                                                                         | Implementation |
| -------------------------------------------------------------------------------------------------------------- | -------------- |
| [Designing a URL Shortening service like TinyURL](grokking/Designing_a_URL_Shortening_service_like_TinyURL.md) |                |
| [Designing Pastebin](grokking/DesigningPastebin.md)                                                            |                |
| [Designing Instagram]()                                                                                        |                |
| [Designing Dropbox]()                                                                                          |                |
| [Designing Facebook Messenger]()                                                                               |                |
| [Designing Twitter](grokking/DesigningTwitter.md)                                                              |                |
| [Designing Youtube or Netflix]()                                                                               |                |
| [Designing Typeahead Suggestion]()                                                                             |                |
| [Designing an API Rate Limiter](grokking/DesigningAnApiRateLimiter.md)                                         |                |
| [Designing Twitter Search](grokking/DesigningTwitterSearch.md)                                                 |                |
| [Designing a Web Crawler]()                                                                                    |                |
| [Designing Facebook???s Newsfeed](grokking/DesigningFacebooksNewsfeed.md)                                        |
| [Designing Yelp or Nearby Friends]()                                                                           |                |
| [Designing Uber backend]()                                                                                     |                |
| [Designing Ticketmaster]()                                                                                     |                |

# System Design Primer Practices

| Question                                                                                      |                                                                                                                            |
| --------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------- |
| Design Pastebin.com (or Bit.ly)                                                               | [Solution](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/pastebin/README.md)     |
| Design the Twitter timeline (or Facebook feed)<br/>Design Twitter search (or Facebook search) | [Solution](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/twitter/README.md)      |
| Design a web crawler                                                                          | [Solution](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/web_crawler/README.md)  |
| Design Mint.com                                                                               | [Solution](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/mint/README.md)         |
| Design the data structures for a social network                                               | [Solution](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/social_graph/README.md) |
| Design a key-value store for a search engine                                                  | [Solution](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/query_cache/README.md)  |
| Design Amazon's sales ranking by category feature                                             | [Solution](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/sales_rank/README.md)   |
| Design a system that scales to millions of users on AWS                                       | [Solution](https://github.com/donnemartin/system-design-primer/blob/master/solutions/system_design/scaling_aws/README.md)  |

# Essential Micro Services

| Service         |
| --------------- |
| [CMDB](essentials/CMDB.md) |
| [Api Gateway](essentials/ApiGateway.md) |
| [Deployment System](essentials/Deployment.md) |
| [LogViewer](essentials/LogViewer.md) |
| [Monitoring](essentials/Monitoring.md) |
| [Alterting](essentials/Alterting.md) |
| [ABTesting](essentials/ABTesting.md) |

# Additional System Design Interview Questions

| Question                                                    | Reference(s)                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| ----------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Design a file sync service like Dropbox                     | [youtube.com](https://www.youtube.com/watch?v=PE4gwstWhmc)                                                                                                                                                                                                                                                                                                                                                                                                  |
| Design a search engine like Google                          | [queue.acm.org](http://queue.acm.org/detail.cfm?id=988407)<br/>[stackexchange.com](http://programmers.stackexchange.com/questions/38324/interview-question-how-would-you-implement-google-search)<br/>[ardendertat.com](http://www.ardendertat.com/2012/01/11/implementing-search-engines/)<br>[stanford.edu](http://infolab.stanford.edu/~backrub/google.html)                                                                                             |
| Design a scalable web crawler like Google                   | [quora.com](https://www.quora.com/How-can-I-build-a-web-crawler-from-scratch)                                                                                                                                                                                                                                                                                                                                                                               |
| Design Google docs                                          | [code.google.com](https://code.google.com/p/google-mobwrite/)<br/>[neil.fraser.name](https://neil.fraser.name/writing/sync/)                                                                                                                                                                                                                                                                                                                                |
| Design a key-value store like Redis                         | [slideshare.net](http://www.slideshare.net/dvirsky/introduction-to-redis)                                                                                                                                                                                                                                                                                                                                                                                   |
| Design a cache system like Memcached                        | [slideshare.net](http://www.slideshare.net/oemebamo/introduction-to-memcached)                                                                                                                                                                                                                                                                                                                                                                              |
| Design a recommendation system like Amazon's                | [hulu.com](http://tech.hulu.com/blog/2011/09/19/recommendation-system.html)<br/>[ijcai13.org](http://ijcai13.org/files/tutorial_slides/td3.pdf)                                                                                                                                                                                                                                                                                                             |
| Design a tinyurl system like Bitly                          | [n00tc0d3r.blogspot.com](http://n00tc0d3r.blogspot.com/)                                                                                                                                                                                                                                                                                                                                                                                                    |
| Design a chat app like WhatsApp                             | [highscalability.com](http://highscalability.com/blog/2014/2/26/the-whatsapp-architecture-facebook-bought-for-19-billion.html)                                                                                                                                                                                                                                                                                                                              |
| Design a picture sharing system like Instagram              | [highscalability.com](http://highscalability.com/flickr-architecture)<br/>[highscalability.com](http://highscalability.com/blog/2011/12/6/instagram-architecture-14-million-users-terabytes-of-photos.html)                                                                                                                                                                                                                                                 |
| Design the Facebook news feed function                      | [quora.com](http://www.quora.com/What-are-best-practices-for-building-something-like-a-News-Feed)<br/>[quora.com](http://www.quora.com/Activity-Streams/What-are-the-scaling-issues-to-keep-in-mind-while-developing-a-social-network-feed)<br/>[slideshare.net](http://www.slideshare.net/danmckinley/etsy-activity-feeds-architecture)                                                                                                                    |
| Design the Facebook timeline function                       | [facebook.com](https://www.facebook.com/note.php?note_id=10150468255628920)<br/>[highscalability.com](http://highscalability.com/blog/2012/1/23/facebook-timeline-brought-to-you-by-the-power-of-denormaliza.html)                                                                                                                                                                                                                                          |
| Design the Facebook chat function                           | [erlang-factory.com](http://www.erlang-factory.com/upload/presentations/31/EugeneLetuchy-ErlangatFacebook.pdf)<br/>[facebook.com](https://www.facebook.com/note.php?note_id=14218138919&id=9445547199&index=0)                                                                                                                                                                                                                                              |
| Design a graph search function like Facebook's              | [facebook.com](https://www.facebook.com/notes/facebook-engineering/under-the-hood-building-out-the-infrastructure-for-graph-search/10151347573598920)<br/>[facebook.com](https://www.facebook.com/notes/facebook-engineering/under-the-hood-indexing-and-ranking-in-graph-search/10151361720763920)<br/>[facebook.com](https://www.facebook.com/notes/facebook-engineering/under-the-hood-the-natural-language-interface-of-graph-search/10151432733048920) |
| Design a content delivery network like CloudFlare           | [cmu.edu](http://repository.cmu.edu/cgi/viewcontent.cgi?article=2112&context=compsci)                                                                                                                                                                                                                                                                                                                                                                       |
| Design a trending topic system like Twitter's               | [michael-noll.com](http://www.michael-noll.com/blog/2013/01/18/implementing-real-time-trending-topics-in-storm/)<br/>[snikolov .wordpress.com](http://snikolov.wordpress.com/2012/11/14/early-detection-of-twitter-trends/)                                                                                                                                                                                                                                 |
| Design a random ID generation system                        | [blog.twitter.com](https://blog.twitter.com/2010/announcing-snowflake)<br/>[github.com](https://github.com/twitter/snowflake/)                                                                                                                                                                                                                                                                                                                              |
| Return the top k requests during a time interval            | [ucsb.edu](https://icmi.cs.ucsb.edu/research/tech_reports/reports/2005-23.pdf)<br/>[wpi.edu](http://davis.wpi.edu/xmdv/docs/EDBT11-diyang.pdf)                                                                                                                                                                                                                                                                                                              |
| Design a system that serves data from multiple data centers | [highscalability.com](http://highscalability.com/blog/2009/8/24/how-google-serves-data-from-multiple-datacenters.html)                                                                                                                                                                                                                                                                                                                                      |
| Design an online multiplayer card game                      | [indieflashblog.com](http://www.indieflashblog.com/how-to-create-an-asynchronous-multiplayer-game.html)<br/>[buildnewgames.com](http://buildnewgames.com/real-time-multiplayer/)                                                                                                                                                                                                                                                                            |
| Design a garbage collection system                          | [stuffwithstuff.com](http://journal.stuffwithstuff.com/2013/12/08/babys-first-garbage-collector/)<br/>[washington.edu](http://courses.cs.washington.edu/courses/csep521/07wi/prj/rick.pdf)                                                                                                                                                                                                                                                                  |
| Design an API rate limiter                                  | [https://stripe.com/blog/](https://stripe.com/blog/rate-limiters)                                                                                                                                                                                                                                                                                                                                                                                           |
| Add a system design question                                | [Contribute](#contributing)                                                                                                                                                                                                                                                                                                                                                                                                                                 |

# Real World Architecture

| Type            | System                                                                                                               | Reference(s)                                                                                                                                   |
| --------------- | -------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------- |
| Data processing | **MapReduce** - Distributed data processing from Google                                                              | [research.google.com](http://static.googleusercontent.com/media/research.google.com/zh-CN/us/archive/mapreduce-osdi04.pdf)                     |
| Data processing | **Spark** - Distributed data processing from Databricks                                                              | [slideshare.net](http://www.slideshare.net/AGrishchenko/apache-spark-architecture)                                                             |
| Data processing | **Storm** - Distributed data processing from Twitter                                                                 | [slideshare.net](http://www.slideshare.net/previa/storm-16094009)                                                                              |
|                 |                                                                                                                      |                                                                                                                                                |
| Data store      | **Bigtable** - Distributed column-oriented database from Google                                                      | [harvard.edu](http://www.read.seas.harvard.edu/~kohler/class/cs239-w08/chang06bigtable.pdf)                                                    |
| Data store      | **HBase** - Open source implementation of Bigtable                                                                   | [slideshare.net](http://www.slideshare.net/alexbaranau/intro-to-hbase)                                                                         |
| Data store      | **Cassandra** - Distributed column-oriented database from Facebook                                                   | [slideshare.net](http://www.slideshare.net/planetcassandra/cassandra-introduction-features-30103666)                                           |
| Data store      | **DynamoDB** - Document-oriented database from Amazon                                                                | [harvard.edu](http://www.read.seas.harvard.edu/~kohler/class/cs239-w08/decandia07dynamo.pdf)                                                   |
| Data store      | **MongoDB** - Document-oriented database                                                                             | [slideshare.net](http://www.slideshare.net/mdirolf/introduction-to-mongodb)                                                                    |
| Data store      | **Spanner** - Globally-distributed database from Google                                                              | [research.google.com](http://research.google.com/archive/spanner-osdi2012.pdf)                                                                 |
| Data store      | **Memcached** - Distributed memory caching system                                                                    | [slideshare.net](http://www.slideshare.net/oemebamo/introduction-to-memcached)                                                                 |
| Data store      | **Redis** - Distributed memory caching system with persistence and value types                                       | [slideshare.net](http://www.slideshare.net/dvirsky/introduction-to-redis)                                                                      |
| Data store      | **Couchbase** - an open-source, distributed multi-model NoSQL document-oriented database                             | [couchbase.com](https://www.couchbase.com/)                                                                                                    |
|                 |                                                                                                                      |                                                                                                                                                |
| File system     | **Google File System (GFS)** - Distributed file system                                                               | [research.google.com](http://static.googleusercontent.com/media/research.google.com/zh-CN/us/archive/gfs-sosp2003.pdf)                         |
| File system     | **Hadoop File System (HDFS)** - Open source implementation of GFS                                                    | [apache.org](https://hadoop.apache.org/docs/r1.2.1/hdfs_design.html)                                                                           |
|                 |                                                                                                                      |                                                                                                                                                |
| Misc            | **Chubby** - Lock service for loosely-coupled distributed systems from Google                                        | [research.google.com](http://static.googleusercontent.com/external_content/untrusted_dlcp/research.google.com/en/us/archive/chubby-osdi06.pdf) |
| Misc            | **Dapper** - Distributed systems tracing infrastructure                                                              | [research.google.com](http://static.googleusercontent.com/media/research.google.com/en//pubs/archive/36356.pdf)                                |
| Misc            | **Kafka** - Pub/sub message queue from LinkedIn                                                                      | [slideshare.net](http://www.slideshare.net/mumrah/kafka-talk-tri-hug)                                                                          |
| Misc            | **Zookeeper** - Centralized infrastructure and services enabling synchronization                                     | [slideshare.net](http://www.slideshare.net/sauravhaloi/introduction-to-apache-zookeeper)                                                       |
| Misc            | **??MQ** - a high-performance asynchronous messaging library, aimed at use in distributed or concurrent applications. | [zeromq.org](http://zeromq.org/)                                                                                                               |
| Misc            | **etcd** - A distributed, reliable key-value store for the most critical data of a distributed system.               | [etcd docs](https://coreos.com/etcd/docs/latest/)                                                                                              |

# Company Architectures

| Company        | Reference(s)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| -------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Amazon         | [Amazon architecture](http://highscalability.com/amazon-architecture)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| Cinchcast      | [Producing 1,500 hours of audio every day](http://highscalability.com/blog/2012/7/16/cinchcast-architecture-producing-1500-hours-of-audio-every-d.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| DataSift       | [Realtime datamining At 120,000 tweets per second](http://highscalability.com/blog/2011/11/29/datasift-architecture-realtime-datamining-at-120000-tweets-p.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| DropBox        | [How we've scaled Dropbox](https://www.youtube.com/watch?v=PE4gwstWhmc)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| ESPN           | [Operating At 100,000 duh nuh nuhs per second](http://highscalability.com/blog/2013/11/4/espns-architecture-at-scale-operating-at-100000-duh-nuh-nuhs.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| Google         | [Google architecture](http://highscalability.com/google-architecture)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| Instagram      | [14 million users, terabytes of photos](http://highscalability.com/blog/2011/12/6/instagram-architecture-14-million-users-terabytes-of-photos.html)<br/>[What powers Instagram](http://instagram-engineering.tumblr.com/post/13649370142/what-powers-instagram-hundreds-of-instances)                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| Justin.tv      | [Justin.Tv's live video broadcasting architecture](http://highscalability.com/blog/2010/3/16/justintvs-live-video-broadcasting-architecture.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| Facebook       | [Scaling memcached at Facebook](https://cs.uwaterloo.ca/~brecht/courses/854-Emerging-2014/readings/key-value/fb-memcached-nsdi-2013.pdf)<br/>[TAO: Facebook???s distributed data store for the social graph](https://cs.uwaterloo.ca/~brecht/courses/854-Emerging-2014/readings/data-store/tao-facebook-distributed-datastore-atc-2013.pdf)<br/>[Facebook???s photo storage](https://www.usenix.org/legacy/event/osdi10/tech/full_papers/Beaver.pdf)                                                                                                                                                                                                                                                                                           |
| Flickr         | [Flickr architecture](http://highscalability.com/flickr-architecture)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| Mailbox        | [From 0 to one million users in 6 weeks](http://highscalability.com/blog/2013/6/18/scaling-mailbox-from-0-to-one-million-users-in-6-weeks-and-1.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| Pinterest      | [From 0 To 10s of billions of page views a month](http://highscalability.com/blog/2013/4/15/scaling-pinterest-from-0-to-10s-of-billions-of-page-views-a.html)<br/>[18 million visitors, 10x growth, 12 employees](http://highscalability.com/blog/2012/5/21/pinterest-architecture-update-18-million-visitors-10x-growth.html)                                                                                                                                                                                                                                                                                                                                                                                                             |
| Playfish       | [50 million monthly users and growing](http://highscalability.com/blog/2010/9/21/playfishs-social-gaming-architecture-50-million-monthly-user.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| PlentyOfFish   | [PlentyOfFish architecture](http://highscalability.com/plentyoffish-architecture)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| Salesforce     | [How they handle 1.3 billion transactions a day](http://highscalability.com/blog/2013/9/23/salesforce-architecture-how-they-handle-13-billion-transacti.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| Stack Overflow | [Stack Overflow architecture](http://highscalability.com/blog/2009/8/5/stack-overflow-architecture.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| TripAdvisor    | [40M visitors, 200M dynamic page views, 30TB data](http://highscalability.com/blog/2011/6/27/tripadvisor-architecture-40m-visitors-200m-dynamic-page-view.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| Tumblr         | [15 billion page views a month](http://highscalability.com/blog/2012/2/13/tumblr-architecture-15-billion-page-views-a-month-and-harder.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| Twitter        | [Making Twitter 10000 percent faster](http://highscalability.com/scaling-twitter-making-twitter-10000-percent-faster)<br/>[Storing 250 million tweets a day using MySQL](http://highscalability.com/blog/2011/12/19/how-twitter-stores-250-million-tweets-a-day-using-mysql.html)<br/>[150M active users, 300K QPS, a 22 MB/S firehose](http://highscalability.com/blog/2013/7/8/the-architecture-twitter-uses-to-deal-with-150m-active-users.html)<br/>[Timelines at scale](https://www.infoq.com/presentations/Twitter-Timeline-Scalability)<br/>[Big and small data at Twitter](https://www.youtube.com/watch?v=5cKTP36HVgI)<br/>[Operations at Twitter: scaling beyond 100 million users](https://www.youtube.com/watch?v=z8LU0Cj6BOU) |
| Uber           | [How Uber scales their real-time market platform](http://highscalability.com/blog/2015/9/14/how-uber-scales-their-real-time-market-platform.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| WhatsApp       | [The WhatsApp architecture Facebook bought for $19 billion](http://highscalability.com/blog/2014/2/26/the-whatsapp-architecture-facebook-bought-for-19-billion.html)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| YouTube        | [YouTube scalability](https://www.youtube.com/watch?v=w5WVu624fY8)<br/>[YouTube architecture]                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |

# company engineering blog

* [Airbnb Engineering](http://nerds.airbnb.com/)
* [Atlassian Developers](https://developer.atlassian.com/blog/)
* [Autodesk Engineering](http://cloudengineering.autodesk.com/blog/)
* [AWS Blog](https://aws.amazon.com/blogs/aws/)
* [Bitly Engineering Blog](http://word.bitly.com/)
* [Box Blogs](https://www.box.com/blog/engineering/)
* [Cloudera Developer Blog](http://blog.cloudera.com/blog/)
* [Dropbox Tech Blog](https://tech.dropbox.com/)
* [Engineering at Quora](http://engineering.quora.com/)
* [Ebay Tech Blog](http://www.ebaytechblog.com/)
* [Evernote Tech Blog](https://blog.evernote.com/tech/)
* [Etsy Code as Craft](http://codeascraft.com/)
* [Facebook Engineering](https://www.facebook.com/Engineering)
* [Flickr Code](http://code.flickr.net/)
* [Foursquare Engineering Blog](http://engineering.foursquare.com/)
* [GitHub Engineering Blog](http://githubengineering.com/)
* [Google Research Blog](http://googleresearch.blogspot.com/)
* [Groupon Engineering Blog](https://engineering.groupon.com/)
* [Heroku Engineering Blog](https://engineering.heroku.com/)
* [Hubspot Engineering Blog](http://product.hubspot.com/blog/topic/engineering)
* [High Scalability](http://highscalability.com/)
* [Instagram Engineering](http://instagram-engineering.tumblr.com/)
* [Intel Software Blog](https://software.intel.com/en-us/blogs/)
* [Jane Street Tech Blog](https://blogs.janestreet.com/category/ocaml/)
* [LinkedIn Engineering](http://engineering.linkedin.com/blog)
* [Microsoft Engineering](https://engineering.microsoft.com/)
* [Microsoft Python Engineering](https://blogs.msdn.microsoft.com/pythonengineering/)
* [Netflix Tech Blog](http://techblog.netflix.com/)
* [Paypal Developer Blog](https://devblog.paypal.com/category/engineering/)
* [Pinterest Engineering Blog](http://engineering.pinterest.com/)
* [Quora Engineering](https://engineering.quora.com/)
* [Reddit Blog](http://www.redditblog.com/)
* [Salesforce Engineering Blog](https://developer.salesforce.com/blogs/engineering/)
* [Slack Engineering Blog](https://slack.engineering/)
* [Spotify Labs](https://labs.spotify.com/)
* [Twilio Engineering Blog](http://www.twilio.com/engineering)
* [Twitter Engineering](https://engineering.twitter.com/)
* [Uber Engineering Blog](http://eng.uber.com/)
* [Yahoo Engineering Blog](http://yahooeng.tumblr.com/)
* [Yelp Engineering Blog](http://engineeringblog.yelp.com/)
* [Zynga Engineering Blog](https://www.zynga.com/blogs/engineering)

# Cloud Design Patterns

* [Cloud Design Patterns](clouddesignpattern.md)

----

MSA ??? pattern ?????? ?????? ????????????.

# Cracking The Coding Interview Quiz

* Stock Data
* Social Network
* Web Crawler
* Duplicate URLs
* Cache
* Sales Rank
* Personal Financial Manager
* Pastebin

