# springframework2
Spring Boot + Spring Data JPA + Spring Data Mongodb + Spring Batch


**To run this code, need set up mysql Docker environment first**

[mysql docker usage: https://hub.docker.com/r/mysql/mysql-server/](https://hub.docker.com/r/mysql/mysql-server/)

1. **pull mysql offical docker file**
```ruby
BQMac:mysql5.7 qibo$ docker pull mysql/mysql-server
```
2. **start docker container**

```ruby
BQMac:mysql5.7 qibo$ docker run --name bq-mysql-container -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_ROOT_HOST=172.17.0.1 -d mysql/mysql-server
79a84f9de3bbc61ba7dc500c70bb5b2520db75d376cbf73068abc30c75e05d36
BQMac:mysql5.7 qibo$ docker ps 
CONTAINER ID        IMAGE                COMMAND                  CREATED             STATUS              PORTS                               NAMES
79a84f9de3bb        mysql/mysql-server   "/entrypoint.sh mysql"   6 seconds ago       Up 4 seconds        0.0.0.0:3306->3306/tcp, 33060/tcp   bq-mysql-container
```
3) use mysql client connect to server
BQMac:mysql5.7 qibo$ docker exec -it bq-mysql-container mysql -uroot -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 4
Server version: 5.7.16 MySQL Community Server (GPL)

4) create database

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
4 rows in set (0.00 sec)

mysql> create database test;
Query OK, 1 row affected (0.00 sec)

mysql> use test;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> select * from helloworld;
+----+------+--------+
| id | name | value  |
+----+------+--------+
|  1 | boqi |     88 |
|  2 | boqi | 888888 |
|  3 | boqi |     88 |
+----+------+--------+
3 rows in set (0.00 sec)
 
5) after this initial setup, next time just need stop/start docker container

BQMac:mysql5.7 qibo$ docker ps
CONTAINER ID        IMAGE                COMMAND                  CREATED             STATUS              PORTS                               NAMES
79a84f9de3bb        mysql/mysql-server   "/entrypoint.sh mysql"   23 minutes ago      Up 23 minutes       0.0.0.0:3306->3306/tcp, 33060/tcp   bq-mysql-container
BQMac:mysql5.7 qibo$ docker stop 79a84f9de3bb
79a84f9de3bb
BQMac:mysql5.7 qibo$ docker start 79a84f9de3bb
79a84f9de3bb
BQMac:mysql5.7 qibo$ 
