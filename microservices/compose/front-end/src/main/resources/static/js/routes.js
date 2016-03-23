angular.module('main').config(['$routeProvider',
    function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/example.html',
                controller: 'exampleController'
            }, null)
            .when('/list', {
                templateUrl: '/list.html',
                controller: 'exampleController'
            }, null)
            .when('/register/user',{
                templateUrl: '/user/registerUser.html',
                controller: ''
            },null)
        ;
    }]);