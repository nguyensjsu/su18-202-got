<?php

date_default_timezone_set('America/Los_Angeles');

define("API_KEY", "cmpe202kungfubelikewater"); // key to protect our API from random requests


require 'db.php';




// - - - - - - - - - - - - -
// Database functions


function doQueryInDatabase($query) {
	global $mysqli;

	$res = $mysqli->query($query);

	if ($res)
		return $res;

	returnErrorDatabase();
}


function getRewardsBalanceFromUserWithId($userId){
	global $mysqli;
    $userId = mysqli_real_escape_string($mysqli, $userId);
    $res = doQueryInDatabase("SELECT balance FROM rewards WHERE user_id = '$userId' LIMIT 1");
    if ($res->num_rows == 0)
        return 0;

    $row = $res->fetch_assoc();

    return $row['balance'];
}


function insertOrUpdateUserAccount($userId, $name, $email) {

	$userObj = getUserObj($userId);

	if ($userObj != null){
		$res = doQueryInDatabase("UPDATE users SET name ='$name', email = '$email' WHERE user_id = '$userId'");
	} else {
		$res = doQueryInDatabase("INSERT INTO users (name, email, user_id) VALUES ('$name', '$email', '$user_id')");
	}

	return $res;
}


function getUserObj($userId) {

	$res = doQueryInDatabase("SELECT name, email, user_id as 'id' FROM users WHERE user_id = '$userId' LIMIT 1");
    if ($res->num_rows == 0)
    	return null;

  $row = $res->fetch_assoc();

	return $row;
}

// - - - - - - - - - - - - -
// Aux functions


function checkAuthorization($onlyCheckAPIKey = false) {
	if (!isset($_REQUEST["apiKey"]) || $_REQUEST["apiKey"] !== API_KEY )
		returnErrorNotAuthenticated("Wrong API Key");

	if ($onlyCheckAPIKey)
		return true;

	if (!isset($_REQUEST["token"]))
		returnErrorNotAuthenticated("Missing User Token");

	$userId = getUserIdFromToken($_REQUEST["token"]);
	if ($userId== null)
		returnErrorNotAuthenticated("Invalid User Token");

	return $userId;
}

//
// exmaple of use:
// $inputs = getAndCheckParams(array(
// 	array("key" => "filename", "type" => "string", "required" => true),
// 	array("key" => "contents", "type" => "string", "required" => false),
// 	array("key" => "fileObject", "type" => "file",  "required" => false),
// ));
// http://php.net/manual/en/function.gettype.php
function getAndcheckParams($list) {
	$params = array();
	foreach ($list as &$param) {
		$key = $param['key'];
		$type = $param['type'];
		$required = $param['required'];

		$origin = $_REQUEST;
		if ($type == "file") {
			$origin = $_FILES;
			$type = "array";
		}

		if ($required && !isset($origin[$key]) )
			returnErrorRequiredParams("Missing Required Param '$key'");

		if (isset($origin[$key])) {
			$value = $origin[$key];

			if ($type === "boolean")
			 	$value = (boolean) $value;

			if (gettype($value) != $type)
				returnErrorRequiredParams("Wrong type for key '$key'");

			if ($required && $type == "string" && empty($value))
				returnErrorRequiredParams("Missing");

			$params[$key] = $value;
	 	}
	}
	unset($param);

	return $params;
}


function getUserIdFromToken($token){
	do_log("getUserIdFromToken");
   $curl = curl_init();

   curl_setopt_array($curl, array(
   CURLOPT_URL => "https://safe-caverns-72745.herokuapp.com/",
   CURLOPT_RETURNTRANSFER => true,
   CURLOPT_ENCODING => "",
   CURLOPT_MAXREDIRS => 10,
   CURLOPT_TIMEOUT => 30,
   CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
   CURLOPT_CUSTOMREQUEST => "GET",
   //CURLOPT_POSTFIELDS => "repo=".$repoName,
   CURLOPT_HTTPHEADER => array(
    "Cache-Control: no-cache",
    "Content-Type: application/x-www-form-urlencoded",
    //"Postman-Token: 821acf75-e555-4d23-8f23-2f062a7a5638",
    "authorization: $token",
   ),
   ));

   do_log("going to send request");
   $response = curl_exec($curl);
   $err = curl_error($curl);

   curl_close($curl);

   if ($err) {
      do_log("cURL Error #:" . $err);
   		return ;
   	}
  do_log("result=" .  $response);
  $json = json_decode($response, true);
  return $json["id"];
}



// - - - - - - - - - - - - -
// Return Errors


function returnErrorDatabase(){
	global $mysqli;
	returnError(1, "An error occurred communicating with the database", $mysqli->error);
}

function returnErrorNotAuthenticated($errorMsgDetail){
    returnError(2, "You are not logged in. Please sign in.", $errorMsgDetail);
}

function returnErrorRequiredParams($errorMsgDetail = null){
    returnError(3, "Missing/Invalid params", $errorMsgDetail);
}



function returnError($code, $msg, $msgDetail = null){
	$ret = new StdClass();
	$ret->errorCode = $code;
	$ret->errorMessage = $msg;
	$ret->errorMessageDetail = $msgDetail;
    returnJSON($ret);
}


// - - - - - - - - - - - -
// LOG FUNCTIONS

function do_log_request() {
    $fd = fopen(__DIR__ . "/log.txt", "a");
    fwrite($fd, "==================================================\n");
    fwrite($fd, "Date: ".date('r')."\n");
    fwrite($fd, "Original URL: ".$_SERVER['REQUEST_URI']."\n");
    fwrite($fd, "Parameters:\n");
    foreach ($_REQUEST as $k => $v):
        if (strlen($v) > 300 && $k != 'access_token'):
            fwrite($fd, "{$k} => ".substr($v,0,300)." (...)\n");
        else:
            fwrite($fd, "{$k} => {$v}\n");
        endif;
    endforeach;
    fclose($fd);
} // do_log


function do_log($str) {
    $fd = fopen(__DIR__ . "/log.txt", "a");
    fwrite($fd, date('r'). ': ' . $str."\n");
    fclose($fd);

} // do_log


// - - - - - - - - - - - - -
// Return Json

function returnJSON($obj) {
    header("Content-type: application/json");
    $out = json_encode($obj, JSON_PRETTY_PRINT);
    print $out;
    exit;
}


?>