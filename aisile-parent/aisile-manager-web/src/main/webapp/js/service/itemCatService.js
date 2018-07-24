//封装所有连接后端的代码,需要$http
	 app.service('itemCatService',function($http){
		//查询全部
		 this.findAll = function(){
			 return $http.get('../itemCat/findAll.do');
		 }
		this.findPage = function(page,rows){
			return $http.get('../itemCat/findPage.do?page='+page+'&rows='+rows);
		}
		//带参数分页查询
		this.search = function(page,rows,searchEntity){
			return $http.post('../itemCat/search.do?page='+page+"&rows="+rows,searchEntity);
		}
		//修改
		this.update = function(entity ){
			return $http.post('../itemCat/update.do',entity);
		}
		//添加
		this.add = function(entity){
			return $http.post('../itemCat/add.do',entity );
		}
		//删除
		this.dele = function(selectIds){
			return $http.get('../itemCat/delete.do?ids='+selectIds);
		}		//根据id查询对象
		this.findOne = function(id){
			return $http.get('../itemCat/findOne.do?id='+id);
		}
		this.findOneBySelectIdAndParId = function(id){
			return $http.get('../itemCat/findOneBySelectIdAndParId.do?id='+id);
		}
		//根据parentid获取类别
		this.findAllByParentId = function(id){
			return $http.get('../itemCat/findAllByParentId.do?parentId='+id);
		}
	 })