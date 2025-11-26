FROM tomcat:9-jdk11

RUN rm -rf /usr/local/tomcat/webapps/*

COPY shopping-app.war /usr/local/tomcat/webapps/shopping-cart.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
