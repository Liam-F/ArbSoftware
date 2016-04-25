#Shane Clarke
#G00303735

## ArbSoftware
###Final Year Project

---

###Description
My project is an arbitrage betting website. For information on what arbitrage betting is look here 
[sports betting worm](http://www.sportsbettingworm.com/arbitrage-betting/). My project is made up of two applications
the [ArbitrageScraper](/ArbitrageScraper) and the [ArbitrageWebsite](/ArbitrageWebsite). The scraper app scrapes different bookmakers
websites and performs calculations on the data it scrapes and the website provides people with a chance to use the data and place arbitrage bets.

In this Readme I will have a list of what this repository contains along with instructions on running the project.

---

###What is contains
- [ArbitrageScraper](/ArbitrageScraper) this folder contains all the source code for the scraper and the calculations.
- [ArbitrageWebsite](/ArbitrageWebsite) this folder contains all the source code for the website.
- [projectMovie.mov](/projectMovie.mov) is a 2 minute demonstration of the project being used.
- [accounts.xlsx](/accounts.xlsx) file is an excel sheet that shows all the bets my friends and I placed along with the profit we made from testing the application after receiving investment.
- [ArbitrageWebsite.war](/ArbitrageWebsite.war) is the .war file to be deployed to your tomcat server so you can run the website.
- [arbitrageScraper.jar](/arbitrageScraper.jar) is an executable jar file that you can run in order to find arbitrage opportunities.
- [umlProject.png](/umlProject.png) is a uml diagram of the ArbitrageScraper application.
- [ArbitrageTradingSite.sql](/ArbitrageTradingSite.sql) is a file that contains how the database was setup. However, I have the database for both applications hosted on godaddy.com so you should not need to run this file to use the application.
- [ArbitrageTable.png](/ArbitrageTable.png) is a screenshot of the table displayed on the website when it has found a few arbitrage opportunities.
- [project.pdf](/project.pdf) is the documentation for the project that was submitted to moodle.

---

###Running the project
This project contains some prerequisites in order to run.
- Java [Install java](http://docs.oracle.com/javase/7/docs/webnotes/install/) - this is needed to run both application files

- Apache tomcat webserver - [Install Apache tomcat](https://www.ntu.edu.sg/home/ehchua/programming/howto/Tomcat_HowTo.html) - this is needed in order to deploy the .war file and view the website.

***

Once you have both of them on your machine you can get to running the project.

1. Download [this](/ArbSoftware) repository.

2. The second thing you should do is add this snippet to your context.xml in the conf folder in tomcat. Anywhere in between the context tags is fine.
  ```xml
  <Resource name="jdbc/ArbitrageTradingSite" auth="Container" type="javax.sql.DataSource"
     maxActive="100" maxIdle="30" maxWait="10000"
     username="shaneclarke" password="1Thunder1" driverClassName="com.mysql.jdbc.Driver"
     url="jdbc:mysql://160.153.162.158:3306/ArbitrageTradingSite"/>
  ```
This is needed in order for the website app to connect to the database.

3. From the downloaded folder locate the ArbitrageWebsite.war and copy it into tomcats webapps folder.

4. Open up the terminal or cmd prompt navigate to tomcats bin folder and start the tomcat server.

5. Once thats done you should be able to go to http://localhost:8080/ArbitrageWebsite/Controller and see the website, but it wont be finding arbitrage opportunities yet.

6. Open another cmd prompt or terminal and navigate to the downloaded folder. Run this command to start the scraper.
  - java -jar arbitrageScraper.jar 
  
 This will start the scraper and find arb opportunities.

7. Go back to the browser and go to http://localhost:8080/ArbitrageWebsite/Controller then sign up and go to the trades page and there you have it.

Sometimes the trades page of the website will have no data even while the scraper is running, this is common as it might not find arb bets every time it scrapes the pages. During the morning or late afternoon is the time it finds the most opportunities, it rarely finds any at night time.
