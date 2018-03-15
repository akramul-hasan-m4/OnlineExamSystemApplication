var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	$scope.login = function() {
		var dataObj = {
				email 	: $scope.email,
				password 	: $scope.password
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'POST',
			url : '/login',
			data : dataObj,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.SuccessMSG = response.headers('SuccessMSG');
			//$scope.messageAlart();
			//$scope.loadTable();
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			//$scope.messageAlart();
		});
	}
});