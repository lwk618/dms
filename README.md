# dms

Server side deploy
Environment
	Window 7/10 with 64bit
	Java 1.8
	Mysql 5.6.1 or above
	

Installation Steps
	
	Install xampp 

	Run the Mysql service
	
	Create mysql database user with 
		username:dms
		password:dms
	
	Login mysql with user(dms)
	
	Run the sql in "dms.sql" file to create schema and table with data 
	
	Copy "wildfly-9.0.1.Final" folder into C:\
	
	Copy "Java 1.8" folder into C:\
	
	Double click the "run.bat" shortcut to run the "Wildfly" server. After few minutes, the server container finish initialize.
	
	Done. Finish the server side deployment.