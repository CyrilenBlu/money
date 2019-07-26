FROM tomcat:8-jre8
MAINTAINER Luke Petzer <lukepetzerint2@gmail.com>
EXPOSE 8080
ARG WAR_FILE
COPY ${WAR_FILE} /usr/local/tomcat/webapps/client.war
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/local/tomcat/webapps/client.war" ]
