//封装所有连接后端的代码,需要$http
	 app.service('specificationService',function($http){
		//查询全部
		 this.findAll = function(){
			 return $http.get('../specification/findAll.do');
		 }
		this.findPage = function(page,rows){
			return $http.get('../specification/findPage.do?page='+page+'&rows='+rows);
		}
		//带参数分页查询
		this.search = function(page,rows,searchEntity){
			return $http.post('../specification/search.do?page='+page+"&rows="+rows,searchEntity);
		}
		//修改
		this.update = function(entity ){
			return $http.post('../specification/update.do',entity);
		}
		//添加
		this.add = function(entity){
			return $http.post('../specification/add.do',entity );
		}
		//删除
		this.dele = function(selectIds){
			return $http.get('../specification/dele.do?ids='+selectIds);
		}
		//根据id查询对象
		this.findOne = function(id){
			return $http.get('../specification/findOne.do?id='+id);
		}
		//模板里的下拉列表
		this.selectOptionList = function(){
			return $http.get('../specification/selectOptionList.do');
		}
		
	 })