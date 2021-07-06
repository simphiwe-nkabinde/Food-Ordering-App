# Food-Ordering-App
A food ordering app built with Java. It uses the JDBC API to interact with a Database.

## Detailed Description
This project was part of a School Capstone Project.
The app meets the following project requirements and criteria:
- Design and create a database for the App.

**Write a Java programs that:**
- Reads and writes data about restaurants and customers associated with drivers from your database
- Captures information about new customers and adds it to the database
- Update information about existing customers
- Finalise existing orders. An invoice should be generated for the customer in the form of a .txt file
- Include exception handling.
- Remove all errors from your code
- Document your code. Adhere to the style guide.
In addition to the requirements, the app has a Graphical User Interface built with the SWING GUI Java Toolkit.
This allows for a good user experience for the user.

## Installation
- download the zip repository folder
- unzip the folder
- the folder contains contains a folder called 'sql query scripts' in the 'Database' folder. It contains all the MS SQL query scripts to create the relevant database objects.
execute the scripts in your database directory using MS SQL SERVER. The Database folder also contains image and txt files that illustrate the structure of the database
- Open the files contained in the 'QuickFoodApp' folder using eclipse to run the java files.  
- The app uses the JDBC Java API to interact with the datbase. You'll have to change the url, username and password 
for every instance of the DriverManager.getConnection() method in all the Connection object instances. Use your own databases credentials to ensure that the app connects successful to your database. 
The following .java files will require this change: Customer, Driver, Invoice, OrderData and Restaurant

## Usage
- Once all that has been set up you should be good to go. Run the app from the eclipse environment.
- A new window will appear displaying the app user interface. 
- The app will prompt you to enter your details will you can submit to the databse by clicking the submit button.
- click next to display the available restaurants
- select a restaurant to order from then click next
- A menu will appear with the option of selecting the quantity of each meal you would like to order
- once you have indicated the quantity of each meal you wish to order, click the add button beside the meals you wish to order.
- type in any special preparation instructions in the text box at the bottom.
- click the order button to make your order.
- A text file will open up in a seperate window containing your invoice. The invoice contains you personal info, order info and information about the nearest driver to your location who will be delivering your food
