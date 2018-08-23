release

edit ~/.m2/settings.xml

<servers>  
     <server>
        <id>github.com</id>  
        <username>YOUR_GITHUB_USERNAME</username>  
        <password>YOUR_GITHUB_PASSWORD</password>  
     </server>   
  </servers>

mvn release:prepare -DreleaseVersion=1.0.0 -DdevelopmentVersion=1.0.1-SNAPSHOT
mvn release:perform -Dgoals="clean install"