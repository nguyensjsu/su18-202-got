<?php

date_default_timezone_set('America/Los_Angeles');

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



// - - - - - - - - - - - - -
// Return Errors

function returnErrorDatabase(){
	global $mysqli;
	returnError(1, "An error occurred communicating with the database", $mysqli->error);
}


function returnError($code, $msg, $msgDetail = null){
	$ret = new StdClass();
	$ret->errorCode = $code;
	$ret->errorMessage = $msg;
	$ret->errorMessageDetail = $msgDetail;
    returnJSON($ret);
}


?>