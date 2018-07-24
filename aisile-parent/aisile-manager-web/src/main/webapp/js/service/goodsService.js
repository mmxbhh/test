//封装所有连接后端的代码,需要$http
	 app.service('goodsService',function($http){
		//查询全部
		 this.findAll = function(){
			 return $http.get('../goods/findAll.do');
		 }
		this.findPage = function(page,rows){
			return $http.get('../goods/findPage.do?page='+page+'&rows='+rows);
		}
		//带参数分页查询
		this.search = function(page,rows,searchEntity){
			return $http.post('../goods/search.do?page='+page+"&rows="+rows,searchEntity);
		}
		//修改
		this.update = function(entity ){
			return $http.post('../goods/update.do',entity);
		}
		
		//添加
		this.addd = function(entity){
			return $http.post('../goods/add.do',entity );
		}
		//删除
		this.dele = function(selectIds){
			return $http.get('../goods/delete.do?ids='+selectIds);
		}
		//根据id查询对象
		this.findOne = function(id){
			return $http.get('../goods/findOne.do?id='+id);
		}
		this.updateStatus = function(ids,status){
			return  $http.get('../goods/updateStatus.do?ids='+ids+"&status="+status);
		}
		
	 })