var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {
	
	$scope.examStatuss = ["Active", "Inactive"];
	$scope.examDuration = '';
	$scope.SuccessMSG = '';
	$scope.ErrorMSG = '';
	
	$("#examDuration").timepicker({
		onSelect: function(data) {
			$scope.$apply(function () {
				$scope.examDuration = data;
			});
		}
	});
	
	$("#examDate").datepicker({
		dateFormat: 'yy-mm-dd',
		onSelect: function(data) {
			$scope.$apply(function () {
				$scope.examDate = data;
			});
		}
	});
	
	$scope.allExamBoardInfo={};
	$scope.loadTable = function (){
		$http({
			method : "GET",
			url : "/examboard"
		}).then(function mySuccess(response) {
			$scope.allExamBoardInfo = response.data;
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
		$scope.SuccessMSG = response.headers('SuccessMSG');
		$scope.messageAlart();
	}, function myError(response) {
		$scope.ErrorMSG = response.headers('ErrorMSG');
		$scope.messageAlart();
	});
	
	$scope.saveExamDeclaration = function () {
		var dataObj = {
				courseId : $scope.courseId,
				examDate : $scope.examDate,
				totalQuestion: $scope.totalQuestion,
				examDuration : $scope.examDuration,
				totalMark : $scope.totalMark,
				passMark : $scope.passMark,
				examStatus : $scope.examStatus
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method: 'POST',
			url: '/examboard',
			data: dataObj,
			headers: {'Content-Type': 'application/json'}
		}).then(function (response) {
			$scope.SuccessMSG = response.headers('SuccessMSG');
			$scope.messageAlart();
			$scope.loadTable();
		},function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	}
	
	$scope.examId ='' ;
	$scope.EditRow = function (data){
		$scope.rowData = data;
		if(data != null) {
			$scope.examId = data.examId ;
			$scope.courseId = data.courseId ;
			$scope.examDate = data.examDate ;
			$scope.totalQuestion = data.totalQuestion ;
			$scope.examDuration = data.examDuration ;
			$scope.totalMark = data.totalMark ;
			$scope.passMark = data.passMark ;
			$scope.examStatus = data.examStatus ;
		}else {
			$scope.clearform();
		}
	}
	
	$scope.updateExamDeclaration = function (){
		
		var dataObj = {
				examId :	$scope.examId,
				courseId : $scope.courseId,
				examDate : $scope.examDate,
				totalQuestion: $scope.totalQuestion,
				examDuration : $scope.examDuration,
				totalMark : $scope.totalMark,
				passMark : $scope.passMark,
				examStatus : $scope.examStatus
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method: 'PUT',
			url: '/examboard/'+$scope.examId,
			data: dataObj,
			headers: {'Content-Type': 'application/json'}
		}).then(function (response) {
			$scope.SuccessMSG = response.headers('SuccessMSG');
			$scope.messageAlart();
			$scope.loadTable();
		}, function myError (response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	}
	
	$scope.DeleteRow = function (examId) {
		if (confirm('Are you sure to delete this exam declaration ?')) {
			$http({
				method: 'DELETE',
				url: '/examboard/'+examId,
			}).then(function (response) {
				$scope.SuccessMSG = response.headers('SuccessMSG');
				$scope.messageAlart();
				$scope.loadTable();
			}, function myError (response) {
				$scope.ErrorMSG = response.headers('ErrorMSG');
				$scope.messageAlart();
			});
		}
	}
	
	$scope.clearform = function (){
		$scope.courseId = "";
		$scope.examDate = "";
		$scope.totalQuestion = "" ;
		$scope.examDuration = "";
		$scope.totalMark = "" ;
		$scope.passMark = "" ;
		$scope.examStatus = "" ;
	}
	
	$scope.messageAlart = function () {
		if ($scope.SuccessMSG != undefined) {
			$("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
			$("#success-alert").slideUp(500);
		});
		}
		if ($scope.ErrorMSG != undefined) {
			$("#error-alert").fadeTo(2000, 500).slideUp(500, function(){
			$("#error-alert").slideUp(500);
		});
		}
	}
});