<?php

require 'functions.php';

$userId = checkAuthorization();

// Checking and validating params
$inputs = getAndCheckParams(array(
	array("key" => "email", "type" => "string", "required" => true),
	array("key" => "name", "type" => "string", "required" => true),
));
$name = $inputs["name"];
$email = $inputs["email"];


if ($userId) {
	$result = [];
	$result['result'] = insertOrUpdateUserAccount($userId, $name, $email);
	return returnJSON($result);
}

returnErrorNotAuthenticated();

?>