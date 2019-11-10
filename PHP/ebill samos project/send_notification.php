<?php
require 'send_mail.php';
require 'init.php';
require "google-api-2.2.2/vendor/autoload.php";
putenv('GOOGLE_APPLICATION_CREDENTIALS=service.json');
$client = getClient();
$service = new Google_Service_Drive($client);

if(isset($_POST['commun'])){
    $community = $_POST['commun'];

    switch($community){
    case "ΒΑΘΕΟΣ" : $folderId = "1MEY6D857aQ_qxGq1KDuoStbUF2wKGKct";
    break;
    case "Καρλοβασίων" : $folderId = "1590izYAtt3QjYzu1mpTnkFmhUDormmYd";
    break;
    case "ΚΟΚΚΑΡΙΟΥ" : $folderId = "1XsF6-xWPqOd26FVzI6bZyzQMUw6JMlW-";
    break;
    case "ΣΑΜΙΩΝ" : $folderId = "1MhXvNgsV56FOG08RzTestLIRZdLKaDdK";
    break;
    case "Πυθαγορείου" : $folderId = "1MVTSHc5MV5d7K5aMnqL2E-GxyWtCqY4T";
    break;
    case "Μυτιληνιών" : $folderId = "1_HDJM4FNiWjH30BHYOb15QAr_WFeCaA7";
    break;
    case "Παγώνδα" : $folderId = "1zexX0CTq84a_MOo3pvMh4TSQazDsasTl";
    break;
    case "Χώρας" : $folderId = "1IYA7vTJ5bFPulHJne92CS-SH2mOOaH7l";
    break;
    case "Μαραθοκάμπου" : $folderId = "15_2au-zxq9hLiVa0iy7QZ-vlxDrEf0H5";
    break;
    case "Α_ΒΑΘΥ_ΝΕΚΡΟΤΑΦΕΙΟ" : $folderId = "1UzCtqy-gVDSO7ENX2PJRboXCAT8cIQCB";
    break;
    case "ΑΓ_ΚΩΝ_ΝΟΣ" : $folderId = "1ScElZUGl5ccfCwEppM_Vol8YbvtNw40J";
    break;
    case "ΑΜΠΕΛΟΥ" : $folderId = "1nhmPEFdAmV38zBnlrg-PJKQNXFF7CwNU";
    break;
    case "ΑΡΔΕΥΣΗ_ΜΕΣΟΚΑΜΠΟΥ" : $folderId = "1dniQtq4yybBfZ-r94Ly1S6I7vnyZeco8";
    break;
    case "ΒΑΘΕΟΣ_ΕΞΟΧΩΝ" : $folderId = "1MBWn7qK-IOjlspxEiR6tThR_gUXOFATE";
    break;
    case "ΒΑΘΕΟΣ_ΝΕΑΠΟΛΗ" : $folderId = "1ZKLNYdBkhHgniYE7UbrBt2s-hvScDXpJ";
    break;
    case "ΒΟΥΡΛΙΩΤΩΝ" : $folderId = "1eoEZZuL1wU5VOJiAH7HXRA8Z7wbcz-hJ";
    break;
    case "ΜΑΝΩΛΑΤΩΝ" : $folderId = "11kj3oDi5v9TJrg8bv2fFz03f6wr1V6FU";
    break;
    case "ΠΑΛΑΙΟΚΑΣΤΡΟΥ" : $folderId = "1n9H84jmZwnlCuecRLfjC8j66mgYhfQ13";
    break;
    case "ΣΤΑΥΡΙΝΗΔΩΝ" : $folderId = "14_dc6K5XJZQHpowcj8-UhxjaBNZ6dwbG";
    break;
    case "ΣΤΡΑΤΙΩΤΙΚΕΣ_ΜΟΝΑΔΕΣ" : $folderId = "1CqsBvpdVCp2Y03gX38z4efiLbW8goLKn";
    break;
    }

$sql = "select real_estate.hydro,users_info.email,real_estate_info.community from users,real_estate_info,real_estate,users_info where users.qprime = real_estate.qprime and real_estate.hydro = real_estate_info.hydro and users_info.qprime = users.qprime"; 

$result = mysqli_query($con, $sql);


if($result){
   
while($row = mysqli_fetch_assoc($result)){
    
    if( strlen($row['community']) == (strlen($community)+1)) {
        
        $hydro = $row['hydro'];
   
    $optParams = array(
    'pageSize' => 1,
     'fields' => 'nextPageToken,files(webViewLink,name)',
     'q' => "name contains '" . $hydro . "' and '" . $folderId . "' in parents",
    'orderBy' => 'createdTime desc'
    );
  
            try {
                $results = $service->files->listFiles($optParams);
                
                  if (count($results->getFiles()) > 0) { 
                   
                    foreach ($results->getFiles() as $file){
                    
                    $name = $file->getName();
                    
                    list($first, $secon, $third, $commun, $etos, $minas, $qprime, $hydro,$ar_hydro, $ypoxreos, $idioktitis, $afm, $bool, $poso, $code1,$exp_date) = explode("-", $name);
                    $poso = str_replace('_', ',', $poso);
                    $exp_date = str_replace('_','/',$exp_date);
                    if(send_new_log($row['email'],$poso,$hydro,$etos,$minas,$exp_date)){
                        echo "Mail sent for : ". $row['email']. "<br>";
                    }else {
                        echo 'Mail not sent for : '. $row['email'] . "<br>";
                    }
                    } 
                }
            } catch (Exception $e) {
            $error = $e->getMessage();
            echo $error;
            }
        } 
     }
    }
}

function getClient() {
$client = new Google_Client();
$client->useApplicationDefaultCredentials();
$client->addScope(Google_Service_Drive::DRIVE_METADATA_READONLY);
$client->setSubject('info@obiorange.gr');
return $client;
}