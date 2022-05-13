# filesystem
File System Service


Dependencies:
1. Java 8
2. Maven


Steps to set up local DB:
1. Import the project via pom.mxml in IDE of your choice (Recommended: Intellij)
2. Set the following property in application.properties file
   spring.servlet.multipart.location=<local-directory-path-of-your-choice>
3. Run the spring boot app by running org.filesystem.storage.Main class, this should start the server.
4. Navigate to localhost:8082/swagger-ui.html in browser to view the API docs and test them. 
