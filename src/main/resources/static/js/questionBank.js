var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	$scope.firstname = "John";
	$scope.lastname = "Doe";
	
	$scope.loadTable = function (){
		$http({
			method : "GET",
			url : "/quesionsBank"
		}).then(function mySuccess(response) {
			$scope.allQuestion = response.data;
		}, function myError(response) {
			$scope.errorStatus = response.statusText;
		});
	};
	
	$scope.saveQus = function(ans) {
			var typeObj = {
					courseId : $scope.courseId,
					bookId : $scope.bookId,
					refId: $scope.refId,
					chapterId : $scope.chapterId,
					questionTitle : $scope.questionTitle,
					option1 : $scope.option1,
					option2 : $scope.option2,
					option3 : $scope.option3,
					option4 : $scope.option4,
					correctAnsNo : ans
			}
			
			typeObj = JSON.stringify(typeObj);
			$http.post('/quesionsBank', typeObj)
			.success(
				function(data, status, headers, config) {
				})
			/*.error(
				function(data, status, header, config) {
				});*/
				$scope.loadTable();
	}
});