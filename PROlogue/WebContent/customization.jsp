<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
</style>
<head>
<meta charset="ISO-8859-1">
<title>Position/Customize</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body style="background-color: #ddd">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">ProRecruiter</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="customization.jsp">Customization <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="recommendation.jsp">Dashboard</a>
      </li>
    </ul>
    <ul class="navbar-nav ml-auto ">
    	<li class="nav-item">
     	 <button class="btn btn-outline-success " type="submit" onclick="update()">Update Database</button>
    	</li>
	</ul>
  </div>
</nav>
	<div class="card  card-body" style="margin:5% 18% auto 18%">
		<form method="post" action="" >
			<p class="text-center lead" style="font-size:35px">Enter Details</p>
			<div class="form-group">
				<label for="position-input">Position name</label>
				<input type="text" name="position" id="position-input" class="form-control"/>
			</div>
			<p class="text-center lead" style="font-size:28px">Customize</p>
			<small><b>Note: </b>10 is the best performance</small><br/><br/>
			requirement: <input type="text" id="requirementName"> <br><br>
			<p id="requirementList">
				<!-- will be inserted by JS -->
			</p>
			<div class="form-group row">
			    <label class="col-sm-3"for="exampleFormControlSelect1">GitHub activity</label>
			    <select class="form-control col-sm-2" id="GitHubActivity">
			      <option>0</option>
			      <option>1</option>
			      <option>2</option>
			      <option>3</option>
			      <option>4</option>
			      <option>5</option>
			      <option>6</option>
			      <option>7</option>
			      <option>8</option>
			      <option>9</option>
			      <option>10</option>
		   		</select>
  			</div>
			<div class="form-group row">
			    <label class="col-sm-3"for="exampleFormControlSelect1">Competitive Programming</label>
			    <select class="form-control col-sm-2" id="CPActivity">
			      <option>0</option>
			      <option>1</option>
			      <option>2</option>
			      <option>3</option>
			      <option>4</option>
			      <option>5</option>
			      <option>6</option>
			      <option>7</option>
			      <option>8</option>
			      <option>9</option>
			      <option>10</option>
		   		</select>
  			</div>
			<input type="button" class=" btn btn-outline-success" style="margin-left:45%" value="add requirement" onclick="addReq()"> <br><br>
			<input type="button" class=" btn btn-outline-success" style="margin-left:45%" value="submit" onclick="sendDetails()">
		</form>
</div>
	<script>
		function update(){
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		    	// for future use
		    };
		    xhttp.open("POST", "update", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("");
		}
		
		function sendDetails(){
				var positionName = document.getElementById("position-input").value;
				var GitHubActivity = document.getElementById("GitHubActivity").value;
				var CPActivity = document.getElementById("CPActivity").value;
				var requirements = document.getElementsByName("requirements");
				var keyValue = "positionName="+positionName+"&GitHubActivity="+GitHubActivity+"&CPActivity="+CPActivity+"&keyValue=";
				for (i = 0; i < requirements.length; i++) {
					var key = requirements[i].options[requirements[i].selectedIndex].value;
					var value = requirements[i].options[requirements[i].selectedIndex].text;
				 	keyValue+=key+":"+value;
				 	if(i != requirements.length-1){
				 		keyValue+=","
				 	}
				}

				var xhttp = new XMLHttpRequest();
			    xhttp.onreadystatechange = function() {
			    	// for future use
			    };
			    xhttp.open("POST", "customization", true);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				console.log(keyValue);
				xhttp.send(keyValue);
		}

		function addReq(){
				var addSelect = '<div class="form-group row"><label class="col-sm-3"for="exampleFormControlSelect1">';
				addSelect+=document.getElementById("requirementName").value+'</label>'+'<select name="requirements" class="form-control col-sm-2">';
				for(var i=1;i<=10;i++){
					addSelect+='<option value="'+document.getElementById("requirementName").value+'">'+i+'</option>';
				}
				addSelect+='</select></div>';
				document.getElementById("requirementList").innerHTML = document.getElementById("requirementList").innerHTML+addSelect;
		}
		
	</script>
</body>
</html>
