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
	
	$scope.SuccessMSG = '';
	$scope.ErrorMSG = '';
	
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
		console.log($scope.userInfo);
		var uploadUrl = '/user';
		multipartForm.post(uploadUrl, $scope.userInfo);
	}
	
	$scope.loadTable = function() {
		$http({
			method : "GET",
			url : "/user"
		}).then(function mySuccess(response) {
			$scope.allUsers = response.data;
		}, function myError(response) {
			$scope.ErrorMSG = response.headers('ErrorMSG');
			$scope.messageAlart();
		});
	};
	
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