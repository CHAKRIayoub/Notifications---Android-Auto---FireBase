<?php 

	//ce script permet d'ajouter un nouveau ID d'un telephone qu'on veut l'envoyer des alerts
	if (isset($_GET["Token"])  ) {
		
		   $_uv_Token=$_GET["Token"];

		   $conn = mysqli_connect("localhost","root","","fcm") or die("Error connecting");

		   $q="INSERT INTO users (Token) VALUES ( '$_uv_Token') "
              ." ON DUPLICATE KEY UPDATE Token = '$_uv_Token';";

           echo "success";
              
      mysqli_query($conn,$q) or die(mysqli_error($conn));

      mysqli_close($conn);

	}else echo "no token";


 ?>
