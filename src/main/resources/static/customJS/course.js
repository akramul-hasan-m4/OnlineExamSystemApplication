var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	$scope.loadCourses = function (){
		$http({
			method : "GET",
			url : "/courses"
		}).then(function mySuccess(response) {
			$scope.allCourses = response.data;
		}, function myError(response) {
			$scope.errorStatus = response.statusText;
		});
	};
});