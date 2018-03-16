var app = angular.module('myApp', []);

app.directive('examFileModel', ['$parse', function($parse){
	return {
		restrict: 'A',
		link: function(scope, element, attrs){
			var model = $parse(attrs.examFileModel);
			var modelSetter = model.assign;

			element.bind('change', function(){
				scope.$apply(function(){
					modelSetter(scope, element[0].files[0]);
				})
			})
		}
	}
}]);

app.service('multipartForm', ['$http', function($http){
	this.post = function(uploadUrl, data){
		var fd = new FormData();
		for(var key in data)
			fd.append(key, data[key]);
		$http.post(uploadUrl, fd, {
			transformRequest: angular.indentity,
			headers: { 'Content-Type': undefined }
		});
	}
}]);

app.controller('myCtrl', function($scope, $http, multipartForm) {
	
	$scope.securityQuestions = [
					"What is Your Favourite Teacher name ?",
					"what is your Primary School name ?",
					"what is your best friend name ?",
					"what is your first phone number ?",
					"what is your favourite game ?",
					"what is your hobby ?"
		];
	
	$scope.userInfo = {};
	$scope.saveUserRegitrationInfo = function(){
		/*var dataObj = {
				firstName 		 : $scope.firstName,
				lastName 		 : $scope.lastName,
				email 			 : $scope.email,
				phone 			 : $scope.phone,
				photo			 : $scope.photo,
				password		 : $scope.password,
				gender			 : $scope.gender,
				currentAddress 	 : $scope.currentAddress,
				permanentAddress : $scope.permanentAddress,
				status			 : 'Active',
				securityQuestion : $scope.securityQuestion,
				securityAns		 : $scope.securityAns
		}
		dataObj = JSON.stringify(dataObj);*/
		console.log($scope.userInfo);
		var uploadUrl = '/user';
		multipartForm.post(uploadUrl, $scope.userInfo);
	}
	
	/*$scope.saveUserRegitrationInfo = function() {
		var dataObj = {
				firstName 		 : $scope.firstName,
				lastName 		 : $scope.lastName,
				email 			 : $scope.email,
				phone 			 : $scope.phone,
				photo			 : $scope.photo,
				password		 : $scope.password,
				gender			 : $scope.gender,
				currentAddress 	 : $scope.currentAddress,
				permanentAddress : $scope.permanentAddress,
				status			 : 'Active',
				securityQuestion : $scope.securityQuestion,
				securityAns		 : $scope.securityAns
		}
		dataObj = JSON.stringify(dataObj);
		console.log(dataObj);
		$http({
			method : 'POST',
			url : '/user',
			data : dataObj,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			//$scope.SuccessMSG = response.headers('SuccessMSG');
			//$scope.messageAlart();
			//$scope.loadTable();
		}, function myError(response) {
			//$scope.ErrorMSG = response.headers('ErrorMSG');
			//$scope.messageAlart();
		});
	}*/
});