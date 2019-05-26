<!doctype html>
<html lang="en" dir="ltr">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta http-equiv="Content-Language" content="en" />
	<meta name="msapplication-TileColor" content="#2d89ef">
	<meta name="theme-color" content="#4188c9">
	<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="mobile-web-app-capable" content="yes">
	<meta name="HandheldFriendly" content="True">
	<meta name="MobileOptimized" content="320">
	<link rel="icon" href="./favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" type="image/x-icon" href="./favicon.ico" />
	<!-- Generated: 2018-04-16 09:29:05 +0200 -->
	<title>Computer Networking | Temperature</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">
	<script src="./assets/js/require.min.js"></script>
	<script>
		requirejs.config({
			baseUrl: '.'
		});
	</script>


  <script>
  require(['c3', 'jquery'], function(c3, $) {
    $(document).ready(function(){
      var chart = c3.generate({
        bindto: '#chart-wrapper', // id of chart wrapper
        data: {
          columns: [
              // each columns data
            ['data1',<?php
            require_once('conn.php');
          //$query="select * from sensorValues order by dateTime ASC";
              $query1 = "select Temperature from sensorValues";
              $row1 = mysqli_query($conn, $query1);
              $counter=7;
              for ($i=0; $i <$counter; $i++) {
                $values1 = mysqli_fetch_array($row1);
                echo $values1['Temperature'];
                echo ',';
              }
             ?>]
          ],
          labels: true,
          type: 'line', // default type of chart
          colors: {
            'data1': tabler.colors["blue"]
          },
          names: {
              // name of each serie
            'data1': 'Temperature'
          }
        },
        axis: {
          x: {
            type: 'category',
            // name of each category
            categories: ['Monday', 'Tuseday', 'Wednesday', 'Thursday', 'Friday', 'Saturday','Sunday']
          },
        },
        legend: {
                  show: false, //hide legend
        },
        padding: {
          bottom: 0,
          top: 0
        },
      });
    });
  });
  </script>




	<!-- Dashboard Core -->
	<link href="./assets/css/dashboard.css" rel="stylesheet" />
	<script src="./assets/js/dashboard.js"></script>
	<!-- c3.js Charts Plugin -->
	<link href="./assets/plugins/charts-c3/plugin.css" rel="stylesheet" />
	<script src="./assets/plugins/charts-c3/plugin.js"></script>
	<!-- Google Maps Plugin -->
	<link href="./assets/plugins/maps-google/plugin.css" rel="stylesheet" />
	<script src="./assets/plugins/maps-google/plugin.js"></script>
	<!-- Input Mask Plugin -->
	<script src="./assets/plugins/input-mask/plugin.js"></script>
</head>

