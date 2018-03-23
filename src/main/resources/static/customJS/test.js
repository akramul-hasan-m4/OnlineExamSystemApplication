var todos = angular.module('quesPaper', []);

todos.controller('quesPaperController', function($scope, $http) {

	$scope.backButtonDisable = true;
	$scope.nextButtonDisable = false;
	$scope.showResult = false ;
	$scope.showQus = true ;
	
	var currentPage = this;
	currentPage.numRecords = 1;
	currentPage.page = 1;

	$scope.filteredQues = [];
	$scope.totalQus = 0;
	$scope.currentQus = 1;
	var collectAns = [];

	$http({
		method : "GET",
		url : "/questionPaper/showCreatedQuestion"
	}).then(function mySuccess(response) {
		$scope.filteredQues = response.data;
		$scope.totalQus = response.data.length;
		console.log(response.data);
	}, function myError(response) {
		$scope.errorStatus = response.statusText;
	});

	currentPage.next = function(index, ans, qus) {
		
		$scope.currentQus = index + 2;
		var currentpageindex = index + 1;
		$scope.backButtonDisable = false;
		if (currentpageindex == $scope.filteredQues.length - 1) {
			$scope.nextButtonDisable = true;
		}
		currentPage.page = currentPage.page + 1;
	};

	currentPage.back = function(index) {
		if (index > 0) {
			$scope.currentQus = index;
			$scope.nextButtonDisable = false;
			$scope.backButtonDisable = false;
			currentPage.page = currentPage.page - 1;
		}

	};

	$scope.clickedAns = function(ans, bankId) {

		var data = {
			questionBank : {
				qusBankId : bankId
			},
			collectedAns : ans
		}

		if (collectAns.length > 0) {
			var ansIsExist = collectAns.find(function(v) {
				return v.questionBank.qusBankId == bankId;
			});
			if (ansIsExist) {
				collectAns.find(function(v) {
					return v.questionBank.qusBankId == bankId;
				}).collectedAns = ans;
			} else {
				collectAns.push(data);
			}
		} else {
			collectAns.push(data);
		}

	}

	$scope.finish = function(ans, qusBankId) {
		$http({ 
			method : 'PUT', url : '/questionPaper',
			data : JSON.stringify(collectAns),
			headers : { 'Content-Type' : 'application/json' }
		}).then(function(response) { 
			$scope.correctAns = response.data.CorrectAns;
			$scope.wrongAns = response.data.wrongAns;
			$scope.showResult = true ;
			$scope.showQus = false ;
			$scope.resultParcentage = ($scope.correctAns / $scope.totalQus) * 100;
		});
	}

});