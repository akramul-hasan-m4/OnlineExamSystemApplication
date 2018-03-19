var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	$scope.SuccessMSG = '';
	$scope.ErrorMSG = '';

	$scope.loadTable = function() {
		$http({
			method : "GET",
			url : "/reference"
		}).then(function mySuccess(response) {
			$scope.allReferences = response.data;
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
	
	$scope.saveRef = function() {
		var dataObj = {
				courses 		: {
					courseId : $scope.courseId
					},
				referenceHeader : $scope.referenceHeader
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'POST',
			url : '/reference',
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
	
	$scope.refId = '';
	$scope.EditRow = function(data) {
		$scope.rowData = data;
		if (data != null) {
			$scope.refId = data.refId;
			$scope.courseId = data.courses.courseId;
			$scope.referenceHeader = data.referenceHeader;
		} else {
			$scope.clearform();
		}
	}
	
	$scope.updateRefInfo = function() {
		var dataObj = {
				refId 		: $scope.refId,
				bookId 		: $scope.bookId,
				courses 		: {
					courseId : $scope.courseId
					},
				referenceHeader : $scope.referenceHeader
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'PUT',
			url : '/reference/' + $scope.refId,
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

	$scope.DeleteRow = function(refId) {
		if (confirm('Are you sure to delete this reference ?')) {
			$http({
				method : 'DELETE',
				url : '/reference/' + refId
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
		$scope.referenceHeader = "";
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