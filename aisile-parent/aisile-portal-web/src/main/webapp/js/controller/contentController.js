app.controller("contentController",function($scope,contentService){
	$scope.contentList=[];//广告集合
	$scope.findByCategoryId = function(categoryId){
		contentService.findByCategoryId(categoryId).success(function(response){
			//相当于[ [轮播图],[大广告],[小]]  list集合嵌套list集合
			$scope.contentList[categoryId] = response;
		})
	}
})