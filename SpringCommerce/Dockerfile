
#----------------------------------
# Stage 1 Build STage
#----------------------------------

# Import docker image with maven installed
FROM maven:3.8.3-openjdk-17 AS builder

# Add maintainer, so that new user will understand who had written this Dockerfile
LABEL maintainer="Ashish Jha <ajha5645@gmail.com>"

# Add labels to the image to filter out if we have multiple application running
LABEL app=SpringCommerce

# Set working directory
WORKDIR /src

# Copy source code from local to container
COPY . /src

#Download all the dependencies in offline mode also
RUN mvn dependency:go-offline

# Build application and skip test cases
RUN mvn clean install -DskipTests=true


#--------------------------------------
# Stage 2 Run Time Stage
#--------------------------------------

# Import small size java image
FROM openjdk:17-alpine as deployer

#Ask for any environment variable if required
ARG PROFILE=dev
ARG DATABASE_URL
ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=${PROFILE}
ENV DATASOURCE_URL=${DATABASE_URL}
ENV DATASOURCE_USERNAME=${DATABASE_USERNAME}
ENV DATASOURCE_PASSWORD=${DATABASE_PASSWORD}

# Set working directory
WORKDIR /app

# Copy jar file from builder stage to deployer stage(/src represent the build stage Working directory name we given /src/target/*.jar)
COPY --from=builder /src/target/*.jar /app/app.jar

# Expose the port
EXPOSE 8000

# Run the application
CMD ["java", "-jar", "/app/app.jar"]