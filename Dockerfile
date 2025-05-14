# Use official Tomcat 10.1.40 image with JDK 17
FROM tomcat:10.1.40-jdk17

# Remove default ROOT app (optional, to avoid conflicts)
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy your WAR file to the webapps folder and name it ROOT.war
# So your app is served at the root URL (/)
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose the default port Render expects (dynamic, Render sets $PORT)
ENV PORT=8080
EXPOSE $PORT

# Replace port 8080 with the environment variable $PORT (Render requirement)
RUN sed -i "s/port=\"8080\"/port=\"${PORT}\"/" /usr/local/tomcat/conf/server.xml

# Start Tomcat
CMD ["catalina.sh", "run"]
