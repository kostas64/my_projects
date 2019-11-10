<?php

session_start();

require "google-api-2.2.2/vendor/autoload.php";
putenv('GOOGLE_APPLICATION_CREDENTIALS=service.json');

$client = getClient();
$service = new Google_Service_Drive($client);

function getClient() {
    $client = new Google_Client();
    $client->useApplicationDefaultCredentials();
    $client->addScope(Google_Service_Drive::DRIVE_METADATA_READONLY);
    $client->setSubject('info@obiorange.gr');
    return $client;
}


if (isset($_GET['hydro'])) {
    $hydro = $_GET['hydro'];
    $hydro = str_replace("-", "_",$hydro);
    
    $optParams = array(
        'pageSize' => 5,
        'fields' => 'nextPageToken,files(webViewLink,name)',
         'q' => "name contains '" . $hydro . "'",
    );


    try {
        $results = $service->files->listFiles($optParams);

        if (count($results->getFiles()) == 0) {
            echo "[]";
        } else {
            $links = [];
            foreach ($results->getFiles() as $i => $file) {
                $name = $file->getName();
                if (strpos($name, str_replace("-", "_", $_GET['hydro'])) != false) {
                    list($first, $secon, $third, $commun, $etos, $minas, $qprime, $hydro,$ar_hydro, $ypoxreos, $idioktitis, $afm, $bool, $poso, $code1,$exp_date) = explode("-", $name);
                    $links[$i]["name"] = $code1;//substr($name,-10);
                    $links[$i]["link"] = $file->getWebViewLink();
                }
            }
            echo json_encode($links);
        }
    } catch (apiServiceException $e) {
        $error = $e->getMessage();
    }
}
?>