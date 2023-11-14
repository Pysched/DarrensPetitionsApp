# Use the Tomcat image as the base image
FROM tomcat:latest

# Remove the default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR file into the Tomcat webapps directory
COPY ./target/DarrensPetitions.war /usr/local/tomcat/webapps/


# Expose the port the Tomcat server will be listening on
EXPOSE 8082
