
<?php
$p_name = $_REQUEST['name'];

$professor = array 
(
array("Κωτσάκης Σπύρος","https://www.icsd.aegean.gr/icsd/profile.php?member=44"),
array("Λουκής Ευριπίδης","https://www.icsd.aegean.gr/icsd/profile.php?member=46"),
array("Μήτρου Λίλιαν","https://www.icsd.aegean.gr/icsd/profile.php?member=47"),
array("Σκιάνης Χαράλαμπος","https://www.icsd.aegean.gr/icsd/profile.php?member=53"),
array("Βουγιούκας Δημοσθένης","https://www.icsd.aegean.gr/icsd/profile.php?member=62"),
array("Καβαλλιεράτου Εργίνα","https://www.icsd.aegean.gr/icsd/profile.php?member=65"),
array("Καμπουράκης Γεώργιος","https://www.icsd.aegean.gr/icsd/profile.php?member=31"),
array("Κοκολάκης Σπύρος","https://www.icsd.aegean.gr/icsd/profile.php?member=20"),
array("Κορμέντζας Γεώργιος","https://www.icsd.aegean.gr/icsd/profile.php?member=51"),
array("Μαραγκουδάκης Εμμανουήλ","https://www.icsd.aegean.gr/icsd/profile.php?member=60"),
array("Σταματάτος Ευστάθιος","https://www.icsd.aegean.gr/icsd/profile.php?member=57"),
array("Χαραλαμπίδης Ιωάννης","https://www.icsd.aegean.gr/icsd/profile.php?member=40"),
array("Καλλίγερος Εμμανουήλ","https://www.icsd.aegean.gr/icsd/profile.php?member=59"),
array("Καπόρης Αλέξιος","https://www.icsd.aegean.gr/icsd/profile.php?member=66"),
array("Καρύδα Μαρία","https://www.icsd.aegean.gr/icsd/profile.php?member=32"),
array("Κωνσταντίνου Ελισάβετ","https://www.icsd.aegean.gr/icsd/profile.php?member=56"),
array("Λερός Ασημάκης","https://www.icsd.aegean.gr/icsd/profile.php?member=52"),
array("Γκουμόπουλος Χρήστος","https://www.icsd.aegean.gr/icsd/profile.php?member=1133"),
array("Σκούτας Δημήτριος","https://www.icsd.aegean.gr/icsd/profile.php?member=284"),
array("Κοφινάς Γεώργιος","https://www.icsd.aegean.gr/icsd/profile.php?member=1148")
);
		
		
		
	 //Συγκριση ονομα καθηγητη με array για να παρουμε το λινκ
     for ($row = 0; $row < 24; $row++) {
			
        if(strcmp($professor[$row][0],html_entity_decode($p_name)) === 0){
           $link = $professor[$row][1];
		   break;
          }
     }
	 //Περασμα ολου του περιεχομενου σε μεταβλητη
     $data = file_get_contents($link);
	
	
	//Περιεχόμενο απο το κομματι της σελιδας "Συνεδρια" μεχρι τελος
        $study = substr($data,stripos($data,"Εκπαίδευση - Σπουδές"));
		//Περιεχόμενο απο το κομματι της σελιδας "Δημοσιεύσεις" μεχρι τελος
        $banner = substr($data,stripos($data,"Ερευνητικά Ενδιαφέροντα"));
		//Τριμαρισμα ωστε να αντικαταστησει το κομματι banner με κενο και να μηνουν μονο τα papers
        $trimmed = str_replace($banner,'',$study);
        echo $trimmed ;

?>
<html>
    <head>
        <body>
		    <!-- Κουμπι επαναφορας για συνεχεια αναζητησης -->
            <form action="search_member.html">
            <input type="submit" value="Search Again">
            </form>
        </body>
    </head>
</html>