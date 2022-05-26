FROM openjdk:11
VOLUME /tmp
ADD ./target/microservicios-productos-0.0.1-SNAPSHOT.jar microservicio-productos.jar
ENTRYPOINT ["java","-jar","/microservicio-productos.jar"]