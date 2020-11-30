<?php

function sendPushNotification($to = '', $data = array()){
	$apiKey = 'AIzaSyC59NA1IcrtDQIklt4DYdEQwpgx7u_kMq4';
	$fields = array( 'to' => $to, 'notification' => $data);
	$headers = array( 'Authorization : key='.$apiKey, 'Content-Type: application/json');
	$url = 'https://fcm.googleapis.com/fcm/send';
	
	$ch = curl_init();
	 if ($ch === false) {
        throw new Exception('failed to initialize');
    }
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_POST, true);
	curl_setopt($ch, CURLOPT_HTTP_VERSION, CURL_HTTP_VERSION_1_1);
	curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
	curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
	$result = curl_exec($ch);
	if ($result === false) {
        throw new Exception(curl_error($ch), curl_errno($ch));
    }
	curl_close($ch);
	return json_decode($result,true);
}


// $token = 'e79GZb8YlNQ:APA91bH5ebhRjx0srBB5cNt5LTcmDP6CU3v5sumkchKqK3hQ3qNNBR6ZPWjcvONGpdnuTIuWKY4Z5kv6DmbOEmLmF8GPrUG9kYft3kkgwqiMeoRt1kcNvUvpehPBmQ4s0Maylt4lJ7H2';
// $token2 = 'cjt1wJ9hap0:APA91bFyjjf3pOO4qYDv-gXxk4clMXSX9zBy0sPsIIrgxlpW_wGXhQbl1GwCaePm57GXvZjYwlfaljaawRS1BGg7v3i5_d1MwWEdQvnN3sfTyAt_jIiDWJtm-xive1pjnawN0LJpomch';
// $data = array(
// 	'body' => 'Gia soy gkauliari!'
// );

//print_r(sendPushNotification($token,$data));

