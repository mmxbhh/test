//封装所有连接后端的代码,需要$http
	 app.service('contentService',function($http){
		//查询全部
		 this.findAll = function(){
			 return $http.get('../content/findAll.do');
		 }
		this.findPage = function(page,rows){
			return $http.get('../content/findPage.do?page='+page+'&rows='+rows);
		}
		//带参数分页查询
		this.search = function(page,rows,searchEntity){
			return $http.post('../content/search.do?page='+page+"&rows="+rows,searchEntity);
		}
		//修改
		this.update = function(entity ){
			return $http.post('../content/update.do',entity);
		}
		//添加
		this.add = function(entity){
			return $http.post('../content/add.do',entity );
		}
		//删除
		this.dele = function(selectIds){
			return $http.get('../content/delete.do?ids='+selectIds);
		}
		//根据id查询对象
		this.findOne = function(id){
			return $http.get('../content/findOne.do?id='+id);
		}
		//更换有效无效
		this.updateStatus = function(selectIds,status){
			return $http.get('../content/updateStatus.do?ids='+selectIds+"&status="+status);
		}
		
	 })