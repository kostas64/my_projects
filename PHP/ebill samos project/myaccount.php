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
                        <li><a href="#"><i class="fa fa-file-text" aria-hidden="true"></i>
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
            <h1> Καλώς ήλθατε: <span class="user-entry"> <?php echo $_SESSION['username']; ?> </span></h1>
        </div>
        <div class="w3-agileits-info-main">

            <div style="padding-bottom:10px">
                <label for="UserCustomerCodesList" id="UserCustomerCodesList" class="bold"><b>Επιλέξτε Ακίνητο:</b></label>
            </div>
            <select name="estates" id="lstUserCustomerCodes" onchange="onSelectionChanged(this.value)"  style="width:100%; height:35px; padding:0 10px; border:1px solid #afafaf;">            
                <?php
                require 'init.php';

                $username = $_SESSION['username'];
                $sql = "SELECT qprime from users WHERE username = '$username';";
                $result = mysqli_query($con, $sql);

                if ($result) {
                    $counter = 0;
                    $selected = "selected";
                    $row = $result->fetch_assoc();
                    $qprime = $row['qprime'];
                    $sql = "SELECT hydro FROM real_estate WHERE qprime = '$qprime';";
                    $result = mysqli_query($con, $sql);
                    if ($result) {
                        while ($row = mysqli_fetch_array($result)) {
                            if ($counter == 0) {
                                echo "<option selected='$selected'>" . $row['hydro'] . "</option>";
                                $counter = 1;
                            } else
                                echo "<option>" . $row['hydro'] . "</option>";
                        }
                    }
                }
                ?>
            </select>
        </div>
        <div class="w3-agileits-info-main">
            <p class="strong">Στοιχεία Λογαριασμού:</p>
            <table class="table table-striped">
                <tbody>
                    <tr>
                        <th scope="row">Όνομα</th>
                        <td><?php echo $_SESSION['name']; ?></td>
                    </tr>
                    <tr>
                        <th scope="row">Επώνυμο</th>
                        <td><?php echo $_SESSION['surname']; ?></td>
                    </tr>
                    <tr>
                        <th scope="row">Email</th>
                        <td><?php echo $_SESSION['email']; ?></td>
                    </tr>  
                    <tr>
                        <th scope="row">Κινητό Τηλέφωνο</th>
                        <td><?php echo $_SESSION['mobile']; ?></td>
                    </tr> 
                </tbody>
            </table>
        </div>
        <div class="w3-agileits-info-main">
            <p class="strong">Λογαριασμοί</p>
            <table class="table table-striped" id="acountsTable">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Ονομα Αρχειου</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div class="w3-agileits-info-main">

            <p class="strong">Ειδοποιήσεις</p>
            Επιθυμώ να ειδοποιούμαι μόλις εκδοθεί λογαριασμός για το συγκεκριμένο ακίνητο μέσω:
            <form action="update_choice.php" method="post">
                <div style="padding-top:10px;padding-left:30px;">    
                    <input id="NotificationType_Email" type="checkbox" name="box[]" value="email"/><label for="NotificationType_Email"><b>e-mail</b></label>
                    <br/>
                    <input id="NotificationType_SMS" type="checkbox" name="box[]" value="sms"/><label for="NotificationType_SMS"><b>SMS</b></label>                     
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="eb-only align_left">
                            <input type="submit" name="Notify$btnUpdate" value="Ενημέρωση" class="button" />
                        </div>
                    </div>
                </div>
                <input type="hidden" name="estate" id="NotificationEstate">
            </form>
        </div>

        <div class="w3-agileits-info-main">
            <p class="strong">Ηλεκτρονικός Λογαριασμός </p>
            <form action="update_ebill.php" method="post">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="cell">
                            <input id="chkEBillOnly" type="checkbox" name="chkEBillOnly" value="ebill"/><label for="chkEBillOnly">Επιθυμώ να λαμβάνω <b>MONO</b> Ηλεκτρονικό Λογαριασμό για το συγκεκριμένο ακίνητο  και να διακοπεί η αποστολή του έντυπου λογαριασμού.</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="eb-only align_left">
                            <input type="submit" name="EBill$btnUpdate" value="Ενημέρωση" id="EBill" class="button" />
                        </div>
                    </div>
                    <input type="hidden" name="estate2" id="EbillUpdate">
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
        <script>

                var val = $('#lstUserCustomerCodes').val();
                $(document).ready(function () {
                    onSelectionChanged(val);
                    $('#NotificationEstate').val(val);
                    $('#EbillUpdate').val($('#lstUserCustomerCodes').val());
                });

                function onSelectionChanged(estate) {
                    trigger1(estate);
                    trigger2(estate);
                    trigger3(estate);
                }

                function trigger1(estate) {
                    $('#NotificationEstate').val(estate);
                    $.get("notify_choice.php?hydro=" + $('#lstUserCustomerCodes').val(), function (response) {
                        values = JSON.parse(response);
                        changeCheckBoxes(values['sms'], values['email']);
                    });
                }
                function changeCheckBoxes(SMSchecked, EMAILchecked) {
                    $('#NotificationType_SMS').prop('checked', SMSchecked == 0 ? false : true);
                    $('#NotificationType_Email').prop('checked', EMAILchecked == 0 ? false : true);
                }
                function trigger2(estate) {
                    $('#EbillUpdate').val(estate);
                    $.get("check_ebill.php?hydro=" + $('#lstUserCustomerCodes').val(), function (response) {
                        values = JSON.parse(response);
                        changeCheckBoxes2(values['ebill']);
                    });
                }
                function changeCheckBoxes2(EBILLchecked) {
                    $('#chkEBillOnly').prop('checked', EBILLchecked == 0 ? false : true);
                }

                function trigger3(estate) {
                    var tableBody = $('#acountsTable > tbody')
                    tableBody.empty();
                    tableBody.append('<tr><td align="center" colspan="2"><strong>Φόρτωση Δεδομένων...</strong></td></tr>');

                    $.get("retrieve_log.php?hydro=" + $('#lstUserCustomerCodes').val(), function (response) {
                        values = JSON.parse(response);
                        append_logs(values);
                    });
                }

                function append_logs(json) {
                    var tableBody = $('#acountsTable > tbody')
                    tableBody.empty();
                    for (i in json) {
                        tableBody.append("<tr>" +
                                "   <td>" + ((parseInt(i)) + 1) + "</td>" +
                                "   <td><a href='" + json[i]['link'] + "' target='_blank'>" + json[i]['name'] + "</a></td>" +
                                "</tr>")
                    }
                }
        </script>       
    </body>
</html>