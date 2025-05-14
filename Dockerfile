# Etapa 1: Construcción del WAR con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución en Tomcat con Java 21
FROM eclipse-temurin:21-jdk

# Instala Tomcat 10.1.40 manualmente (ya que no hay imagen oficial con Temurin 21 aún)
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

RUN apt-get update && \
    apt-get install -y curl && \
    curl -O https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.40/bin/apache-tomcat-10.1.40.tar.gz && \
    tar xzf apache-tomcat-10.1.40.tar.gz && \
    mv apache-tomcat-10.1.40 $CATALINA_HOME && \
    rm apache-tomcat-10.1.40.tar.gz

# Borra la app ROOT por defecto
RUN rm -rf $CATALINA_HOME/webapps/ROOT

# Copia el WAR generado desde la etapa de build
COPY --from=build /app/target/*.war $CATALINA_HOME/webapps/ROOT.war

# Render utiliza un puerto dinámico ($PORT)
ENV PORT=8080
EXPOSE $PORT

# Sustituye 8080 por $PORT en server.xml
RUN sed -i "s/port=\"8080\"/port=\"${PORT}\"/" $CATALINA_HOME/conf/server.xml

# Inicia Tomcat
CMD ["catalina.sh", "run"]
