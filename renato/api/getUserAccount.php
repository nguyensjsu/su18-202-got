<?php

require 'functions.php';

$userId = checkAuthorization();

if ($userId) {

	$userObj = getUserObj($userId);
	return returnJSON($userObj);
}

returnErrorNotAuthenticated();

?>