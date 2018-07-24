app.service("contentService",function($http){
		this.findByCategoryId = function(categoryId){
			//因为index.html在根目录下 所以不用../ 了  以前是在admiin下
			return $http.get("content/findByCategoryId.do?categoryId="+categoryId);
		}
})