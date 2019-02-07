# WolframAlpha

WolframAlpha API using Java

## Technologies / Tools used : 
* WolframAlpha API
* Java
* Maven
* Gson
* OkHttp

To run the program first create an account on WolframAlpha's developer page, http://developer.wolframalpha.com/portal/myapps/ .
Then click on the create an App ID button which will generate an APPID and the screen will look something similar to this.

Copy the AppName and AppId and store it in a text file named wolframAPI.txt

The program will grab the APPID from this file while making the API calls.

Once the repo is cloned, run the command ```mvn clean package```

This will generate a BIG FAT JAR, which we'll use to execute the program.

Execute the program by running the command ```java -jar target/search-1.0-jar-with-dependencies.jar <search arguments>```
