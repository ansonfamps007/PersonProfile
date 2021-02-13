# Person Profile
API which provides a service for storing, updating, retrieving and deleting Person entities

## Installation Instructions

Beacon Engine uses a number of open source projects & databases and datazoom APIs to work properly:
```
* [Spring Boot] - Java based framework
* [H2 Database] - In memory database
```
Follow the steps to run beacon engine
1. Clone the repository
```
git clone https://github.com/ansonfamps007/PersonProfile.git
```
2. Open PersonProfile in Eclipse IDEA 
3. Run the application

## Dependencies

### Java 11
Person Profile requires [Java 11] to run.

### Building for source
For local development:
```
$ git clone git@github.com:ansonfamps007/PersonProfile.git
$ cd PersonProfile
$ gradlew clean build
```

### Docker
Person Profile is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 8080, so change this within the Dockerfile if necessary. When ready, simply use the Dockerfile to build the image.

```
$ cd PersonProfile
$ gradlew clean build
$ docker build -t person-profile:0.0.1 .
```
This will create the beacon-engine image and pull in the necessary dependencies.

Once done, run the Docker image and map the port to whatever you wish on your host. In this example, we simply map port 8080 of the host to port 8080 of the Docker :

```
docker run -d -p 8080:8080 person-profile:0.0.1
```

Verify the deployment by navigating to your server address in your preferred browser.

```
http://localhost:8080/actuator/health
```

## Usage instructions
 - Use the swagger-ui for API documents http://localhost:8080/swagger-ui.html
 - For this POC, credentials are hardcoded pass the below request to get access token 
	{
	"user_name":"testUser",
	"password":"test123"
	}

## Credits

 - Anson: Developed the initial version.
