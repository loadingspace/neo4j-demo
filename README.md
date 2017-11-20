# neo4j-demo
neo4j demo for springboot


# run

1.install neo4j

2.modify spring config `src/main/resources/application.properties`

`
  # tomcat config
  server.port=8080

  # neo4j config
  spring.data.neo4j.username=neo4j
  spring.data.neo4j.password=root
  spring.data.neo4j.uri=http://localhost:7474
`

3.run test
