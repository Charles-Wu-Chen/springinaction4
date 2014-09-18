
taskManagerModule.controller('TaskDetailCtrl', ['$scope', '$routeParams', '$http',
                                                   function($scope, $routeParams, $http) {

	var urlBase="http://localhost:8080/spring4";
	$scope.selection = [];
	$scope.statuses=['ACTIVE','COMPLETED'];
	$scope.priorities=['HIGH','LOW','MEDIUM'];
	
    $http.get(urlBase + '/tasks/' + $routeParams.taskId).success(function(data) {
       $scope.task = data;
     });
    
    
    

	//add a new task
	$scope.updateTask = function updateTask() {
		if($scope.task.taskName=="" || $scope.task.taskDesc=="" || $scope.task.taskPriority == "" || $scope.task.taskStatus == ""){
			alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
		}
		else{
		 $http.post(urlBase + '/tasks/update/'+$scope.task.taskId+'/' +$scope.task.taskName+'/'+$scope.task.taskDescription+'/'+$scope.task.taskPriority+'/'+$scope.task.taskStatus).
		  success(function(data) {
			 alert("Task Update");
			 $scope.tasks = data;	
			 $scope.taskName="";
			 $scope.taskDesc="";
			 $scope.taskPriority="";
			 $scope.taskStatus="";		 
		    });
		}
	};
	
	
   }]);