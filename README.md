# step_project_tinder_IBA
A list of the working endpoints in a fully finished application:

__/users
__/liked
__/messages/{id}
__/login


__Order of tasks execution (you are not required to follow it):

__Create a new Maven app. Add required dependencies
__Launch Jetty web-server
__Create a test servlet which will output simple message 'Hello world' in the browser window
__Map the test servlet to the "/users" path
__Output a static HTML page with four elements - name, photo (a link to any picture from the web) and two buttons - Yes/No when requesting the "/users" path
__Wrap the page in an HTML form
__When a Yes/No button is pressed - send a POST request to the server (there is no handler there at the moment)
__Add POST request handler on the server and store the user's choice (yes or no) on the server (in any form)
__The doPost method should forward the request to the "/users" GET path
__Create a few profiles (name + link to a picture from the web)
__When a Yes/No button is pressed - show the next profile, the picture and name on the page should change
__When the list of the available profiles runs out - start showing them again
__Create a DAO class, store collection of profiles there
__Add a servlet with the "/liked" address, output a static (hardcoded) list of profiles that user "liked"
__After the user had clicked on all the available profiles, redirect him to the "/liked" page
__"/liked" page should only show the profiles for which we pressed Yes
__Add an "id" field to all profiles
__Add a servlet with the "/messages/{id}" address, show a static chat with hard-coded messages there
__When clicking on some profile on the "/liked" page, redirect to the "/messages/{id}" page where you will show a harcoded dialogue with the user
__Connect app to MySQL/PostgreSQL database (local or remote)
__Write a new implementation of the DAO classes so that all the users are stored in the database
__Show real messages between users on the "/messages/{id}" page
__Add a possibility to make a POST request on the server with a new message on this page
__Create a doPost method in the corresponding servlet, which will save the new message to the database
__Add a simple login page to the "/login" path
__Use Bootstrap templates as a base for all web pages
__Use Freemarker template to output HTML pages
__Handle POST method from the login page that will allow the user to log into the app. Store the data about the current user in cookies
__Create an HttpFilter, which will redirect the non-logged in user to the login page
__Add a possibility to log in from different accounts. Store each user's likes separately
__Use remote database
__Assemble the project into the executable JAR archive. Test locally that it is working
__Deploy the project on Heroku (or AWS)
