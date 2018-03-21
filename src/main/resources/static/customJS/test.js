var todos = angular.module('quesPaper', []);

todos.controller('quesPaperController', function($scope, $http, $filter) {

	$scope.backButtonDisable = true;
	$scope.nextButtonDisable = false;
	var currentPage = this;
	currentPage.numRecords = 1;
	currentPage.page = 1;

	$scope.filteredQues = [];
	$scope.totalQus = 0;
	$scope.currentQus = 1;
	var collectAns = [];

		$http({
			method : "GET",
			url : "/questionPaper/1/1"
		}).then(function mySuccess(response) {
			$scope.filteredQues = response.data;
			$scope.totalQus = response.data.length;
			console.log(response.data);
		}, function myError(response) {
			$scope.errorStatus = response.statusText;
		});

	currentPage.next = function (index, ans, qus) {
		if (ans != undefined){
			collectAns.push({
				questionBank : {quesBankId : qus.qusBankId},
				collectedAns : ans
			});
		}
		$scope.currentQus = index + 2;
		var currentpageindex = index + 1;
		$scope.backButtonDisable = false;
		if (currentpageindex == $scope.filteredQues.length - 1){
			$scope.nextButtonDisable = true;
		}
		currentPage.page = currentPage.page + 1;
	};

	currentPage.back = function (index) {
		if (index > 0) {
			$scope.currentQus = index;
			$scope.nextButtonDisable = false;
			$scope.backButtonDisable = false;
			currentPage.page = currentPage.page - 1;
		}

	};

	$scope.finish = function(ans, qusBankId) {
		collectAns = JSON.stringify(collectAns);
		console.log('colletd = '+collectAns);
		
		$http({
			method : 'PUT',
			url : '/questionPaper',
			data : collectAns,
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