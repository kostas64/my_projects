<!DOCTYPE html>
<html>

<head>
    <title>Title of the document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js"></script>
    <script>
        setInterval(
            function() {
                $('#content').load('temp.php');
            }, 30000);
    </script>
</head>

<body>
    <title>Auto Load Page in Div using Jquery</title>
    <h1>Auto Load Page in Div</h1>
    <div id="content"> Please wait .. </div> 
</body>

</html>