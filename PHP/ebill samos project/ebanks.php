<?php
session_start();
if (!isset($_SESSION['username'])) {
    header("Location: login.php");
}
if (isset($_GET["success"])){
    $success = $_GET["success"];
}
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset=utf-8>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,900&amp;lang=en"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet"  type='text/css'>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="assets/css/main.css" rel="stylesheet">
        <!-- Website CSS style -->
        <link rel="stylesheet" type="text/css" href="assets/css/main.css">
        <title>Main</title>
        
    </head>
    <body>
        <!-- header -->
        <header>
            <div class="container">
                <img src="assets/images/logo-isld.png">
                <img src="assets/images/slogan.png">
            </div>
        </header>
        <!-- end header -->
        <!-- menu navbar -->
        <!-- Fixed navbar -->
        <nav class="navbar navbar-default navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand title-nav" href="#">e-ΛΟΓΑΡΙΑΣΜΟΙ ΝΕΡΟΥ</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="myaccount.php"><i class="fa fa-file-text" aria-hidden="true"></i>
                                Οι λογαριασμοί μου</a></li>
                                <li><a href="edit_account.php"><i class="fa fa-tasks" aria-hidden="true"></i>
                                Διαχείριση Χρήστη</a></li>     
                                <li><a href="#" target="blank"><i class="fa fa-external-link" aria-hidden="true"></i>
                                Ηλ. Πληρωμή</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="logout.php"><i class="fa fa-user" aria-hidden="true"></i>
                                Αποσύνδεση</a></li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </nav>
        <!-- end navbar -->
        <div class="title-upper">
            <h1> Καλώς ήλθατε: <span class="user-entry"> <?php echo $_SESSION['username']; ?> </span></h1>
        </div>
        <!-- LOG IN -->
      <div class="w3-agileits-info-main" style="width:80%;">
      
      <div class="row">
<div class="col-sm-12">
<h5 style="text-decoration: underline;">Πληρωμές μπορούν να πραγματοποιηθούν στις παρακάτω τράπεζες με τους εξής διαθέσιμους τρόπους:</h5>
</div>
</div>
<div class="su-table">
<table>
<table>
<tbody>
<tr class="su-even"><th><b>Τράπεζα</b></th><th><b>Κατάστημα</b></th><th><b>Internet/Banking</b></th><th><b>Phone/Banking</b></th></tr>
<tr>
<td>Εθνική Τράπεζα</td>
<td>✔</td>
<td>✔</td>
<td>✔</td>
</tr>
<tr class="su-even">
<td>Alphabank</td>
<td>&nbsp;</td>
<td>✔</td>
<td>✔</td>
</tr>
<tr>
<td>Πειραιώς</td>
<td>✔</td>
<td>✔</td>
<td>✔</td>
</tr>
<tr class="su-even">
<td>Eurobank</td>
<td>✔</td>
<td>✔</td>
<td>✔</td>
</tr>
</tbody>
</table>
</div>
<div class="row" style="background: #fff; border: 1px solid #ccc; border-radius: 5px; margin-top: 20px; padding-top: 15px;">
<div class="col-sm-12">
<h5 style="text-decoration: underline;">Κλικάρετε στα λογότυπα των τραπεζών για να ανοίξετε τις σχετικές Ιστοσελίδες:</h5>
</div>
<div class="col-xs-12 col-sm-3"><a href="https://www.nbg.gr/" target="blank"><img class="logo-bank" src="../assets/images/nbg-logo.jpg" alt="" /></a></div>
<div class="col-xs-12 col-sm-3"><a href="https://www.alpha.gr/page/" target="blank"><img class="logo-bank" src="../assets/images/alpha-logo.png" alt="" /></a></div>
<div class="col-xs-12 col-sm-3"><a href="http://www.piraeusbank.gr/el/Idiwtes" target="blank"><img class="logo-bank" src="../assets/images/piraeus-logo.jpg" alt="" /></a></div>
<div class="col-xs-12 col-sm-3"><a href="https://www.eurobank.gr/online/home/" target="blank"><img class="logo-bank" src="../assets/images/eurobank-logo.jpg" alt="" /></a></div>
</div>
<p>&nbsp;</p>
<div class="row">
<div class="col-sm-12">
<h5 style="text-decoration: underline;">Ή χρησιμοποιήστε την πλατφόρμα EASYPAY της τράπεζας πειραιώς:</h5>
</div>
<div class="col-xs-12 col-sm-12"><a href="https://www.easypay.gr/Payments/Payments.asp?SID=P840A0DC4D7C04A0188F11F39E19F0E40BC425EF04A9E4BD5B3FD6B3D281F984F507DC237F18F487&amp;lang=1&amp;pcatid=36" target="blank"><img src="../assets/images/easypay-logo.png" alt="" /></a></div>
</div>
      
      </div>
        <!-- END LOGIN -->
        <footer>
            <div class="container">
                Powered by <a href="http://www.obiorange.gr">obiorange</a><img src="assets/images/obi-logo.png" width="80"><img src="assets/images/logo-dimos.png" width="70"> Τμήμα Πληροφορικής
            </div>
        </footer>
        <!-- js -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src='assets/js/bootstrap.min.js'></script>      
    </body>
</html>