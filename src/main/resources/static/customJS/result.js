var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	$scope.loadTable = function() {
		$http({
			method : "GET",
			url : "/examInfo/singleResult"
		}).then(function mySuccess(response) {
			$scope.firstName = response.data.students.users.firstName;
			$scope.lastName = response.data.students.users.lastName;
			$scope.generatedStId = response.data.generatedStId;
			$scope.course = response.data.students.selectedCourse;
			$scope.batch = response.data.students.batchs.batchNo;
			$scope.score = response.data.score;
			$scope.grade = response.data.grade;
		}, function myError(response) {
			// $scope.ErrorMSG = response.headers('ErrorMSG');
			// $scope.messageAlart();
			console.log('no data')
		});
	};
	
	$scope.getAllResult = function() {
		$http({
			method : "GET",
			url : "/examInfo"
		}).then(function mySuccess(response) {
			$scope.allResult = response.data;
		}, function myError(response) {
			// $scope.ErrorMSG = response.headers('ErrorMSG');
			// $scope.messageAlart();
			console.log('no data')
		});
	};
})