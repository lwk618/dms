# dms

Server side deploy
Environmental Requirement

	Windows 7/10 with 64bit
	Java 1.8
	MySQL 5.5.27 or above
	

Installation Steps
	
	1. Install XAMPP, the install file embedded (xampp-win32-5.5.38-2-VC11-installer.exe)
	
	2. When finish install XAMPP, open the "XAMPP" to start the "Apache" and "MySQL" service
	
	3. Press the "Admin" button of "MySQL" servic, it will open browser and go the phpMyAdmin home page
	
	4. You can login to phpMyAdmin without username and password, just press the "Go" button
	
	5. After logged in to phpMyAdmin, click the "User" tab
	
	6. Create MySQL database user with below setting
		username:dms
		password:dms
		host:localhost
		Global privileges:select all
		
	7. Click the "SQL" tab, then copy and run the SQL in "dms.sql" file to create schema and table with data. Finish install database.
	
	8. Copy "wildfly-9.0.1.Final" folder into C:\
	
	9. Double click the "run.bat" shortcut to run the "Wildfly" server. After few minutes, the server container finish initialize.
	
	10. Done. Finish the server side deployment.