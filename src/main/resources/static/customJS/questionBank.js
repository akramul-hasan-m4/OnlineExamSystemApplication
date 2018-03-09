var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http, $timeout) {
	
	$scope.disableCombo = true ;
	$scope.disableRef = true ;
	
	$scope.allQuestion={};
	$scope.loadTable = function (){
		$http({
			method : "GET",
			url : "/quesionsBank"
		}).then(function mySuccess(response) {
			$scope.allQuestion = response.data;
		}, function myError(response) {
			$scope.errorStatus = response.statusText;
		});
	};
	
	$scope.saveQus = function() {
			var typeObj = {
					courseId : $scope.courseId,
					bookId : $scope.bookId,
					refId: $scope.refId,
					chId : $scope.chapterId,
					questionTitle : $scope.questionTitle,
					teacherId : 1,
					option1 : $scope.option1,
					option2 : $scope.option2,
					option3 : $scope.option3,
					option4 : $scope.option4,
					ans 	: $scope.ans
			}
			typeObj = JSON.stringify(typeObj);
			
			$http({
				method: 'POST',
				url: '/quesionsBank',
				data: typeObj,
				headers: {'Content-Type': 'application/json'}
			}).then(function(data, status, headers, config) {
						$scope.loadTable();
					});
	}
	
	$http({
		method : "GET",
		url : "/courses"
	}).then(function mySuccess(response) {
		$scope.allCourses = response.data;
		
	}, function myError(response) {
		$scope.errorStatus = response.statusText;
	});
	
	$scope.selectedCourse = function(courseId){
		$http({
			method : "GET",
			url : "/books/"+courseId
		}).then(function mySuccess(response) {
			$scope.bookId=null;
			$scope.chapterId=null;
			$scope.refId=null;
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
});