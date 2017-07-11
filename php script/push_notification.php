<?php 

	//cette fonction permet d'envoyer une requete JSON au serveur Firebase Cloud messaging avec l'adresse https://fcm.googleapis.com/fcm/send
	function send_notification ($tokens, $message)
	{
		//l'adresse du serveur firebse cloud messaging
		$url = 'https://fcm.googleapis.com/fcm/send';

		//registration_ids : les ids des telephones qu'on veut l'envoyer des alerts
		//data : le message d'alert à envoyer
		$fields = array(
			 'registration_ids' => $tokens,
			 'data' => $message
			);

		//Authorization:key :la clé de notre serveur, récupérée depuis la condole firebase de notre projet
		$headers = array(
			'Authorization:key = AIzaSyDtzGzCG1jb0JazmmMw6Azw_V-lIBDoAtQ',
			'Content-Type: application/json'
			);

		//curl : un outil permet de produire et envoyer des réquete HTTP
	   $ch = curl_init();
       curl_setopt($ch, CURLOPT_URL, $url); 
       curl_setopt($ch, CURLOPT_POST, true);
       curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);  
       curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
       curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
       $result = curl_exec($ch);           
       if ($result === FALSE) {
           die('Curl failed: ' . curl_error($ch));
       }
       curl_close($ch);
       return $result;
	}

	//récupérer le message a envoyée
	if(isset($_GET['msg'])) $msg = $_GET['msg'];
	else $msg = "firebase sever";
	
	//connexion avec la base de données
	$conn = mysqli_connect("localhost","root","","fcm");

	//récupérer la liste des IDs des telephones qu'on veut l'envoyer des alerts
	$sql = " Select Token From users";


	$result = mysqli_query($conn,$sql);
	$tokens = array();

	if(mysqli_num_rows($result) > 0 ){

		while ($row = mysqli_fetch_assoc($result)) {
			$tokens[] = $row["Token"];
		}
	}

	mysqli_close($conn);


	$message = array("message" => $msg);

	//lancer la fonction et afficher le résultat
	$message_status = send_notification($tokens, $message);
	echo $message_status;

 ?>
