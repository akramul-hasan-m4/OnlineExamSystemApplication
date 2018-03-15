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
	
	$scope.saveUserRegitrationInfo = function() {
		var dataObj = {
				firstName 		 : $scope.firstName,
				lastName 		 : $scope.lastName,
				email 			 : $scope.email,
				phone 			 : $scope.phone,
				photo			 : 'photo',
				password		 : $scope.password,
				gender			 : $scope.gender,
				currentAddress 	 : $scope.currentAddress,
				permanentAddress : $scope.permanentAddress,
				status			 : 'Active',
				securityQuestion : $scope.securityQuestion,
				securityAns		 : $scope.securityAns
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'POST',
			url : '/user',
			data : dataObj,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			//$scope.SuccessMSG = response.headers('SuccessMSG');
			//$scope.messageAlart();
			//$scope.loadTable();
		}, function myError(response) {
			//$scope.ErrorMSG = response.headers('ErrorMSG');
			//$scope.messageAlart();
		});
	}
});