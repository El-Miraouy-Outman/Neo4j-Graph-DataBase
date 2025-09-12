FROM openjdk:17-oracle
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 9898

#---------last step :jar not founded in target ---------------
FROM maven:3.8.7-openjdk-18 as build
RUN mvn -version
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
#RUN cat build.log  

#RUN ls -lah /build/target/

#
FROM openjdk:17-oracle
ENV PROFILE=prod
ENV DB_URL=${DB_URL}
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PASSWORD}
# il faut mieux de partager cette version entre dockerfile et gitlabCI
#ENV APP_VERSION=$(./mvnw -q -Dexec.executable='echo' -Dexec.args='${project.version}' --non-recursive exec:exec)
ENV APP_VERSION=1.0.2
WORKDIR /app
VOLUME /tmp

#RUN test -f /build/target/client-service-${JAR_VERSION}.jar || (echo "ERREUR: Le fichier .jar n'existe pas !" && exit 1)
COPY --from=build /build/target/client-service-*.jar /app/client-service-${APP_VERSION}.jar
EXPOSE 9898
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}
CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} /app/client-service-${APP_VERSION}.jar
#-----------------------
    #-----------------------

#FROM ubuntu
#run apt-get update 
#run apt-get -y install apache2
#add test.html /var/www/html
#RUN echo "ServerName localhost" >> /etc/apache2/apache2.conf
#CMD ["apachectl", "-D", "FOREGROUND", "-e", "info", "-c", "ServerName localhost"]

#ENTRYPOINT apachectl -D FOREGROUND
#ENV name=value
