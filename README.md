# Example REST API consumer
Spring Boot Application performing the task of fetching the data (Posts) from REST service, and saving it in the form of JSON files.

## Usage
To execute the application, simply open it IntelliJ or Eclipse IDE, and press run.  
In order to run it from console, make sure that your **JAVA_HOME** environmental variable is set to JDK 11 path, after that execute either 
```
gradlew.bat bootRun
```
or
```
./gradlew bootRun
```
depending on operating system you use.

### Further configuration
There is a set of custom properties defined in [application.properties](./src/main/resources/application.properties):
* provider.comment.rest.url - GET path for all comments
* provider.post.rest.url - GET path for all posts
* writer.save-path - name of directory which will contain saved posts
* writer.pretty-print - defines if json files should be pretty formated (true/false)
* batch.chunk-size - number of items handled at once by Spring Batch job
