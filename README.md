# TEAM 2 CS2TP
  <img src="https://user-images.githubusercontent.com/99501966/224847617-72f1a015-516f-4df6-9ace-d4d300a46850.png" width:200  style="max-width=%100">
<h2>About Games Station </h2> 

Game Station is an e-commerce platform aimed at gamers who are looking for a convenient and reliable online store to purchase their video games and accessories. The target audience for Game Station includes a wide range of ages starting from 10 years old who are interested in video games, including both enthusiastic and hardcore gamers, as well as casual players, all individuals can find a vast selection of gaming products at competitive prices.

It is a website that is dedicated to the gaming community. It provides an extensive collection of gaming products such as video games, consoles, accessories, and tech products. The design of the website is intended to be user-friendly, with a search function that enables customers to search for specific products easily. The website's colour theme features a combination of purple, white, blue, and black, which are carefully selected to create a modern and professional appearance that appeals to gamers. The purple colour is frequently linked to qualities such as creativity, and passion, which aligns with the culture of gaming. White and blue colours provide a clean and calming appearance and provide contrast to the purple colour. Black is a timeless and classic colour that adds sophistication and elegance to the overall
design. Using these colours on the website helps establish a robust and easily recognizable brand identity that is memorable.

The functionality of the Game Station website is designed with the user in mind. The website is easy to navigate, with clear categories and subcategories that allow customers to quickly find the products they are looking for. The website aims to become a one-stop shop for all gaming related needs, catering to the needs of gamers of different ages and preferences with a commitment to quality, reliability, and customer satisfaction.

<h2>Setup Games Station E-Commerce System</h2>
The following project will work on Eclipse and IntelliJ IDE. Though it is recommended to use IntelliJ Ultimate or the Community Edition of IntelliJ.

- To get Eclipse IDE for Enterprise Java and Web Developers: https://www.eclipse.org/downloads/packages/

- To get IntelliJ go to: https://www.jetbrains.com/idea/

You will also need to download an open-source software package that provides a local web server environment for testing and developing web applications, such as XXAMP which contains tools and utilities, such as phpMyAdmin (for managing databases).

- To get XXAMP go to: https://www.apachefriends.org/download.html


<h3> Clone the repository:</h3>

1. Open the command prompt or terminal on your computer, and navigate to the directory where you want to clone the repository.This can be done by typing "cd" followed by the path of the directory you want to navigate to. For example, if you want to navigate to the "Documents" folder, type "cd Documents" and press Enter.
2. Copy the URL of the repository you want to clone from GitHub. You can find the URL on the repository's page by clicking the green "Code" button as shown below.
  <img src="https://user-images.githubusercontent.com/99501966/227723986-6238e6b5-5290-45e1-8af8-d5560733172f.png" width:100  style="max-width=%100">
3. In the command prompt or terminal, enter the following command: git clone (repository URL), replace (repository URL) with the URL of the repository you have copied, and press enter to run the command, and Git will begin cloning the repository to your local machine.

<h3> Database Configration:</h3>
1. Once you downloaded XXAMP you need to launch it and start the Apache and MySQL servers: In the XAMPP Control Panel, click on the "Start" button next to "Apache" and "MySQL" to start the web server and database server <img src="https://user-images.githubusercontent.com/99501966/227725750-9f4ab1e5-45f2-42e0-8e76-79b9b6488611.png" width:100 style="max-width=%100">
2. Open a web browser and navigate to http://localhost/phpmyadmin/. This will open the phpMyAdmin interface for managing MySQL databases.

3. To create a new database, click on the "New" button in the left-hand sidebar to create a new database. Enter a name for the database and select create.

4. Configure the database connection properties in the application.properties as shown below <img src="https://user-images.githubusercontent.com/99501966/227725612-61d34841-8d06-420b-9481-5aabd6c24768.png" width:100 style="max-width=%100">

5. You can replace gamestation, myusername, and mypassword with the actual values for your database.
 
<h3> Run the application:</h3>
  
1. Open IntelliJ IDEA and select "file" -> "open" ->. 
2. Navigate to the directory where the Spring Boot project is located, select it and click ok.
3. Once the project is loaded, navigate to the main class of your Spring Boot application.   <img src="https://user-images.githubusercontent.com/99501966/227722979-7ab819e5-56a1-4c91-b2e6-daf39a13cc6a.png" width:100  style="max-width=%50">
4. Right-click on the main class, select the correct Run Configuration, and IntelliJ IDEA will build the application and start it.
 <img src="https://user-images.githubusercontent.com/99501966/227724099-4ba24ca6-920c-41ad-b15d-f61c64178a21.png" width:100  style="max-width=%50">

<h3> Access the application: </h3>

Once the application is running, open a web browser and navigate to http://localhost:8080. This should take you to the home page of the Spring Boot application.

<h2>Team Members</h2>
<table>
  <thead>
    <tr>
      <th>Name</th>
      <th>ID Number</th>
      <th>Roles</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>RAHEEB ABDUSALAM</td>
      <td>210119617</td>
      <td>Back-end developer & Code Manager</td>
    </tr>
    <tr>
      <td>IBRAHEEM KHAN</td>
      <td>200154725 </td>
       <td>Back-end developer & Team leader </td>
    </tr>
    <tr>
      <td>ALABAS ALATIKI </td>
      <td>200127774</td>
       <td>Back-end developer & Mental health champion</td>
    </tr>
    <tr>
      <td>MIKHAIL MIRZA</td>
      <td>190163329</td>
      <td>Front-end developer & Scrum master </td>
    </tr>
    <tr>
      <td>BOLUWATIFE OLUSINA</td>
      <td>220036388</td>
       <td>Front-end developer & Project manager </td>
    </tr>
    <tr>
      <td>RAIHAAN ARSHAD </td>
      <td>190070142 </td>
      <td>Back-end developer & Designer</td>
    </tr>
     <tr>
      <td>ALISHA WALTERS </td>
      <td>210178500 </td>
      <td>Front-end developer & Designer  </td>
    </tr>
     <tr>
      <td>GURVIR BRAR</td>
      <td>210113442</td>
      <td>Front-end developer</td>
    </tr>
  </tbody>
</table>
