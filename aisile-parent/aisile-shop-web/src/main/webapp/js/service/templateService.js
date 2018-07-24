//封装所有连接后端的代码,需要$http
	 app.service('templateService',function($http){
		//查询全部
		 this.findAll = function(){
			 return $http.get('../typeTemplate/findAll.do');
		 }
		this.findPage = function(page,rows){
			return $http.get('../typeTemplate/findPage.do?page='+page+'&rows='+rows);
		}
		//带参数分页查询
		this.search = function(page,rows,searchEntity){
			return $http.post('../typeTemplate/search.do?page='+page+"&rows="+rows,searchEntity);
		}
		//修改
		this.update = function(entity ){
			return $http.post('../typeTemplate/update.do',entity);
		}
		//添加
		this.add = function(entity){
			return $http.post('../typeTemplate/add.do',entity );
		}
		//删除
		this.dele = function(selectIds){
			return $http.get('../typeTemplate/dele.do?ids='+selectIds);
		}
		//根据id查询对象
		this.findOne = function(id){
			return $http.get('../typeTemplate/findOne.do?id='+id);
		}
		
		//查询规格列表
		this.findSpecList = function(id){
			return $http.get('../typeTemplate/findOptionList.do?id='+id);
		}
	 })