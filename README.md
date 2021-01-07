# gulimall

## 1. project structure and plan 

Gulimall is a self-operated mall platform, which consists of a cluster system + backend management system.

This project covers the full-stack technology of distributed development, including front-and-back full-stack development, Restful interface, data verification, gateway, registration discovery, configuration center, fuse, current limit, downgrade,
Link tracking, performance monitoring, stress testing, system warning, cluster deployment, continuous integration, continuous deployment.

This project will be devided into 3 parts, basic distributed part, advanced distributed part, and high-availability cluster deployment part, details are shown below, updates are continuous.

### Basic distributed part (Finished)

Use SpringBoot + Vue + reverse engineering to build a full set of backend management system, using the front and back separation method.

### Advanced distributed part (Ongoing）

Develop the entire mall system with SpringBoot + SpringCloud + SpringCloud Alibaba series. Nacos registration center/configuration center, Sentinel traffic protection system, Seata distributed transaction & RabbitMQ flexible transaction solution,
SpringCloud-Gateway gateway, Feign remote call, Sleuth+Zipkin link tracking system, Spring Cache cache, SpringSession cross-subdomain Session synchronization solution, based on ElasticSearch7 full-text retrieval, asynchronous orchestration and thread pool,
Stress test tuning, Redisson distributed lock, distributed semaphore.

### High-availability cluster deployment part

Develop a full set of pipeline based on kubesphere and k8s. To be continued...


## 2. Project record and notes

### 2.1. Cloud server related deployment

Here I chose the Google cloud server with CentOS 7 where docker (mysql, redis) and nacos are runing on, its free for the first 3 monthes, and could save your laptop :)

To know more about installing docker with mysql and redis, please find it in my other blog. [Installing docker on google cloud server](https://github.com/Xinjiann/java/blob/main/Deploy%20docker%20with%20mysql%20and%20redis%20on%20google%20cloud%20server.md), and problems solving when deploy the nacos on cloud server. [Runing nacos as configuration and registration centeron google cloud server](https://github.com/Xinjiann/java/blob/main/Runing%20nacos%20as%20configuration%20and%20registration%20center%20on%20google%20cloud%20server.md)

### 2.2. Code generater

[Renren Generator](https://gitee.com/renrenio/renren-generator): A open source code generator can generate entity, xml, dao, service, vue, sql code online, reducing development tasks by more than 70%.

[Renren Fast](https://gitee.com/renrenio/renren-fast): A lightweight Spring Boot 2.1 rapid development platform, including: administrator list, role management, menu management, timed tasks, parameter management, code generator, log management, cloud storage, API module (APP interface development tool), front and back end separation, etc.

[Renren Fast Vue](https://gitee.com/renrenio/renren-fast-vue): The front-end system of renren-fast background management.



### 2.3. Gateway and CORS

The gateway provides protocol adaptation, protocol forwarding, security policies, anti-brushing, traffic, monitoring logs functions.

More problems solving of gateway and CORS in my blog: [xinjian.blog/gateway](http://xinjian.blog/2020/11/28/Spring-Cloud-Gateway-Service-Startup-Error/) and  [xinjian.blog/CORS](http://xinjian.blog/2020/12/30/Cross-Origin-Resource-Sharing-CORS-problem-solving-and-ideas/)

### 2.4. JSR303 custom annotation

JSR303 is a sub-specification in Java EE 6, called Bean Validation. We can mark different annotations on the fields of entity classes to verify data without if-else.

[xinjian.blog/JSR303]()

### 2.5. File upload

Different from the traditional single application, here we choose to upload the data to the distributed file server, Alibaba OSS(Object Storage Service).

[xinjian.blog/fileUpload]()

The principle of direct transmission after signing on the server:

- The user sends a policy upload request to the application server.
- The application server returns the upload policy and signature to the user.
- Users directly upload data to OSS.



