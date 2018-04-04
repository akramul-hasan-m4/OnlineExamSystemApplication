var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	$scope.securityQuestions = [
		"What is Your Favourite Teacher name ?",
		"what is your Primary School name ?",
		"what is your best friend name ?",
		"what is your first phone number ?",
		"what is your favourite game ?",
		"what is your hobby ?"
	];
	
	$scope.loadUserInfo = function() {
		$http({
			method : "GET",
			url : "/user/userInfo"
		}).then(function mySuccess(response) {
			$scope.userId = response.data.userId;
			$scope.firstName = response.data.firstName;
			$scope.lastName = response.data.lastName;
			$scope.email = response.data.email;
			$scope.phone = response.data.phone;
			$scope.photo = response.data.photo;
			$scope.password = response.data.password;
			$scope.gender = response.data.gender;
			$scope.currentAddress = response.data.currentAddress;
			$scope.permanentAddress = response.data.permanentAddress;
			$scope.status = response.data.status;
			$scope.securityQuestion = response.data.securityQuestion;
			$scope.securityAns = response.data.securityAns;
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	};

});