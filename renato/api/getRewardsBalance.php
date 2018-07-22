<?php

require 'functions.php';

$userId = checkAuthorization();

if ($userId) {
	$result = [];
	$result['balance'] = getRewardsBalanceFromUserWithId($userId);
	return returnJSON($result);
}

returnErrorNotAuthenticated();

?>