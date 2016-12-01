# dms

Server side deploy
Environment

	Windows 7/10 with 64bit
	Java 1.8
	MySQL 5.6.1 or above
	

Installation Steps
	
	1. Install XAMPP
	
	2. Run the MySQL service
	
	3. Create MySQL database user with below setting
		username:dms
		password:dms
	
	4. Login MySQL with "dms" user
	
	5. Run the SQL in "dms.sql" file to create schema and table with data 
	
	6. Copy "wildfly-9.0.1.Final" folder into C:\
	
	7. Copy "Java 1.8" folder into C:\
	
	8. Double click the "run.bat" shortcut to run the "Wildfly" server. After few minutes, the server container finish initialize.
	
	9. Done. Finish the server side deployment.