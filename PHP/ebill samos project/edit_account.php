<?php 
session_start();
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
                <a class="navbar-brand title-nav" href="myaccount.php">e-ΛΟΓΑΡΙΑΣΜΟΙ ΝΕΡΟΥ</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
               <ul class="nav navbar-nav">
                   <li><a href="myaccount.php"><i class="fa fa-file-text" aria-hidden="true"></i>
 Οι λογαριασμοί μου</a></li>
 <li><a href="edit_account.php"><i class="fa fa-tasks" aria-hidden="true"></i>
 Διαχείριση Χρήστη</a></li>  
 <li><a href="ebanks.php"><i class="fa fa-external-link" aria-hidden="true"></i>
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
         <h1>Καλώς ήλθατε: <span class="user-entry"><?php echo $_SESSION['username']; ?></span></h1>
      </div>
      <!-- LOG IN -->

      <div class="w3-agileits-info-main">
         <p class="strong">Διαχείριση Στοιχείων Χρήστη:</p>
         <p><b>Συμπληρώστε τα παρακάτω πεδία για να αλλάξετε τα στοιχεία χρήστη που έχετε δηλώσει.</b></p>
        <form class="noshadow" method="post" action="manage_profile.php">
  
  <div class="form-group">
    <label for="exampleInputName">Τηλέφωνο: (Υφιστάμενο <?php echo $_SESSION['mobile']?>)</label>
    <input type="text" name="phone" class="form-control" id="exampleInputName" aria-describedby="name" placeholder="Συμπληρώστε το νεό αριθμό Κινητού">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">Email: (Υφιστάμενο <?php echo $_SESSION['email']?>)</label>
    <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Συμπληρώστε το νεό Email">
  </div>
  <div class="form-group">
    <label for="exampleInputName">Κωδικός Χρήστη:</label>
    <input type="password" name="password" class="form-control" id="exampleInputName" aria-describedby="name" placeholder="Συμπληρωστε το νέο κωδικό">
  </div>
  <div class="form-group">
    <label for="exampleInputName">Επανάληψη Κωδικού Χρήστη:</label>
    <input type="password" name="retype" class="form-control" id="exampleInputName" aria-describedby="name" placeholder="Συμπληρωστε το νέο κωδικό">
  </div>
   <button type="submit" class="btn btn-primary">Ενημέρωση</button>

</form>
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
      <script src='assets/js/jquery.validate.min.js'></script>
      <script src='assets/js/formAnimation.js'></script>
      <script src='assets/js/shake.js'></script>
      <!-- //js -->	
   </body>
</html>