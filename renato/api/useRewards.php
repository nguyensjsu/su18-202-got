<?php

require 'functions.php';

checkServerAuthorization();


// Checking and validating params
$inputs = getAndCheckParams(array(
	array("key" => "amount", "type" => "string", "required" => true),
	array("key" => "userId", "type" => "string", "required" => true),
));
$amount = $inputs["amount"];
$userId = $inputs["userId"];


if ($userId) {
	$result = addReward($userId, - $amount);
	return returnJSON($result);
}

returnErrorNotAuthenticated();

?>