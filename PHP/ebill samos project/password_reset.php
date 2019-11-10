<?php
if (!isset($_GET["error"])) {
    if (isset($_GET["token"])) {
        $token = $_GET["token"];
    } else {
        header("Location: login.php");
    }
}
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset=utf-8>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="assets/css/main.css" rel="stylesheet">
        <!-- Website CSS style -->
        <link rel="stylesheet" type="text/css" href="assets/css/main.css">
        <title>Login</title>
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
                    <a class="navbar-brand title-nav" href="login.php">ΛΟΓΑΡΙΑΣΜΟΙ ΝΕΡΟΥ</a>
                </div>
            </div>
        </nav>
        <!-- end navbar -->
        <!-- LOG IN -->
        <div class="w3-agileits-info">
            <form class='form' id='form1' action="change_pass.php" method="post">
                <p class="w3agileits">Επαναφορά Κωδικού</p>
                <div class='form-group has-feedback w3ls'>
                    <label class='control-label sr-only' for='username'>Νέος Κωδικός </label> 
                    <input class='form-control' id='username' name='password' placeholder='Νέο Συνθηματικό' type='password'>
                    <span class='glyphicon glyphicon-ok form-control-feedback'></span>
                </div>
                <div class='form-group has-feedback agile'>
                    <label class='control-label sr-only' for='password'></label> 
                    <input class='form-control w3l' id='password' name='password2' placeholder='Επαναπληκτρολόγηση Νέου Συνθηματικού' type='password'><span class='glyphicon glyphicon-ok form-control-feedback'></span>
                </div>
                <input type="hidden" name="token" value="<?php echo $token ?>">

                <?php if (isset($_GET['error'])) { ?>
                    <div class="form-group has-feedback agile" style=";width:100%;text-align:center">
                        <?php
                        switch ($_GET['error']) {
                            case "token_invalid": echo "<span style='color:red'>Μη έγκυρο token</span> ";
                                break;
                            case "pass_missmatch": echo "<span style='color:red'>Οι κωδικοί δεν ταιριάζουν</span> ";

                                break;

                            default:
                                break;
                        }
                        ?>
                </div>
                <?php } ?> 
                <div class='submit w3-agile'>
                    <input class='btn btn-lg' type='submit' value='ΑΛΛΑΓΗ'>
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