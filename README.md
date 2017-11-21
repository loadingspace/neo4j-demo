# neo4j-demo
neo4j demo for springboot


# run

1.install neo4j

2.modify spring config `src/main/resources/application.properties`

```
  # tomcat config
  server.port=8080

  # neo4j config
  spring.data.neo4j.username=neo4j
  spring.data.neo4j.password=root
  spring.data.neo4j.uri=http://localhost:7474
```

3.run test

# neo4j cpyher

## Node语法
在cypher里面通过用一对小括号()表示一个节点，它在cypher里面查询形式如下：
1, `()` 代表匹配任意一个节点
2, `(node1)` 代表匹配任意一个节点，并给它起了一个别名
3, `(:Lable)` 代表查询一个类型的数据
4, `(person:Lable)` 代表查询一个类型的数据，并给它起了一个别名
5, `(person:Lable {name:"小王"})` 查询某个类型下，节点属性满足某个值的数据
6, `(person:Lable {name:"小王",age:23})`　节点的属性可以同时存在多个，是一个AND的关系

## 关系语法
关系用一对-组成，关系分有方向的进和出，如果是无方向就是进和出都查询
1, `-->` 指向一个节点
2, `-[role]->` 给关系加个别名
3, `-[:acted_in]->` 访问某一类关系
4, `-[role:acted_in]->` 访问某一类关系，并加了别名
5, `-[role:acted_in {roles:["neo","hadoop"]}]->` 访问某一类关系下的某个属性的关系的数据

## 模式语法
模式语法是节点和关系查询语法的结合，通过模式语法我们可以进行我们想要的任意复杂的查询
```
(p1: Person:Actor {name:"tom"})-[role:acted_in {roles:["neo","actor"]}]-(m1:Movie {title:"water"})
```

## 模式变量
为了增加模块化和减少重复，cypher允许把模式的结果指定在一个变量或者别名中，方便后续使用或操作
```
path = (: Person)-[:ACTED_IN]->(:Movie)
```

path是结果集的抽象封装，有多个函数可以直接从path里面提取数据如：
1, `nodes(path)` 提取所有的节点
2, `rels(path)` 提取所有的关系 和relationships(path)相等
3, `length(path)` 获取路径长度

## 条件
cypher语句也是由多个关键词组成，像SQL的
```
select name, count(*) from talbe where age=24 group by name having count(*) >2  order by count(*) desc
```
多个关键字组成的语法，cypher也非常类似，每个关键词会执行一个特定的task来处理数据
match: 查询的主要关键词
create: 类似sql里面的insert
filter，project，sort，page等都有对应的功能语句
通过组合上面的一些语句，我们可以写出非常强大复杂的语法，来查询我们想要检索的内容，cypher会 自动解析语法并优化执行。


## 例子
### 1.创建
```
create (:Movie {title:"驴得水",released:2016})  return p;
```
执行成功，在neo4j的web页面我们能看到下面的信息
```
+-------------------+
| No data returned. |
+-------------------+
Nodes created: 1
Properties set: 2
Labels added: 1
```
当然cypher也可以一次创建多个数据，并同时添加关系

### 2.查询

match (p: Person) return p; 查询Person类型的所有数据

match (p: Person {name:"sun"}) return p; 查询名字等于sun的人

match( p1: Person {name:"sun"} )-[rel:friend]->(p2) return p2.name , p2.age 查询sun的朋友的名字和年龄

match (old) ... create (new) create (old)-[rel:dr]->(new) return new 对已经存在的节点和新建的节点建立关系

### 3.查询或更新

merge 语法可以对已经存在的节点不做改变，对变化的部分会合并
```
MERGE (m:Movie { title:"Cloud Atlas" })
ON CREATE SET m.released = 2012
RETURN m
```
merge .... on create set ... return 语法支持合并更新

### 4.筛选过滤

cypher过滤也是用的和SQL一样的关键词where

match (p1: Person) where p1.name="sun" return p1;

等同下面的

match (p1: Person {name:"sun"}) return p1

注意where条件里面支持 and ， or ，xor，not等boolean运算符，在json串里面都是and

除此之外，where里面查询还支持正则查询
```
match (p1: Person)-[r:friend]->(p2: Person)
where p1.name=~"K.+" or p2.age=24 or "neo" in r.rels
return p1,r,p2
```
关系过滤匹配使用not
```
MATCH (p:Person)-[:ACTED_IN]->(m)
WHERE NOT (p)-[:DIRECTED]->()
RETURN p,m
```
### 5.结果集返回
```
MATCH (p:Person)
RETURN p, p.name AS name, upper(p.name), coalesce(p.nickname,"n/a") AS nickname, { name: p.name,
  label:head(labels(p))} AS person
```
结果集返回做去重
```
match (n) return distinct n.name;
```

### 6.聚合函数

cypher支持count,sum,avg,min,max

match (: Person) return count(*)

聚合的时候null会被跳过 count 语法 支持 count( distinct role )
```
MATCH (actor:Person)-[:ACTED_IN]->(movie:Movie)<-[:DIRECTED]-(director:Person)
RETURN actor,director,count(*) AS collaborations
```

### 7.排序和分页
```
MATCH (a:Person)-[:ACTED_IN]->(m:Movie)
RETURN a,count(*) AS appearances
ORDER BY appearances DESC SKIP 3 LIMIT 10;
```

### 8.收集聚合结果
```
MATCH (m:Movie)<-[:ACTED_IN]-(a:Person)
RETURN m.title AS movie, collect(a.name) AS cast, count(*) AS actors
```

### 9.union 联合
支持两个查询结构集一样的结果合并
```
MATCH (actor:Person)-[r:ACTED_IN]->(movie:Movie)
RETURN actor.name AS name, type(r) AS acted_in, movie.title AS title
UNION （ALL）
MATCH (director:Person)-[r:DIRECTED]->(movie:Movie)
RETURN director.name AS name, type(r) AS acted_in, movie.title AS title
```

### 10.with

with语句给cypher提供了强大的pipeline能力，和return语句非常类似，唯一不同的是，with的每一个结果，必须使用别名标识。

通过这个功能，我们可以轻而易举的做到在查询结果里面在继续嵌套查询。
```
MATCH (person:Person)-[:ACTED_IN]->(m:Movie)
WITH person, count(*) AS appearances, collect(m.title) AS movies
WHERE appearances > 1
RETURN person.name, appearances, movies
```
注意在SQL里面，我们想过滤聚合结果，需要使用having语句但是在cypher里面我们可以配合with语句使用 where关键词来完成过滤

### 11.添加约束或者索引

唯一约束(使用merge来实现) CREATE CONSTRAINT ON (movie:Movie) ASSERT movie.title IS UNIQUE

添加索引(在图谱遍历时，快速找到开始节点),大幅提高查询遍历性能 CREATE INDEX ON :Actor(name)

添加测试数据：

CREATE (actor:Actor { name:"Tom Hanks" }),(movie:Movie { title:'Sleepless IN Seattle' }),
  (actor)-[:ACTED_IN]->(movie);
使用索引查询:

MATCH (actor:Actor { name: "Tom Hanks" })
RETURN actor;
