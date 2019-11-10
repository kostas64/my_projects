<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require 'PHPMailer/src/Exception.php';
require 'PHPMailer/src/PHPMailer.php';
require 'PHPMailer/src/SMTP.php';

function initMail(){

    $mail = new PHPMailer(true);                              // Passing `true` enables exceptions
    //Server settings
    $mail->SMTPDebug = 0;                                 // Enable verbose debug output
    $mail->isSMTP();                                      // Set mailer to use SMTP
    $mail->Host = 'mail.ebill-samos-nera.com';  // Specify main and backup SMTP servers
    $mail->SMTPAuth = true;                               // Enable SMTP authentication
    $mail->Username = 'noreply@ebill-samos-nera.com';                 // SMTP username
    $mail->Password = '+=X+OG.uyE!d';                           // SMTP password
    $mail->SMTPSecure = 'ssl';                            // Enable TLS encryption, `ssl` also accepted
    $mail->Port = 465;                                    // TCP port to connect to
    $mail->CharSet = 'UTF-8';
    
    return $mail;
}

function send_pass_reset($recipient,$token){
    $mail = initMail();
    $mail->setFrom('noreply@ebill-samos-nera.com', 'e-Πλατφόρμα Υδρευσης Σάμου'); 
    $mail->addAddress($recipient);
    $mail->isHTML(true);
    $mail->Subject = 'Επαναφορά Κωδικού Πρόσβασης';
    $mail->Body = "Πατήστε <a href='https://www.ebill-samos-nera.com/password_reset.php?token=$token'>εδώ</a> για να ανακατευθυνθείτε στη σελίδα επαναφοράς του συνθηματικού σας.";
    $mail->AltBody = "Ακολουθείστε τον παρακάτω σύνδεσμο για να επαναφέρετε το συνθηματικό σας. https://www.ebill-samos-nera.com/password_reset.php?token=$token";
    return $mail->send();
    
}

function send_acc_verification($recipient,$token){
    $mail = initMail();
    $mail->setFrom('noreply@ebill-samos-nera.com', 'e-Πλατφόρμα Υδρευσης Σάμου'); 
    $mail->addAddress($recipient);
    $mail->isHTML(true);
    $mail->Subject = 'Επιβεβαίωση Λογαριασμού';
    $mail->Body = "Πατήστε <a href='https://www.ebill-samos-nera.com/login.php?token=$token'>εδώ</a> για ενεργοποίηση του λογαριασμού σας.";
    $mail->AltBody = "Πατήστε τον σύνδεσμο που ακολουθεί για να ενεργοποιήσετε το λογαριασμό σας. https://www.ebill-samos-nera.com/login.php?token=$token";
    return $mail->send();
}

function send_new_log($recipient,$value,$hydro,$etos,$minas,$exp_date){
    $mail = initMail();
    $mail->setFrom('noreply@ebill-samos-nera.com', 'e-Πλατφόρμα Υδρευσης Σάμου');
    $mail->addAddress($recipient);
    $mail->isHTML(true);
    $mail->Subject = 'Έκδοση νέου λογαριασμού';
    $mail->Body = "Εκδόθηκε νέος λογαριασμός με ποσό $value € για το ακίνητο με Αρ. Υδρομέτρησης : '$hydro'<br>Ημερομηνία έκδοσης λογαριασμού $minas/$etos<br>Ημερομηνία λήξης λογαριασμού $exp_date";
    $mail->AltBody = "Εκδόθηκε νέος λογαριασμός με ποσό $value € για το ακίνητο με Αρ. Υδρομέτρησης : '$hydro'<br>Ημερομηνία έκδοσης λογαριασμού $minas/$etos<br>Ημερομηνία λήξης λογαριασμού $exp_date";
    return $mail->send();
}
