Step 1 : First, we will identify the event in user portfolio.
         For example: Change in user profile information(like email address update)
		 
Step 2 : A user can be configured for one or more notifications (Push/SMS/Email)
         get the list of the type of notifications (Push/SMS/Email) the user is configured.

Step 3 : Create a seprate thread for sending notification and submit it to executor services.
		 
Step 4 :  The main thread will complete its normal transaction while sending notification will be an asynchronous process.

Step 5 : Sending notification will first get the notification template, send the notification and save the notification for record in a database.

As a proptotype we have created project SmartAlertServices, with a controller "UserController". Which accept a POST request http://localhost:8080/updateUserProfile with the user data (please refer SmartAlertServices_Request.JPG for refrence).
and if there is change in email address it will send the alert to user alsng with saving the user profile.