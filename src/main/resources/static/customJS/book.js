var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	$scope.SuccessMSG = '';
	$scope.ErrorMSG = '';

	$scope.loadTable = function() {
		$http({
			method : "GET",
			url : "/books"
		}).then(function mySuccess(response) {
			$scope.allBooks = response.data;
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	};

	$http({
		method : "GET",
		url : "/courses"
	}).then(function mySuccess(response) {
		$scope.allCourses = response.data;
		
	}, function myError(response) {
		$scope.errorStatus = response.statusText;
	});
	
	$scope.saveBook = function() {
		var dataObj = {
				courses 	: {
					courseId : $scope.courseId
					},
				bookName 	: $scope.bookName,
				authorName 	: $scope.authorName,
				edition 	: $scope.edition
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'POST',
			url : '/books',
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
	
	$scope.bookId = '';
	$scope.EditRow = function(data) {
		$scope.rowData = data;
		if (data != null) {
			$scope.bookId = data.bookId;
			$scope.courseId = data.courses.courseId;
			$scope.bookName = data.bookName;
			$scope.authorName = data.authorName;
			$scope.edition = data.edition;
		} else {
			$scope.clearform();
		}
	}
	
	$scope.updateBookInfo = function() {

		var dataObj = {
				bookId 		: $scope.bookId,
				courses 	: {
					courseId : $scope.courseId
					},
				bookName 	: $scope.bookName,
				authorName 	: $scope.authorName,
				edition 	: $scope.edition
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'PUT',
			url : '/books/' + $scope.bookId,
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
	
	$scope.DeleteRow = function(courseId) {
		if (confirm('Are you sure to delete this book ?')) {
			$http({
				method : 'DELETE',
				url : '/books/' + courseId,
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
		$scope.courseId = "";
		$scope.bookName = "";
		$scope.authorName = "";
		$scope.edition = "";
	}
	
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
});