var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	$http({
		method : "GET",
		url : "/examboard"
	}).then(function mySuccess(response) {
		$scope.examBoard = response.data;
	}, function myError(response) {
		$scope.errorStatus = response.statusText;
	});
});