<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <head>
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <meta charset="ISO-8859-1">
      <title>Insert title here</title>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
      <style>
         span{
             position:fixed;
             top:120px;
         }
         span:hover{
             cursor:pointer;
         }
         .sidenav {
             height: 100%; /* 100% Full-height */
             width: 0; /* 0 width - change this with JavaScript */
             position: fixed; /* Stay in place */
             z-index: 1; /* Stay on top */
             top: 0; /* Stay at the top */
             left: 0;
             background-color: #111; /* Black*/
             overflow-x: hidden; /* Disable horizontal scroll */
             padding-top: 60px; /* Place content 60px from the top */
             transition: 0.5s; /* 0.5 second transition effect to slide in the sidenav */
         }
         /* The navigation menu links */
         .sidenav a {
             padding: 8px 8px 8px 32px;
             text-decoration: none;
             font-size: 25px;
             color: #818181;
             display: block;
             transition: 0.3s;
         }
         /* When you mouse over the navigation links, change their color */
         .sidenav a:hover {
             color: #f1f1f1;
         }
         /* Position and style the close button (top right corner) */
         .sidenav .closebtn {
             position: absolute;
             top: 0;
             right: 25px;
             font-size: 36px;
             margin-left: 50px;
         }
         /* Style page content - use this if you want to push the page content to the right when you open the side navigation */
         #main {
             transition: margin-left .5s;
             padding: 20px;
         }
         /* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
         @media screen and (max-height: 450px) {
             .sidenav {padding-top: 15px;}
             .sidenav a {font-size: 18px;}
         }
      </style>
   </head>
   <body style="background-color: #ddd">
      <div id="mySidenav" class="sidenav">
         <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
         <a href="#">All Positions:</a>
         <a href="Intern.jsp">AI Intern</a>
         <a href="SofwareEngineer.jsp">Software Engineer</a>
         <a href="CEO.jsp">CEO</a>
         <a href="Contact.jsp">Contact Us for Help</a>
      </div>
      <span onclick="openNav()"><i class="material-icons" style="font-size:64px">menu</i></span>
      <div id="main">
         <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">ProRecruiter</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
               <ul class="navbar-nav mr-auto">
                  <li class="nav-item active">
                     <a class="nav-link" href="#">Dashboard <span class="sr-only">(current)</span></a>
                  </li>
                  <li class="nav-item">
                     <a class="nav-link" href="#">Settings</a>
                  </li>
               </ul>
               <form class="form-inline my-2 my-lg-0">
               </form>
            </div>
         </nav>
         <div class="card text-center card-body" style="margin:5% 18% auto 18%">
            <table>
               <tr>
                  <td>The data goes in the table here lol</td>
               </tr>
            </table>
         </div>
         <!-- Use any element to open the sidenav -->
      </div>
   </body>
   <script>
      function openNav() {
          document.getElementById("mySidenav").style.width = "250px";
      }

      /* Set the width of the side navigation to 0 */
      function closeNav() {
          document.getElementById("mySidenav").style.width = "0";
      }

      function openNav() {
          document.getElementById("mySidenav").style.width = "250px";
          document.getElementById("main").style.marginLeft = "250px";
      }

      /* Set the width of the side navigation to 0 and the left margin of the page content to 0 */
      function closeNav() {
          document.getElementById("mySidenav").style.width = "0";
          document.getElementById("main").style.marginLeft = "0";
      }
   </script>
</html>
