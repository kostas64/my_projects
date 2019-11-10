<?php
include 'token_error.php';
?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset=utf-8>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet"  type='text/css'>
        <link href="assets/css/main.css" rel="stylesheet">
        <!-- Website CSS style -->
        <link rel="stylesheet" type="text/css" href="assets/css/main.css">
        <title>Είσοδος</title>
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
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="register.php"><i class="fa fa-user" aria-hidden="true"></i>
                                Δημιουργία Λογαριασμού</a></li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </nav>
        <!-- end navbar -->
        <!-- LOG IN -->
        <div class="w3-agileits-info">
            <form class='form' id='form1' action="check_creds.php" method="post">
                <p class="w3agileits">Συνδεθείτε εδώ</p>
                <div class='form-group has-feedback w3ls'>
                    <label class='control-label sr-only' for='username'>Ονομα χρήστη</label> 
                    <input class='form-control' id='username' name='username' placeholder='Ονομα Χρήστη' type='text'>
                    <span class='glyphicon glyphicon-ok form-control-feedback'></span>
                </div>
                <div class='form-group has-feedback agile'>
                    <label class='control-label sr-only' for='password'>Κωδικός πρόσβασης</label> 
                    <input class='form-control w3l' id='password' name='password' placeholder='Κωδικός Χρήστη' type='password'><span class='glyphicon glyphicon-ok form-control-feedback'></span>
                    
                </div>
                <div>
                     <input type="hidden" value="<?php if (isset($_GET["token"])) { echo $_GET["token"];} ?>" name="token" id="token">
                </div>
                
                <div class='submit w3-agile'>
                    <input class='btn btn-lg' type='submit' value='ΣΥΝΔΕΣΗ'>
                </div>  
                <?php if (isset($_GET['error'])) { ?>
                    <div class="form-group has-feedback agile" style=";width:100%;text-align:center">
                        <?php
                        switch ($_GET['error']) {
                            case "email_exist": echo "<span style='color:red'>Υπαρχει λογαριασμός με αυτό το email</span> ";
                                break;
                            case "failed_validation": echo "<span style='color:red'>Απαιτείται ενεργοποίηση λογαριασμού μέσω email</span> ";
                                break;
                            case "invalid_creds" : echo "<span style='color:red'>Λανθασμένα στοιχεία</span> ";
                                break;
                            case "pass_change_fail": echo "<span style='color:red'>Ασυμφωνία Κωδικών <br> Ο κωδικός παρέμεινε ως έχει</span> ";
                                break;
                            default:
                                break;
                        }
                        ?>
                    </div>
                    <?php }  ?>
                    <?php if (isset($_GET['success'])) { ?>
                    <div class="form-group has-feedback agile" style=";width:100%;text-align:center">
                        <?php
                        switch ($_GET['success']) {
                            case "email_change": echo "<span style='color:red'>Η καταχώρηση του email έγινε επιτυχώς</span> ";
                                break;
                            case "mobile_change": echo "<span style='color:red'>Η καταχώρηση του κινητού έγινε επιτυχώς</span> ";
                                break;
                            case "pass_change": echo "<span style='color:red'>Ο κωδικός άλλαξε επιτυχώς</span> ";
                                break;    
                            default:
                                break;
                        }
                        ?>
                    </div>
                    <?php }  ?>
                    
                <div class="form-group text-frm">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="text-center">
                                <a href="email_request.html" tabindex="5" class="forgot-password">Ξεχάσατε τον κωδικο σας;</a>
                            </div>
                            <div class="text-center">
                                <p>Δεν έχετε λογαριασμό; <a href="register.php" tabindex="5" class="forgot-password">Δημιουργήστε  εδώ</a></p>
                            </div>
                        </div>
                    </div>
                </div>
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