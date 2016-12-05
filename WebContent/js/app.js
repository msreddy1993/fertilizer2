var TEMPLATES_PATH = "/html/";
angular.module("fertilizer", function(){
	$stateProvider
	  .state('products',{
	    views: {
	      'content': {
	        templateUrl: TEMPLATES_PATH+'product-list.html',
	        controller: function($scope){}
	      }
	    }
	  })
});