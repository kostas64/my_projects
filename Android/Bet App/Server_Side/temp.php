<?php
include 'push.php';
$url = "https://www.totalcorner.com/match/today";
$token = 'e1wkLfYYVRY:APA91bG82hn7V5z6UHdZP_Tl_fD1baPaWRcaNsIHAuNggAG0EEfhWPk8YCM-aEFQCB5d8-zn4XfdhvxRlrzIb87JPMs3L8lLCj9xHLOA7ZBgNkdYv2j9bwTjzPBFG5NRZFd2Hif01Pjg';
$token2 = 'cjt1wJ9hap0:APA91bFyjjf3pOO4qYDv-gXxk4clMXSX9zBy0sPsIIrgxlpW_wGXhQbl1GwCaePm57GXvZjYwlfaljaawRS1BGg7v3i5_d1MwWEdQvnN3sfTyAt_jIiDWJtm-xive1pjnawN0LJpomch';

//Initialize cURL.
$ch = curl_init();
 
//Set the URL that you want to GET by using the CURLOPT_URL option.
curl_setopt($ch, CURLOPT_URL, $url);
 
//Set CURLOPT_RETURNTRANSFER so that the content is returned as a variable.
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
 
//Set CURLOPT_FOLLOWLOCATION to true to follow redirects.
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
 
//Execute the request.
$data = curl_exec($ch);
 
//Close the cURL handle.
curl_close($ch);

//$data = substr($data,strpos($data,"tbody_match"));
$matches = substr_count($data,"<tr class=\"   \" data-league_id=");

$html = $data;
$needle = "<tr class=\"   \" data-league_id=";
$lastPos = 0;
$positions = array();

while (($lastPos = strpos($html, $needle, $lastPos))!== false) {
    $positions[] = $lastPos;
    $lastPos = $lastPos + strlen($needle);
}

$leagues = array(
    "AFC Champions League",
    "AFC Cup",
    "Argentina Primera Division",
    "Australia A-League",
    "Austria Bundesliga",
    "Bangladesh Federation Cup",
    "Belgium First Division A",
    "Bolivia Apertura",
    "Brazil Serie A",
    "Brazil Serie B",
    "Czech First League",
    "Chile Apertura",
    "China Super League",
    "Colombia Primera A",
    "Colombia Primera B",
    "CONCACAF Champions League",
    "Copa America",
    "Copa Libertadores",
    "Copa Sudamericana",
    "Cyprus Division I",
    "Denmark Superligaen",
    "England Championship",
    "England League I",
    "England Premier League",
    "Finland Veikkausliiga",
    "France Ligue I",
    "Germany Bundesliga I",
    "Germany Bundesliga II",
    "Germany 3.Liga",
    "Greece Super League",
    "Holland Eredivisie",
    "Iceland Premier League",
    "India Super League",
    "Israel Premier League",
    "Italy Serie A",
    "Italy Serie Β",
    "Japan J-League",
    "Mexico Apertura",
    "Norway Tippeligaen",
    "Paraguay Division Profesional",
    "Peru Primera Division",
    "Poland Ekstraklasa",
    "Portugal Primeira Liga",
    "Portugal Segunda Liga",
    "Romania Liga I",
    "Russia Premier League",
    "South Korea K-League Classic",
    "Spain Primera Liga",
    "Spain Segunda",
    "Sweden Allsvenskan",
    "Sweden Superettan",
    "Switzerland Super League",
    "Turkey Super Lig",
    "UEFA Champions League",
    "UEFA Europa League",
    "USA MLS",
    "Wales",
);

for( $i=0; $i<$matches-1; $i++){

    $match = substr($html,$positions[$i],$positions[$i+1]-$positions[$i]);

    $league = substr($match,strpos($match,"td_league\">"));
    $league = substr($league,strpos($league,"<a target=\"_blank\" href=\"/league/view"));
    $league = substr($league,strpos($league,"\">")+2);
    $league = substr($league,0,strpos($league,"</a>"));

    for( $j=0; $j<count($leagues); $j++){

        if ($leagues[$j] == $league){
            
            
            
            $minute = (int)substr($match,strpos($match,"minutes\">")+9,-((strpos($match,"minutes\">")+9) - strpos($match,"</span>")));
            echo $minute;
            var_dump($league);
            var_dump("<br>");
            $home_team = substr($match,strpos($match,"leaguePos"));
            $home_team = substr($home_team,strpos($home_team,"<span class=\"\">")+15);
            $home_team = substr($home_team,0,strpos($home_team,"</span>"));
            
            $away_team = substr($match,strpos($match,"match_away"));
            $away_team = substr($away_team,strpos($away_team,"<span class=\"\">")+15);
            $away_team = substr($away_team,0,strpos($away_team,"</span>"));
            
            $score = substr($match,strpos($match,"match_goal")+12);
            $score = substr($score,0,strpos($score,"</td>"));
            
            $home_goal = substr($score,0,1);
            $away_goal = substr($score,4);
            
            $corner = substr($match,strpos($match,"span_match_corner")+19);
            $corner = substr($corner,0,strpos($corner,"</span>"));
            
            $home_corner = substr($corner,0,1);
            $away_corner = substr($corner,4);
            
            $data = array(
                'body' => $minute."' ".$home_team."-".$away_team. " Goal:". $home_goal. "-". $away_goal. " Corner:". $home_corner. "-". $away_corner
            );
    
            
            if($minute >= 78 && $minute <= 85){
                    
                    if( ($home_goal == $away_goal - 1) || ($away_goal == $home_goal - 1)){
						sendPushNotification($token,$data);
                        sendPushNotification($token2,$data);
                    }
                        
                            
                        
                    }   
            }      
        }

    }

        

  


    //var_dump($league);
    //var_dump($away_corner);
    //var_dump($home_corner);
    //var_dump($corner);
    //var_dump($away_goal);
    //var_dump($home_goal);
    //var_dump($score);
    //var_dump($away_team);
    //var_dump($home_team);
    //var_dump($minute);
    //var_dump($match);
    
}




//var_dump($data);


?>