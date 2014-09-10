function Hello($scope, $http) {
    $http.get('http://rest-service.guides.spring.io/greeting').
        success(function(data) {
            $scope.greeting = data;
        });
}

function Person($scope, $http) {
    $http.get('http://localhost:8080/spring4/data/person').
        success(function(data) {
            $scope.person = data;
        });
}