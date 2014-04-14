# DOCKER-VERSION 0.7.6

FROM 	ubuntu:13.10

# Install OpenJDK
RUN 	apt-get -y install openjdk-7-jdk

# Install Maven
RUN 	apt-get -y install maven2

# Define mount point for host sources
VOLUME ["/sources"]

# Set working directory
WORKDIR /sources

# Build the application
CMD mvn clean install