<body class="">
	<div class="page">
		<div class="page-main">

			<div class="header py-4">
				<div class="container">
					<div class="d-flex">
						<a class="header-brand" href="./index.php">
							<img src="./assets/images/IOT.jpg" class="header-brand-img" alt="IOT Project">
						</a>
						<div class="d-flex order-lg-2 ml-auto">

							<div class="dropdown d-none d-md-flex">
								<a class="nav-link icon" data-toggle="dropdown">
									<i class="fe fe-bell">

									</i>
									<span class="nav-unread">

									</span>
								</a>
								<div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
									<a href="#" class="dropdown-item d-flex">
										<span class="avatar mr-3 align-self-center" style="background-image: url(demo/faces/male/41.jpg)">

										</span>
										<div>
											<strong>Muhammad Hamza</strong>
											This is Comp Networking Project
											<div class="small text-muted">6th Semester (BSCS)</div>
										</div>
									</a>
									<a href="#" class="dropdown-item d-flex">
										<span class="avatar mr-3 align-self-center" style="background-image: url(demo/faces/female/1.jpg)">

										</span>
										<div>
											<strong>Umer Farooq</strong>
											This is Comp Networking Project
											<div class="small text-muted">6th Semester (BSCS)</div>
										</div>
									</a>

                  <a href="#" class="dropdown-item d-flex">
										<span class="avatar mr-3 align-self-center" style="background-image: url(demo/faces/female/1.jpg)">

										</span>
										<div>
											<strong>Ammar Farooq Khan</strong>
											This is Comp Networking Project
											<div class="small text-muted">6th Semester (BSCS)</div>
										</div>
									</a>

									<div class="dropdown-divider"></div>
									<a href="#" class="dropdown-item text-center text-muted-dark">
										This is Group Project
									</a>
								</div>
							</div>


							<div class="dropdown">
								<a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown"> <span class="avatar" style="background-image: url(./img/header/profile.png)"></span>
									<span class="ml-2 d-none d-lg-block"> <span class="text-default">Muhammad Hamza</span> <small class="text-muted d-block mt-1">Administrator</small>
									</span>
								</a>
								<div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
									<a class="dropdown-item" href="#"> <i class="dropdown-icon fe fe-user"></i> Profile
									</a> <a class="dropdown-item" href="#"> <i class="dropdown-icon fe fe-settings"></i> Settings
									</a> <a class="dropdown-item" href="#"> <span class="float-right"><span class="badge badge-primary">6</span></span>
										<i class="dropdown-icon fe fe-mail"></i> Inbox
									</a> <a class="dropdown-item" href="#"> <i class="dropdown-icon fe fe-send"></i> Message
									</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#"> <i class="dropdown-icon fe fe-help-circle"></i> Need help?
									</a> <a class="dropdown-item" href="#"> <i class="dropdown-icon fe fe-log-out"></i> Sign out
									</a>
								</div>
							</div>
						</div>
						<a href="#" class="header-toggler d-lg-none ml-3 ml-lg-0" data-toggle="collapse" data-target="#headerMenuCollapse"> <span class="header-toggler-icon"></span>
						</a>
					</div>
				</div>
			</div>
			<div class="header collapse d-lg-flex p-0" id="headerMenuCollapse">
				<div class="container">
					<div class="row align-items-center">

						<div class="col-lg order-lg-first">
							<ul class="nav nav-tabs border-0 flex-column flex-lg-row">
								<li class="nav-item">
									<a href="./index.php" class="nav-link">
										<i class="fe fe-home">
										</i>
										Home
									</a>
								</li>

								<li class="nav-item dropdown">
									<a href="#" class="nav-link" data-toggle="dropdown">
										<i class="fa fa-header">
										</i>
										Help
									</a>
									<div class="dropdown-menu dropdown-menu-arrow">
										<a href="#" class="dropdown-item ">Help</a>
									</div>
								</li>

							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="my-3 my-md-5">
				<div class="container">
					<div class="row row-cards">
						<div class="col-lg-4">
							<div class="card">
								<br>
								<div class="container" style='height:20; width:20;'>

									<div class="card-body">
										<div class="row">
											<div class="col-md-12 col-lg-12">
												<a href="index.php" class="btn  btn-primary btn-block">Home</a>
											</div>
										</div>
									</div>
									<div class="card-body">
										<div class="row">
											<div class="col-md-12 col-lg-12">
												<a href="Temperature.php" class="btn  btn-primary btn-block">Temperature</a>
											</div>
										</div>
									</div>
									<div class="card-body">
										<div class="row">
											<div class="col-md-12 col-lg-12">
												<a href="Humidity.php" class="btn  btn-primary btn-block">Humidity</a>
											</div>
										</div>
									</div>
								</div>
							</div>

              <div class="card">
                <div class="card-header">
                  <h3 class="card-title">Temperature Graph</h3>
                </div>
                <div class="card-body">
                  <div id="chart-wrapper" style="height: 16rem"></div>
                </div>
              </div>

						</div>
						<div class="col-lg-8">
							<div class="card">
								<form action="#" method="post" class="card">
									<div class="card-header">
										<h3 class="card-title">Temperature (Values)</h3>
									</div>
									<div class="card-body">
										<div class="row">
											<div class="col-md-12 col-lg-12">
												<div class="table-responsive">
													<table class="table mb-0">
														<thead>
															<tr>
																<th>ID</th>
																<th>Device Name</th>
																<th>Temperature</th>
																<th>Date & Time</th>
															</tr>
														</thead>

														<?php
															require_once('conn.php');
														//$query="select * from sensorValues order by dateTime ASC";
															$query = "select * from sensorValues order by dateTime DESC";
															$row = mysqli_query($conn, $query);
															while($values = mysqli_fetch_array($row)){
																echo '<tr>';
																	echo '<td>';
																	echo $values['ID'];
																	echo '</td>';

																	echo '<td>';
																	echo $values['Device'];
																	echo '</td>';

																	echo '<td>';
																	echo $values['Temperature'];
																	echo '</td>';

																	echo '<td>';
																	echo $values['dateTime'];
																	echo '</td>';
															}
														?>

													</table>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<footer class="footer">
			<div class="container">
				<div class="row align-items-center flex-row-reverse">

					<div class="col-12">
						Copyright © 2019
						<a href="#">Compute Science Students</a>
						All rights reserved.
					</div>
				</div>
			</div>
		</footer>

	</div>
</body>

</html>
