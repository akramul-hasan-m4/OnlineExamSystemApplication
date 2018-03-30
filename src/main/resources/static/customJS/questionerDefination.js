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
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
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
	
	$scope.saveDefinition = function () {
		var examDefinitionData = {
				qusLimitation : $scope.qusLimitation,
				exam :{
					examId : $scope.examId
					},
				courses :{
					courseId : $scope.courseId
					},
				bookId : $scope.bookId,
				refId: $scope.refId,
				chId : $scope.chapterId
					
		}
		examDefinitionData = JSON.stringify(examDefinitionData);
		
		console.log(examDefinitionData);
		$http({
			method: 'POST',
			url: '/questionerDefination',
			data: examDefinitionData,
			headers: {'Content-Type': 'application/json'}
		}).then(function (response) {
			$scope.SuccessMSG = response.headers('SuccessMSG');
			$scope.messageAlart();
			$scope.loadDefination();
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	}
	
	$scope.definationId = '';
	$scope.EditRow = function(data) {
		$scope.rowData = data;
		if (data != null) {
			$scope.definationId = data.definationId;
			$scope.courseId = data.courses.courseId;
			$scope.qusLimitation = data.qusLimitation;
			$scope.examId = data.exam.examId;
			$scope.bookId = data.bookId;
			$scope.refId = data.refId;
			$scope.chapterId = data.chapterId;
		} else {
			$scope.clearform();
		}
	}
	
	$scope.updateDefinitionInfo = function() {

		var examDefinitionData = {
				definationId : $scope.definationId,
				qusLimitation : $scope.qusLimitation,
				exam :{
					examId : $scope.examId
					},
				courses :{
					courseId : $scope.courseId
					},
				bookId : $scope.bookId,
				refId: $scope.refId,
				chId : $scope.chapterId
					
		}
		examDefinitionData = JSON.stringify(examDefinitionData);
		console.log(examDefinitionData);
		$http({
			method : 'PUT',
			url : '/questionerDefination/' + $scope.definationId,
			data : examDefinitionData,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.SuccessMSG = response.headers('SuccessMSG');
			$scope.messageAlart();
			$scope.loadDefination();
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	}
	
	$scope.DeleteRow = function(definationId) {
		if (confirm('Are you sure to delete this Defination ?')) {
			$http({
				method : 'DELETE',
				url : '/questionerDefination/' + definationId,
			}).then(function(response) {
				$scope.SuccessMSG = response.headers('SuccessMSG');
				$scope.messageAlart();
				$scope.loadDefination();
			}, function myError(response) {
				$scope.ErrorMSG = response.headers('ErrorMSG');
				$scope.messageAlart();
			});
		}
	}
	
	$scope.clearform = function() {
		$scope.qusLimitation = "";
		$scope.examId = "";
		$scope.courseId = "";
		$scope.bookId = "";
		$scope.refId = "";
		$scope.chId = "";
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