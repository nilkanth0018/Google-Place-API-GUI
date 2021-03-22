# Google-Place-API-GUI

How to create build
- Install maven on your system.
- Clone this repository to your local machine
- Open command prompt and to create build use below commands
  
  - MVN CLEAN
  - MVN INSTALL
  - MVN PACKAGE
  
 - Now build is sucessfully created
 - Go to target folder and copy config, resources, logs folder along with created jar file and paste to your desire location.
 
 NOTE: To run the project you need google API key.
 
 # How to enable Google Place Search API and Generate API Key
 
 - Login to your google account.
 - Open https://console.cloud.google.com/
 - Create a project
 - Go To APIs & Services tab and open API Library.
 - Search Google Place API in the search bar and Enable the API.
 - Go To Credentials tab to generate API Key.
 - Click on CREATE CREDENTIALS >> API Key.
 - Now your API key is generated copy that API key and paste it into config/application.properties file and change bwlow varible.
 
    - google.api.key=[YOUR GOOGLE PLACE API KEY]
    
- now you are ready to run the project.
 
 
