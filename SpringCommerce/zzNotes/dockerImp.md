```bash
docker build \
--build-arg PROFILE=prod \
--build-arg DATABASE_URL=jdbc:<url> \
--build-arg DATABASE_USERNAME=<username> \
--build-arg DATABASE_PASSWORD=<password> \
-t springcommerce:1.0 \
-t springcommerce:latest .
```
.
Explanation:
1. docker build
   This is the command that tells Docker to build an image based on the Dockerfile and the context you provide.
   The docker build command will look for a Dockerfile in the current directory (or the directory you specify).
2. --build-arg PROFILE=dev
   This is a build argument.
   --build-arg allows you to pass a value to the Docker build process that will be used inside the Dockerfile.
   In this case, PROFILE=dev is setting the build argument PROFILE to dev. This will be used in the Dockerfile where the ARG PROFILE statement exists (in your case, it will be used to set the SPRING_PROFILES_ACTIVE environment variable).
   In your Dockerfile:

dockerfile
Copy code
ARG PROFILE=dev
ENV SPRING_PROFILES_ACTIVE=${PROFILE}
The value dev will be passed and set as the Spring profile (SPRING_PROFILES_ACTIVE) in your app during runtime.
3. --build-arg DATABASE_URL=<your-database-url>
   Another build argument passed to Docker.
   This will set the DATABASE_URL to the value you specify (<your-database-url> should be replaced with the actual URL of your database).
   In your Dockerfile:

dockerfile
Copy code
ARG DATABASE_URL
ENV DATASOURCE_URL=${DATABASE_URL}
The value of DATABASE_URL will be used to set the DATASOURCE_URL environment variable in the container at runtime.
4. --build-arg DATABASE_USERNAME=<your-database-username>
   A build argument that sets the DATABASE_USERNAME to a value you provide (<your-database-username>).
   This will be used to set the environment variable DATASOURCE_USERNAME during runtime.
   In your Dockerfile:

dockerfile
Copy code
ARG DATABASE_USERNAME
ENV DATASOURCE_USERNAME=${DATABASE_USERNAME}
5. --build-arg DATABASE_PASSWORD=<your-database-password>
   Another build argument for setting the DATABASE_PASSWORD to the password of your database (<your-database-password>).
   This will be used to set the environment variable DATASOURCE_PASSWORD during runtime.
   In your Dockerfile:

dockerfile
Copy code
ARG DATABASE_PASSWORD
ENV DATASOURCE_PASSWORD=${DATABASE_PASSWORD}
6. -t springcommerce:1.0
   -t is the option to tag the image.
   springcommerce:1.0 is the tag you are giving to your Docker image.
   The format of the tag is name:tag, where springcommerce is the name of the image, and 1.0 is the version (tag) of the image.
   Tagging the image as springcommerce:1.0 helps you identify the version of your application (in this case, version 1.0).
7. -t springcommerce:latest
   Another tag (springcommerce:latest) is being applied to the same image.
   This is a common tag used to denote the most recent version of an image. When you use latest, it's easier to refer to the most up-to-date version of your image without specifying a version number.
8. . (Dot)
   This specifies the build context.
   The dot . means "use the current directory as the context for building the image."
   Docker will send the entire contents of the current directory (including the Dockerfile and source code) to the Docker daemon to build the image.





[//]: # (Run the docker container)
```bash
docker run -e SPRING_DATASOURCE_URL=url \
           -e SPRING_DATASOURCE_USERNAME=username \
           -e SPRING_DATASOURCE_PASSWORD=password \
           -e SPRING_PROFILES_ACTIVE=prod \
           -p 8000:8000 springcommerce
```