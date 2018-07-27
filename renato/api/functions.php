<?php

date_default_timezone_set('America/Los_Angeles');

define("API_KEY", "cmpe202kungfubelikewater"); // key to protect our API from random requests


require 'db.php';


function getUserIdFromToken($token){
	return $token; // for this first version, we are going to use the userId as token
}



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
    $email = mysqli_real_escape_string($mysqli, $email);
    $res = doQueryInDatabase("SELECT balance FROM rewards WHERE user_id = '$userId' LIMIT 1");
    if ($res->num_rows == 0)
        return null;

    $row = $res->fetch_assoc();

    return $row['balance'];
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
	if ($userObj== false)
		returnErrorNotAuthenticated("Invalid User Token");

	return $userId;
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