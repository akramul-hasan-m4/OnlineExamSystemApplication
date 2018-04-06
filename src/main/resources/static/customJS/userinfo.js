var app = angular.module('myApp', []);

app.directive('examFileModel', ['$parse', function($parse){
	return {
		restrict: 'A',
		link: function(scope, element, attrs){
			var model = $parse(attrs.examFileModel);
			var modelSetter = model.assign;

			element.bind('change', function(){
				scope.$apply(function(){
					modelSetter(scope, element[0].files[0]);
				})
			})
		}
	}
}]);

app.service('multipartForm', ['$http', function($http){
	this.post = function(uploadUrl, data){
		var fd = new FormData();
		for(var key in data)
			fd.append(key, data[key]);
		$http.post(uploadUrl, fd, {
			transformRequest: angular.indentity,
			headers: { 'Content-Type': undefined }
		}).then(function mySuccess(response) {
			$window.location.assign('/pages/emailVerification');
		}, function myError(response) {

		});
	}
}]);

app.controller('myCtrl', function($scope, $http,$window, $location, multipartForm) {
	
	$scope.SuccessMSG = '';
	$scope.ErrorMSG = '';
	$scope.showRegistrationForm = true ;
	$scope.showVerificationForm = true ;
	$scope.verified = false;
	$scope.teacherMsg = false;
	$scope.studentMsg = false;
	$scope.userId = '';
	
	$scope.securityQuestions = [
		"What is Your Favourite Teacher name ?",
		"what is your Primary School name ?",
		"what is your best friend name ?",
		"what is your first phone number ?",
		"what is your favourite game ?",
		"what is your hobby ?"
	];
	
	$scope.userInfo = {};
	$scope.saveUserRegitrationInfo = function(){
		var uploadUrl = '/user/save';
		multipartForm.post(uploadUrl, $scope.userInfo);
		console.log($scope.userInfo);
		$window.location.assign('/pages/emailVerification');
	}
	
	$scope.loadTable = function() {
		$http({
			method : "GET",
			url : "/user"
		}).then(function mySuccess(response) {
			$scope.allUsers = response.data;
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	};
	
	$scope.messageAlart = function () {
		if ($scope.SuccessMSG != undefined) {
			$("#success-alert").fadeTo(2000, 500).slideUp(500, function() {
				$("#success-alert").slideUp(500);
			});
		}
		if ($scope.ErrorMSG != undefined) {
			$("#error-alert").fadeTo(2000, 500).slideUp(500, function() {
				$("#error-alert").slideUp(500);
			});
		}
	}
	
	$scope.verify = function (verificationCode) {
		$http({
			method : "GET",
			url : "/user/code/"+verificationCode
		}).then(function mySuccess(response) {
			$scope.userId = response.data.users.userId;
			$scope.SuccessMSG = response.headers('SuccessMSG');
			$scope.showVerificationForm = false ;
			$scope.verified = true;
			$scope.messageAlart();
		}, function myError(response) {
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
	
	$scope.clickTeacher = function () {
		if (confirm('Are you sure ?')) {
			$scope.verified = false;
			$scope.teacherMsg = true;
			$scope.studentMsg = false;
		}
	}
	
	$scope.clickStudent = function () {
		$scope.verified = false;
		$scope.studentMsg = true;
		$scope.teacherMsg = false;
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
						$window.location.assign('/pages/login');
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
	
	$scope.DeleteRow = function(userId) {
		if (confirm('Are you sure to delete this User ?')) {
			$http({
				method : 'DELETE',
				url : '/user/' + userId
			}).then(function(response) {
				$scope.SuccessMSG = response.headers('SuccessMSG');
				$scope.messageAlart();
				$scope.loadTable();
			}, function myError(response) {
				$scope.ErrorMSG = response.headers('ErrorMSG');
				$scope.messageAlart();
			});
		}
	}
	
});