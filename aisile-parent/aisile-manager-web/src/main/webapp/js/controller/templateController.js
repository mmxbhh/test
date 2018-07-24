app.controller('templateController' ,function($scope,$http,templateService,$controller,brandService,specificationService){	
	
	$controller('baseController',{$scope:$scope});//继承	
	$scope.list=[];
	        //读取列表数据绑定到表单中  
			$scope.findAll=function(){
				templateService.findAll.success(
					function(response){
						$scope.list=response;
					}			
				);
			}
	        //演示加载品牌数据
			$scope.brandList={data:[{id:1,text:'联想'},{id:2,text:'华为'},{id:3,text:'小米'}]};//品牌列表
			//演示加载规格数据
			$scope.specList={data:[{id:1,text:'联想'},{id:2,text:'华为'},{id:3,text:'小米'}]};//品牌列表
			$scope.brandList={data:[]};//品牌列表
			$scope.specList={data:[]};//规格列表
			//下拉列表
			$scope.findBrandList = function(){
				brandService.selectOptionList().success(
						function(response){
							$scope.brandList={data:response};	
						}
					);		
			}
			//下拉列表
			$scope.findSpecList = function(){
				specificationService.selectOptionList().success(
						function(response){
							$scope.specList={data:response};	
						}
				);		
			}
			
			
	        //分页
	        $scope.findPage=function(page,rows){
	        	templateService.findPage(page,rows).success(
	        			function(response){
	        				$scope.list=response.rows;	
	        				$scope.paginationConf.totalItems=response.total;//更新总记录数
	        			}			
	        	);
	        }
	        
			
	   
	     
	        //修改回显
	       $scope.findOne=function(id){
	    	   templateService.findOne(id).success(
	    				function(response){
	    					$scope.entity= response;	
	    					$scope.entity.brandIds=  JSON.parse($scope.entity.brandIds);//转换品牌列表
	    					$scope.entity.specIds=  JSON.parse($scope.entity.specIds);//转换规格列表
	    					$scope.entity.customAttributeItems= JSON.parse($scope.entity.customAttributeItems);//转换扩展属性
	    			     }
	    		);				
	    	}
	       //新增
	       $scope.entity={specificationOptionList:[]};
	       $scope.addTableRow=function(){	
	    	   //添加一行
	   		$scope.entity.customAttributeItems.push({});
	   	}
	     //批量选项删除行 
	   	$scope.deleTablenRow=function(index){
	   		//splice方法  有两个参数， 第一个是从谁开始删除，第二个是删除介个
	   		//splice从1开始
	   		$scope.entity.customAttributeItems.splice(index,1);//删除			
	   	} 
	       //添加
	    //真正修改 和添加的保存方法写在了一块
	       $scope.save=function(){
	   		var methodName={};//方法名称
	   		if($scope.entity.id!=null){//如果有ID
	   			methodName=templateService.update($scope.entity);//则执行修改方法 
	   		}else{
	   			methodName=templateService.add($scope.entity);
	   		}	
	   		methodName.success(
	   				function(response){
	   					if(response.success){
	   						   //重新查询 
	   				            $scope.reloadList();//重新加载
	   					}else{
	   						   alert(response.message);
	   					 }
	   				}		
	   		);				
	   }
	     
	      //删除 
	        $scope.dele=function(){			
	        		//获取选中的复选框			
	        	templateService.dele($scope.selectIds).success(
	        				function(response){
	        					if(response.success){
	        							$scope.reloadList();//刷新列表
	        					}						
	        				}		
	        		);				
	        }
	      
	      //分页+模糊
	      $scope.searchEntity={};//定义搜索对象 	
	      $scope.search=function(page,rows){
	    	  templateService.search(page,rows,$scope.searchEntity).success(
	    			function(response){
	    					$scope.paginationConf.totalItems=response.total;//总记录数 
	    					$scope.list=response.rows;//给列表变量赋值 
	    			}		
	    		);				
	    	}
		});	