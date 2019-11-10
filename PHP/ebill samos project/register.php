<?php
if (isset($_GET["error"]))
    $error = $_GET["error"];
?>
<!DOCTYPE html>

<html lang="en">
   <head>
      <meta charset=utf-8>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet"  type='text/css'>
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
      <link href="assets/css/main.css" rel="stylesheet">
      <!-- Website CSS style -->
      <link rel="stylesheet" type="text/css" href="assets/css/main.css">
      <title>Sign Up</title>
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
               <a class="navbar-brand title-nav" href="login.php">e-ΛΟΓΑΡΙΑΣΜΟΙ ΝΕΡΟΥ</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="login.php"><i class="fa fa-user" aria-hidden="true"></i>
                                Είσοδος</a></li>
                    </ul>
            </div>
         </div>
      </nav>
      <!-- end navbar -->
      <div class="title-upper">
         <h1>Φόρμα Εγγραφής</h1>
      </div>
      <!-- LOG IN -->
      <div class="w3-agileits-info">
         <form class="form" id="form1" method="post" action="add_user.php">
			<div class="form-group">
			    <div class="form-group">
			    <label for="error" class="cols-sm-2 control-label"><?php 
			    if (isset($_GET["error"])) {
			    switch ($_GET['error']){
			    case "email_exist": echo "<span style='color:red'>Υπαρχει λογαριασμός με αυτό το email</span>";
			    break;
			    case "no_qprime" : echo "<span style='color:red'>Ελέγξτε το qprime και ξαναπροσπαθήστε</span>";
			    break;
			    case "user_exist" : echo "<span style='color:red'>Υπάρχει ήδη χρήστης με αυτό το qprime</span>";
			    break;}} ?></label></div>
               <label for="qprime" class="cols-sm-2 control-label">QPrime</label>
               <div class="input-group-inner">
                  <input type="text" class="form-control" name="qprime" id="qprime"  placeholder="Εισάγετε το QPrime"/>
               </div>
               <div class="help">
                 <img class="TooltipImg" src="../assets/images/help.png"/>
                 <div class="tooltip-d" style="z-index:1001;width:335px;">
                  <p>
                    Ο αριθμητικός κωδικός που βρίσκεται στο επάνω δεξί τμήμα του λογαριασμού σας:
                    <img src="assets/images/thumb_qprime.jpg" width="320"/>                 
                  </p>
                 </div>
               </div>
               
            </div>
            <div class="form-group">
               <label for="name" class="cols-sm-2 control-label">Όνομα</label>
               <div class="input-group-inner">
                  <input type="text" class="form-control" name="name" id="name"  placeholder="Εισάγετε το όνομά σας"/>
               </div>
               <div class="help">
                 <img class="TooltipImg" src="../assets/images/help.png"/>
                 <div class="tooltip-d">
                  <p>
                   Συμπληρώστε το Όνομά σας
                  </p>
                 </div>
               </div>
               
            </div>
            <div class="form-group">
               <label for="surname" class="cols-sm-2 control-label">Επώνυμο</label>
               <div class="input-group-inner">
                  <input type="text" class="form-control" name="surname" id="surname"  placeholder="Εισάγετε το επώνυμό σας"/>
               </div>
               <div class="help">
                 <img class="TooltipImg" src="../assets/images/help.png"/>
                 <div class="tooltip-d">
                  <p>
                    Συμπληρώστε το Επώνυμό σας
                  </p>
                 </div>
               </div>
               
            </div>
            <div class="form-group">
               <label for="email" class="cols-sm-2 control-label">Email</label>
               <div class="cols-sm-10">
                  <div class="input-group-inner">
                     <!--<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>-->
                     <input type="email" class="form-control" name="email" id="email" placeholder="Εισάγετε το Email σας"/>
                  </div>
                  <div class="help">
                    <img class="TooltipImg" src="../assets/images/help.png"/>
                    <div class="tooltip-d">
                      <p>
                       Μια έγκυρη διεύθυνση e-mail που θα χρησιμοποιούμε για να σας στέλνουμε ειδοποιήσεις
                      </p>
                    </div>
                  </div>
                  
               </div>
            </div>
            <div class="form-group">
               <label for="name" class="cols-sm-2 control-label">Αρ. Κινητού</label>
               <div class="input-group-inner">
                  <input type="number" class="form-control" name="mobile" id="mobile"  placeholder="Εισάγετε τον αριθμό του Κινητού σας"/>
               </div>
               <div class="help">
                 <img class="TooltipImg" src="../assets/images/help.png"/>
                 <div class="tooltip-d">
                  <p>
                    Έναν έγκυρο αριθμό κινητού τηλεφώνου που θα χρησιμοποιούμε για να σας στέλνουμε ειδοποιήσεις.
                  </p>
                 </div>
               </div>
               
            </div>
            <div class="form-group">
               <label for="username" class="cols-sm-2 control-label">Όνομα Χρήστη</label>
               <div class="cols-sm-10">
                  <div class="input-group-inner">
                     <!--<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>-->
                     <input type="text" class="form-control" name="username" id="username"  placeholder="Εισάγετε το επιθυμητό Όνομα Χρήστη"/>
                  </div>
                  <div class="help">
                    <img class="TooltipImg" src="../assets/images/help.png"/>
                    <div class="tooltip-d">
                     <p>
                     To Όνομα Χρήστη (Username) που θα χρησιμοποιείτε για να συνδέεστε και να βλέπετε τους λογαριασμούς σας 
                    </p>
                    </div>
                  </div>
                  
               </div>
            </div>
            <div class="form-group">
               <label for="password" class="cols-sm-2 control-label">Κωδικός Χρήστη</label>
               <div class="cols-sm-10">
                  <div class="input-group-inner">
                     <!--<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>-->
                     <input type="password" class="form-control" name="password" id="password"  placeholder="Εισάγετε τον επιθυμητό Κωδικό"/>
                  </div>
                  <div class="help">
                     <img class="TooltipImg" src="../assets/images/help.png"/>
                     <div class="tooltip-d">
                     <p>
                       Τον κωδικό που θα χρησιμοποιείτε για να συνδέεστε και να βλέπετε τους λογαριασμούς σας
                     </p>
                     </div>
                  </div>
               </div>
            </div>
            <div class="form-group">
               <label for="confirm" class="cols-sm-2 control-label">Επιβεβαίωση Κωδικού Χρήστη</label>
               <div class="cols-sm-10">
                  <div class="input-group-inner">
                     <!--<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>-->
                     <input type="password" class="form-control" name="confirm" id="confirm"  placeholder="Επανεισάγετε τον Κωδικό"/>
                  </div>
                  <div class="help">
                     <img class="TooltipImg" src="../assets/images/help.png"/>
                     <div class="tooltip-d">
                     <p>
                       Επιβεβαιώστε τον κώδικο
                     </p>
                     </div>
                  </div>
               </div>
            </div>
            <div class="form-group submit ">
               <input class='btn btn-lg' type='submit' value='ΕΓΓΡΑΦΗ'/>
            </div>
            <div class="login-register">
               <a href="login.php">Είσοδος</a>
            </div>
         </form>
      </div>
      <!-- END LOGIN -->
      <footer>
         <div class="container">
            Powered by <a href="http://www.obiorange.gr">obiorange</a><img src="assets/images/obi-logo.png" width="80">
         </div>
      </footer>
      <!-- js -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
      <script src='assets/js/bootstrap.min.js'></script>
      <script src='assets/js/jquery.validate.min.js'></script>
      <script src='assets/js/formAnimation.js'></script>
      <script src='assets/js/shake.js'></script>

      <!-- //js -->	
   </body>
</html>