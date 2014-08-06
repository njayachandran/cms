cms
===

Test code create lock for jms topic


pre-requisite
=============
* Run ActiveMQ server
* Run Database server
* JDK/JRE 1.7
* Maven 3.0+

DB Scripts
==========
```
$ create database topicdb;

$ create table message_lock ( id int(10) not null auto_increment, message_id varchar(50) not null, message_consumer_id varchar(50) not null, primary key (id));

$ alter table message_lock add unique (message_id);
```

Build application
=================
```
/cms $ mvn clean install
```

Running cms topic listener
==========================
* move cms jar and libraries to a folder for e.g. /ebay/gpf/cms1

* make sure consumerId is unique

```
$ java -classpath cms-0.0.1-SNAPSHOT.jar:lib** -Dcms_home=/ebay/gpf/cms1 -DconsumerId=cms1 com.ebayinc.cms.Driver
```
