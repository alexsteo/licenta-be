FROM maven:3.6.3-jdk-8 AS builder

COPY ./src/ /root/src
COPY ./pom.xml /root/
COPY src/main/resources/route.txt /root/
WORKDIR /root
RUN mvn package
RUN java -Djarmode=layertools -jar /root/target/Backend-0.0.1-SNAPSHOT.jar list
RUN java -Djarmode=layertools -jar /root/target/Backend-0.0.1-SNAPSHOT.jar extract
RUN ls -l /root

FROM openjdk:8-jre

ENV TZ=UTC
ENV DB_IP=ec2-54-228-218-84.eu-west-1.compute.amazonaws.com
ENV DB_PORT=5432
ENV DB_USER=wkgravnujyofky
ENV DB_PASSWORD=b40f60ded972bd0700a8aeafd60243b51c13f00cb23026ef5d4a31eb7e45a2be
ENV DB_DBNAME=d4hpartfrcgrmu


COPY --from=builder /root/dependencies/ ./
COPY --from=builder /root/snapshot-dependencies/ ./

RUN sleep 10
COPY --from=builder /root/spring-boot-loader/ ./
COPY --from=builder /root/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher","-XX:+UseContainerSupport -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -Xms512m -Xmx512m -XX:+UseG1GC -XX:+UseSerialGC -Xss512k -XX:MaxRAM=72m"]
