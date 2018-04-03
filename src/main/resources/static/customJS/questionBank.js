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
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	};
	
	$scope.saveQus = function() {
			var typeObj = {
					courseId : $scope.courseId,
					bookId : $scope.bookId,
					refId: $scope.refId,
					chId : $scope.chapterId,
					questionTitle : $scope.questionTitle,
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
			}).then(function(response) {
				$scope.SuccessMSG = response.headers('SuccessMSG');
				$scope.messageAlart();
				$scope.loadTable();
			}, function myError(response) {
				$scope.ErrorMSG = response.headers('ErrorMSG');
				$scope.messageAlart();
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
	
	$scope.qusBankId = '';
	$scope.EditRow = function(data) {
		$scope.rowData = data;
		if (data != null) {
			$scope.disableCombo = false ;
			$scope.disableRef = false;
			$scope.qusBankId = data.qusBankId;
			$scope.courseId = data.courseId;
			$scope.bookId = data.bookId
			$scope.refId = data.refId;
			$scope.chapterId = data.chapterId;
			$scope.questionTitle = data.questionTitle;
			$scope.option1 = data.option1;
			$scope.option2 = data.option2;
			$scope.option3 = data.option3;
			$scope.option4 = data.option4;
			$scope.ans = data.ans;
		} else {
			$scope.reset();
		}
	}
	
	$scope.updateQusInfo = function() {

		var dataObj = {
				qusBankId 	  : $scope.qusBankId ,
				courseId 	  : $scope.courseId,
				bookId 		  : $scope.bookId,
				refId		  : $scope.refId,
				chId 		  : $scope.chapterId,
				questionTitle : $scope.questionTitle,
				option1 	  : $scope.option1,
				option2 	  : $scope.option2,
				option3 	  : $scope.option3,
				option4 	  : $scope.option4,
				ans 		  : $scope.ans
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'PUT',
			url : '/quesionsBank/' + $scope.qusBankId,
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
	
	$scope.DeleteRow = function(qusBankId) {
		if (confirm('Are you sure to delete this Question ?')) {
			$http({
				method : 'DELETE',
				url : '/quesionsBank/' + qusBankId,
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
	
	$scope.reset = function (){
		$scope.courseId="";
		$scope.bookId="";
		$scope.chapterId="";
		$scope.refId="";
		$scope.questionTitle = "";
		$scope.option1 = "";
		$scope.option2 = "";
		$scope.option3 = "";
		$scope.option4 = "";
		$scope.ans = "";
		$scope.disableCombo = true ;
		$scope.disableRef = true ;
	}
});