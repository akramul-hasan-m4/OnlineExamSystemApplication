var todos = angular.module('quesPaper', [ 'ui.bootstrap' ]);

todos.controller('quesPaperController', function($scope, $http) {

	$scope.startExam = false;
	$scope.startButton = true;

	$scope.startNow = function() {
		$scope.startExam = true;
		$scope.startButton = false;
		$scope.loadQustions();
	}

	$scope.filteredQues = [];
	$scope.currentPage = 0;
	$scope.numPerPage = 1;
	$scope.maxSize = 1;
	$scope.questions = [];

	$scope.loadQustions = function() {
		$http({
			method : "GET",
			url : "/questionPaper/1/1"
		}).then(function mySuccess(response) {
			$scope.questions = response.data;
		}, function myError(response) {
			$scope.errorStatus = response.statusText;
		});
	};

	$scope.loadQustions();

	$scope.numPages = function() {
		return Math.ceil($scope.questions.length / $scope.numPerPage);
	};

	$scope.$watch('currentPage + numPerPage', function() {
		var begin = (($scope.currentPage - 0) * $scope.numPerPage);
		var end = begin + $scope.numPerPage;
		$scope.filteredQues = $scope.questions.slice(begin, end);
	});

	$scope.collectAns = function(event, qusBankId) {
		console.log(event);
		console.log(qusBankId);

	}

});