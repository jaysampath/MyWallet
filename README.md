# MyWallet

MyWallet is a simple credit/debit wallet backend application.

MyWalletBE is the backend application source code.

TechStack - Springboot, Spring security, MySQL, docker

<h2> Features </h2>
<ul>
   <li>Login/Signup interface</li>
   <li>JWT token verification</li>
   <li>Transaction password while adding/withdrawing money to wallet</li>
   <li>Transaction History</li>
</ul>

MyWallet.postman_collection.json is the Postman collection. Import it and run the application

<h2> How to run it  ? </h2>
<ol>
  <li> cd docker </li>
  <li> docker-compose up </li>
  <li> Run the SpringBootApplication - MyWalletBEApplication.java </li>
</ol>
The application connects to mysql docker container and comes up.

Fire up the endpoints using postman after importing them - https://github.com/jaysampath/MyWallet/blob/main/MyWallet.postman_collection.json

Here is the functionality recording link - https://github.com/jaysampath/MyWallet/blob/main/MyWalletFuntionalityRecording.webm



