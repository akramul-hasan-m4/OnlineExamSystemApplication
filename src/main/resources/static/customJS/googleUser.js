var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http, $window, $location) {
	
	$scope.course = false ;
	$scope.userInfo = false ;
	$scope.userId = '';
	
	$scope.checkUser = function (){
		$http({
			method : "GET",
			url : "/googleUser"
		}).then(function mySuccess(response) {
			$scope.userInfo = true;
		}, function myError(response) {
			$window.location.assign('/pages/studentWelcomePage');
		});
	};
	
	$scope.saveGoogleUserInfo = function () {
		var dataObj = {
				phone : $scope.phone,
				gender : $scope.gender,
				currentAddress: $scope.currentAddress,
				permanentAddress : $scope.permanentAddress,
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method: 'POST',
			url: '/googleUser',
			data: dataObj,
			headers: {'Content-Type': 'application/json'}
		}).then(function (response) {
			$scope.SuccessMSG = response.headers('SuccessMSG');
			$scope.messageAlart();
			$scope.userId = response.data.userId;
			$scope.course = true ;
			$scope.userInfo = false ;
		},function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	}
	
	$http({
		method : "GET",
		url : "/courses"
	}).then(function mySuccess(response) {
		$scope.allCourses = response.data;
		
	}, function myError(response) {
		$scope.ErrorMSG = response.headers('ErrorMSG');
	});
	
	$http({
		method : "GET",
		url : "/batch"
	}).then(function mySuccess(response) {
		$scope.allbatches = response.data;
		
	}, function myError(response) {
		$scope.ErrorMSG = response.headers('ErrorMSG');
	});
	
	
	$scope.messageAlart = function () {
		if ($scope.SuccessMSG != undefined) {
			$("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
			$("#success-alert").slideUp(500);
		});
		}
		if ($scope.ErrorMSG != undefined) {
			$("#error-alert").fadeTo(2000, 500).slideUp(500, function(){
			$("#error-alert").slideUp(500);
		});
		}
	}
	
	$scope.savestudentInfo = function (){
		if ($scope.selectedCourse != "" && $scope.selectedCourse != undefined){
			if ($scope.batchId != "" && $scope.batchId != undefined){
					var typeObj = {
							users 		: {
								userId 	: $scope.userId
							},
							batchs 		:{
								batchId : $scope.batchId
							},
							selectedCourse : $scope.selectedCourse
					}
					typeObj = JSON.stringify(typeObj);
		console.log('userid'+$scope.userId);
					$http({
						method: 'POST',
						url: '/student',
						data: typeObj,
						headers: {'Content-Type': 'application/json'}
					}).then(function(response) {
						$scope.SuccessMSG = response.headers('SuccessMSG');
						$window.location.assign('/pages/studentWelcomePage');
						$scope.messageAlart();
					}, function myError(response) {
						$scope.ErrorMSG = response.headers('ErrorMSG');
						$scope.messageAlart();
					});
				} else {
					$scope.cmsg = '';
					$scope.bmsg = 'Please Select a batch';
				}
		} else {
			$scope.cmsg = 'Please Select a course';
		}
	}
});