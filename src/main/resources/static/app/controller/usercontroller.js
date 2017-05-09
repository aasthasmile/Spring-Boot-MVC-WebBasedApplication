'use strict';
(function() {
	var UserController = function($scope, GenericFactory) {
		$scope.users = [], $scope.user = {
			gender : 'M'
		};
		var me = this;

		me.doGetUsers = function() {
			GenericFactory.getMethod({
				url : '/users',
				successCB : function(result) {
					$scope.users = result;
				}
			});
		}

		$scope.doDeleteUser = function(id) {
			GenericFactory.deleteMethod({
				url : '/users',
				data : id,
				successCB : function(result) {
					me.doGetUsers();
				}
			});
		}
		$scope.doEditUser = function(id) {
			for (var i = 0; i < $scope.users.length; i++) {
				if ($scope.users[i].id === id) {
					$scope.user = $scope.users[i];
					break;
				}
			}
		}

		me.createUser = function() {
			GenericFactory.postMethod({
				url : '/users',
				data : $scope.user,
				successCB : function(result) {
					me.doGetUsers();
				}
			});
		}

		me.updateUser = function() {
			GenericFactory.putMethod({
				url : '/users',
				data : $scope.user,
				successCB : function(result) {
					me.doGetUsers();
				}
			});
		}

		$scope.doSubmit = function() {
			if ($scope.users.id !== '') {
				me.updateUser();
			} else {
				me.createUser();
			}
		}
		$scope.reset = function() {
			$scope.user = {
				gender : 'M'
			};
			$scope.userForm.$setPristine();
		}
		function init() {
			me.doGetUsers();
		}
		init();
	};

	myApp.controllers.controller('UserController', [ '$scope',
			'GenericFactory', UserController ]);
})();