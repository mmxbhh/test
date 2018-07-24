//封装所有连接后端的代码,需要$http
	 app.service('sellerService',function($http){
		//查询全部
		 this.findAll = function(){
			 return $http.get('../seller/findAll.do');
		 }
		this.findPage = function(page,rows){
			return $http.get('../seller/findPage.do?page='+page+'&rows='+rows);
		}
		//带参数分页查询
		this.search = function(page,rows,searchEntity){
			return $http.post('../seller/search.do?page='+page+"&rows="+rows,searchEntity);
		}
		//修改
		this.update = function(entity ){
			return $http.post('../seller/update.do',entity);
		}
		
		//添加
		this.add = function(entity){
			return $http.post('../seller/add.do',entity );
		}
		//删除
		this.dele = function(){
			return $http.get('../seller/delete.do?ids='+$scope.selectIds);
		}
		//根据id查询对象
		this.findOne = function(id){
			return $http.get('../seller/findOne.do?id='+id);
		}
		
	 })