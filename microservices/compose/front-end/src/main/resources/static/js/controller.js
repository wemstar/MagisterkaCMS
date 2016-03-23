angular.module('main').controller('exampleController', function ($scope){
    $scope.message = 'Hello world!';
    $scope.list = [
        {
            firstName: 'John',
            lastName: 'Doe'
        },
        {
            firstName: 'Mark',
            lastName: 'Smith'
        },
        {
            firstName: 'James',
            lastName: 'Mole'
        }
    ];
});

angular.module.('main').controller('registerUser',function ($scope) {

    // create a blank object to handle form data.
    $scope.user = {};
    // calling our submit function.
    $scope.submitForm = function() {
        // Posting data to php file
        $http({
            method: 'POST',
            url: 'clone.php',
            data: $scope.user, //forms user object
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data) {
            if (data.errors) {
                // Showing errors.
                $scope.errorName = data.errors.name;
                $scope.errorUserName = data.errors.username;
                $scope.errorEmail = data.errors.email;
            } else {
                $scope.message = data.message;
            }
        });
    };

});