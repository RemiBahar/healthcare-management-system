
# Set-Up Procedure

It is recommended to run this project using docker. This way the patient service has better isolation as the only thing needed to run the project is the docker engine and a docker hub account. 

# Using Docker

## Pre-requisites
* [Docker engine installed](https://docs.docker.com/engine/install/) and [A docker hub account](https://hub.docker.com/signup)
* An image of the system published to docker hub

If you would like to publish a new image, please see the steps below

Create a JAR of the patient service 
```
cd api
mvn clean package
```

Restart docker
```
sudo service docker restart
```

Create docker image
```
sudo docker build –t patient-api-0.1.0 . 
```

Run docker container
```
sudo docker run -p8090:8090 -v /tmp:/home/remi/database -d patient-api-0.1.0 
```

Check docker container service is working
```
curl --request GET localhost:8090/odata/$metadata
```

Login to docker-hub
```
sudo docker login
```

Push the image to docker-hub

```
sudo docker image ls 
sudo docker tag LOCAL_REPOSITORY:LOCAL_TAG DOCKER_USERNAME/REMOTE_REPOSITORY:REMOTE_TAG
sudo docker push DOCKER_USERNAME/REMOTE_REPOSITORY:REMOTE_TAG
```

* `LOCAL_REPOSITORY:LOCAL_TAG` - `REPOSITORY:TAG` of docker container locally after running `sudo docker image ls`
* `DOCKER_USERNAME` - username you use to login to docker hub
* `REMOTE_REPOSITORY:REMOTE_TAG` - name of remote repository and tag used for container


## Steps

1. Login to docker-hub

```
docker --version
sudo docker login
```

2. Pull patient docker image

```
sudo docker pull remibahar/healthcare-management:patient
```

3. Run patient service via docker

```
cd ~
pwd
```

Copy the directory outputted we will refer to it as `<HOME>`

```
sudo docker image ls
sudo docker run -p8090:8090 -v /tmp:<HOME>/database -d IMAGE_ID
```

If everything went well you should be able to see the metadata by running the following command on another computer

```
curl http://<ip>:8090/odata/$metadata
```

where `<ip>` is the ip address of the host running the patient service.


Sometimes a firewall may stop requests from reaching the service. To check this run

```
telnet <ip> 8090
```

If this gets stuck on trying then the firewall is the likely culprit.


## Useful commands

* Run with H2

```
sudo docker run -p 8090:8090 -v /tmp:~/database -d patient-api-0.1.0 
```

* Run with local postgresql

```
sudo docker run -p 8090:8090 -e "SPRING_PROFILES_ACTIVE=postgresql" -d patient-api-0.1.0 
```

* Stopping the docker service

```
sudo docker ps
sudo docker stop CONTAINER_ID
```

# Without Docker

## Pre-requisites

* [Java](https://docs.oracle.com/javase/9/install/installation-jdk-and-jre-linux-platforms.htm#JSJIG-GUID-737A84E4-2EFF-4D38-8E60-3E29D1B884B8)

* [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

Git may already be installed, check using `git --version`

* [Maven](https://maven.apache.org/install.html)

On Ubuntu maven can be installed simply with,
```
sudo apt update
sudo apt install maven
```

Once installed run `mvn --version`


## Steps

1. Clone the repository

```
git clone https://github.com/RemiBahar/patient.git

```

2. Run project

```
cd patient/api
mvn clean install
mvn spring-boot:run
```

If everything went well you should be able to see the metadata by running the following command on another computer

```
curl http://<ip>:8090/odata/$metadata
```

where `<ip>` is the ip address of the host running the patient service.


Sometimes a firewall may stop requests from reaching the service. To check this run

```
telnet <ip> 8090
```

If this gets stuck on trying then the firewall is the likely culprit.

## Useful commands

* Re-compile

```
mvn clean install
```

* Test the service

```
mvn clean test
```

* Run the service (by default it uses a H2 database)

```
mvn spring-boot:run
```

* Run the service using a local postgresql database

```
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```


# Setting up a new API

Apart from the code used to define the patient data model in the /model directory, the rest of the code in this API can be reused as is for other APIs.

To use this API's code to build an API to manage a different set of data, do the following:

1. Follow the instructions in the previous two sections
2. Define a data model in the [/model](/api/src/main/java/com/cmd/hms/patient/model/) directory. See [SystemDocumentation.md](SystemDocumentation.md) for guidance on how the data model works
3. Define test cases in the [/test](/api/src/test/java/com/cmd/hms/patient/test/) directory. See [Testing.md](Testing.md)

We will now go through the rest of the code used in this API. Please see the System Architecture section for more detail.

1. [pom.xml](/patient-api/api/pom.xml) is used by maven to manage any dependencies the API has

2. [application.properties](/patient-api/api/src/main/resources/application.properties) is where can configure extra details for our API. In this API we have configured it to connect to a postgresql database called patient using the postgres user. The password is plaintext but should be stored using something like an environment variable for extra security.

3. [JerseyConfig.java](/patient-api/api/src/main/java/com/cmd/hms/patient/config/JerseyConfig.java) is used to set-up our OData service

5. The [service](/patient-api/api/src/main/java/com/cmd/hms/patient/service) directory is used to set-up JPA with the OData service. 

    * [CustomODataJpaProcessor.java](/patient-api/api/src/main/java/com/cmd/hms/patient/service/CustomODataJpaProcessor.java) defines generic readEntitySet, readEntity, createEntity, updateEntity, deleteEntity that can be used for CRUD operations of any entity we define in our data-model. This lets the developer define the data model and have the API interactions and database interactions handled automatically

    * [CustomOdataServiceFactory.java](/patient-api/api/src/main/java/com/cmd/hms/patient/service/CustomODataServiceFactory.java) creates our OData service

    * [ODataJpaServiceFactory.java](/patient-api/api/src/main/java/com/cmd/hms/patient/service/ODataJpaServiceFactory.java) is a wrapper for spring since we are using the Spring-framework

6. [Application.java](/patient-api/api/src/main/java/com/cmd/hms/patient/Application.java) is another wrapper for spring