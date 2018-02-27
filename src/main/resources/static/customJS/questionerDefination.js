var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	$scope.disableCombo = true ;
	$scope.disableRef = true ;
	
	$http({
		method : "GET",
		url : "/examboard/active"
	}).then(function mySuccess (response) {
		$scope.examBoard = response.data;
	}, function myError(response) {
		$scope.errorStatus = response.statusText;
	});
	
	$scope.AllQusDefination={};
	$scope.loadDefination = function (){
		$http({
			method : "GET",
			url : "/questionerDefination"
		}).then(function mySuccess (response) {
			$scope.AllQusDefination = response.data;
		}, function myError(response) {
			$scope.errorStatus = response.statusText;
		});
	};
	
	$http({
		method : "GET",
		url : "/courses"
	}).then(function mySuccess (response) {
		$scope.allCourses = response.data;
		
	}, function myError(response) {
		$scope.errorStatus = response.statusText;
	});
	
	$scope.selectedCourse = function (courseId){
		$http({
			method : "GET",
			url : "/books/"+courseId
		}).then(function mySuccess(response) {
			$scope.booksFromSelectedCourse = response.data;
			$scope.disableCombo = false ;
		}, function myError(response) {
			$scope.errorStatus = response.statusText;
		});
		
		$http({
			method : "GET",
			url : "/reference/"+courseId
		}).then(function mySuccess(response) {
			$scope.referenceFromSelectedCourse = response.data;
			$scope.disableRef = false ;
		}, function myError(response) {
			$scope.errorStatus = response.statusText;
		});
	};
	
	$scope.chaptersFromSelectedBook={};
	$scope.chapters = function (bookId){
		$http({
			method : "GET",
			url : "/chapter/"+bookId
		}).then(function mySuccess(response) {
			$scope.chaptersFromSelectedBook = response.data;
			$scope.disableRef = true ;
		}, function myError(response) {
			$scope.errorStatus = response.statusText;
		});
	};
	
	$scope.ref = function (refId){
		$scope.disableCombo = true ;
	}
	
	$scope.reset = function (){
		$scope.courseId="";
		$scope.bookId="";
		$scope.chapterId="";
		$scope.refId="";
		$scope.disableCombo = true ;
		$scope.disableRef = true ;
	}
	
	$scope.saveDefinition = function (ans) {
		var examDefinitionData = {
				examId : $scope.examId,
				courseId : $scope.courseId,
				bookId : $scope.bookId,
				refId: $scope.refId,
				chId : $scope.chapterId,
				qusLimitation : $scope.qusLimitation,
				teacherId : 1
		}
		examDefinitionData = JSON.stringify(examDefinitionData);
		console.log(examDefinitionData);
		$http({
			method: 'POST',
			url: '/questionerDefination',
			data: examDefinitionData,
			headers: {'Content-Type': 'application/json'}
		}).then(function (data, status, headers, config) {
					$scope.loadDefination();
				});
	}
});