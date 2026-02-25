FROM maven:3.8.1-jdk-11-openj9 AS builder

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN mvn clean package -DskipTests
RUN mv target/jsf1-0.0.1-SNAPSHOT.war target/ROOT.war

FROM tomcat:8.5.92-jdk11

COPY --from=builder /usr/src/app/target/ROOT.war /usr/local/tomcat/webapps/

ENV PORT=8080

CMD sh -c "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT}\\\"/\" /usr/local/tomcat/conf/server.xml && catalina.sh run"
