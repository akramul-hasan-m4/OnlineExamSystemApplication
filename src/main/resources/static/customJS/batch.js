var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {

	$scope.SuccessMSG = '';
	$scope.ErrorMSG = '';

	$scope.loadTable = function() {
		$http({
			method : "GET",
			url : "/batch"
		}).then(function mySuccess(response) {
			$scope.allBatches = response.data;
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	};
	
	$scope.saveBatch = function() {
		var dataObj = {
				batchNo	  :$scope.batchNo,
				seatLimit :$scope.seatLimit
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'POST',
			url : '/batch',
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
	
	$scope.batchId = '';
	$scope.EditRow = function(data) {
		$scope.rowData = data;
		if (data != null) {
			$scope.batchId = data.batchId;
			$scope.batchNo = data.batchNo;
			$scope.seatLimit = data.seatLimit;
		} else {
			$scope.clearform();
		}
	}

	$scope.updateBatch = function() {

		var dataObj = {
			batchId   : $scope.batchId,
			batchNo	  : $scope.batchNo,
			seatLimit : $scope.seatLimit
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'PUT',
			url : '/batch/' + $scope.batchId,
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

	$scope.DeleteRow = function(batchId) {
		if (confirm('Are you sure to delete this Batch ?')) {
			$http({
				method : 'DELETE',
				url : '/batch/' + batchId,
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
		$scope.batchId = "";
		$scope.batchNo = "";
		$scope.seatLimit = "";
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