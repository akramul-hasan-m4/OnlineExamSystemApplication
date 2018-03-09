var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {
	
	$scope.examStatuss = ["Active", "Inactive"];
	$scope.examDuration = '';
	
	
	
	$("#examDuration").timepicker({
		onSelect: function(data) {
			$scope.$apply(function () {
				$scope.examDuration = data;
			});
		}
	});
	
	$("#examDate").datepicker({
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
			$scope.errorStatus = response.statusText;
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
		}).then(function (data, status, headers, config) {
					$scope.loadTable();
				});
	}
	
	$scope.rowData = {};
	$scope.EditRow=function (data){
		$scope.rowData = data;
		console.log(data);
		/*$scope.courseId = data.courseId ;
		$scope.examDate = data.examDate ;
		$scope.totalQuestion = data.totalQuestion ;
		$scope.examDuration = data.examDuration ;
		$scope.totalMark = data.totalMark ;
		$scope.passMark = data.passMark ;
		$scope.examStatus = data.examStatus ;*/
	}
});