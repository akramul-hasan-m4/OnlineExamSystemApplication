var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	$scope.SuccessMSG = '';
	$scope.ErrorMSG = '';

	$scope.loadTable = function() {
		$http({
			method : "GET",
			url : "/chapter"
		}).then(function mySuccess(response) {
			$scope.allChapters = response.data;
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	};
	
	$scope.messageAlart = function() {
		if ($scope.SuccessMSG != undefined) {
			$("#success-alert").fadeTo(2000, 500).slideUp(500, function() {
				$("#success-alert").slideUp(500);
			});
		}
		if ($scope.ErrorMSG != undefined) {
			$("#error-alert").fadeTo(2000, 500).slideUp(500, function() {
				$("#error-alert").slideUp(500);
			});
		}
	}
	
	$http({
		method : "GET",
		url : "/books"
	}).then(function mySuccess(response) {
		$scope.allBooks = response.data;
		
	}, function myError(response) {
		$scope.errorStatus = response.statusText;
	});
	
	$scope.saveChapter = function() {
		var dataObj = {
				books			:{
					bookId : $scope.bookId
					},
				chapterName 	: $scope.chapterName
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'POST',
			url : '/chapter',
			data : dataObj,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.SuccessMSG = response.headers('SuccessMSG');
			$scope.messageAlart();
			$scope.loadTable();
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	}
	
	$scope.chId = '';
	$scope.EditRow = function(data) {
		$scope.rowData = data;
		if (data != null) {
			$scope.chId = data.chId;
			$scope.bookId = data.books.bookId;
			$scope.chapterName = data.chapterName;
		} else {
			$scope.clearform();
		}
	}
	
	$scope.updateChapterInfo = function() {

		var dataObj = {
				chId 		: $scope.chId,
				books			:{
					bookId : $scope.bookId
					},
				chapterName 	: $scope.chapterName
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'PUT',
			url : '/chapter/' + $scope.chId,
			data : dataObj,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.SuccessMSG = response.headers('SuccessMSG');
			$scope.messageAlart();
			$scope.loadTable();
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	}
	
	$scope.DeleteRow = function(chId) {
		if (confirm('Are you sure to delete this Chapter ?')) {
			$http({
				method : 'DELETE',
				url : '/chapter/' + chId
			}).then(function(response) {
				$scope.SuccessMSG = response.headers('SuccessMSG');
				$scope.messageAlart();
				$scope.loadTable();
			}, function myError(response) {
				$scope.ErrorMSG = response.headers('ErrorMSG');
				$scope.messageAlart();
			});
		}
	}
	
	$scope.clearform = function() {
		$scope.bookId = "";
		$scope.chapterName = "";
	}
});