 
app.controller('itemCatController' ,function($scope,$http,itemCatService,$controller){	
	$controller('baseController',{$scope:$scope});//继承	
	$scope.list=[];
	$scope.entity5 =[];
	$scope.parentIdW=0;
	$scope.grade=1;//默认一级
	$scope.setGrade = function(value){
		$scope.grade = value;
	}
	$scope.findAllList = function(p_entity){
		//通过对象的模式,前台点击下级那就是传 
		if($scope.grade==1){
			$scope.entity_1 = null;
			$scope.entity_2 = null;
		}
		if($scope.grade==2){
			$scope.entity_1 = p_entity;
			$scope.entity_2 = null;
		}
		if($scope.grade==3){
			$scope.entity_2 = p_entity;
		}
		$scope.findAllByParentId(p_entity.id);
		
	}
	
	$scope.findAllByParentId = function(parentId){
		   $scope.parentIdW = parentId;
		itemCatService.findAllByParentId(parentId).success(function(response){
			
			$scope.list=response;
		});
	}
	
	
	//读取列表数据绑定到表单中  
			$scope.findAll=function(){
				itemCatService.findAll.success(
					function(response){
						$scope.list=response;
					}			
				);
			}
	        
		
	        //分页
	        $scope.findPage=function(page,rows){
	        	itemCatService.findPage(page,rows).success(
	        			function(response){
	        				$scope.list=response.rows;	
	        				$scope.paginationConf.totalItems=response.total;//更新总记录数
	        			}			
	        	);
	        }
	        
			
	   
	       
	        //$scope.entity1=[];
	        //修改回显
	       $scope.findOne=function(id){
	    		itemCatService.findOne(id).success(
	    				function(response){
	    					$scope.entity= response;
	    					console.log($scope.entity);
	    					alert($scope.entity);
	    			     }
	    		);				
	    	}
	       
	    
	        //真正修改 和添加的保存方法写在了一块
	       $scope.save=function(){
	    	
	    	 $scope.entity.parentId = $scope.parentIdW;
	   		var methodName={};//方法名称
	   		if($scope.entity.id!=null){//如果有ID
	   			methodName=itemCatService.update($scope.entity);//则执行修改方法 
	   		}else{
	   			methodName=itemCatService.add($scope.entity);
	   		}	
	   		methodName.success(
	   				function(response){
	   					if(response.success){
	   						   //重新查询 
	   				            //$scope.reloadList();//重新加载
	   						$scope.findAllByParentId($scope.entity.parentId);
	   					}else{
	   						   alert(response.message);
	   						   
	   					 }
	   				}		
	   		);				
	   }
	     
	      //删除 
	        $scope.dele=function(){		
	        		//获取选中的复选框			
	        		itemCatService.dele($scope.selectIds).success(
	        				function(response){
	        					if(response.success){
	        							$scope.reloadList();//刷新列表
	        					}						
	        				}		
	        		);				
	        }
	        //判断是否根据最后子节点删除 ，为调用
	        $scope.pd=function(){
	        var findOneBySelectIdAndParId={};
    		findOneBySelectIdAndParId = itemCatService.findOneBySelectIdAndParId($scope.selectIds);
    		findOneBySelectIdAndParId.success(
	   				function(response){
	   					$scope.entity5 = response;//console.log($scope.entity5);alert($scope.entity5.length)
	   					if($scope.entity5.length==0){
	   						$scope.dele($scope.selectIds);
			        			alert("删除成功")
			        	}else{
			        		alert("请先删除最后一级");
			        }
	   				})
              }
	        $scope.delele=function(){	
	        	if($scope.grade==1){
	        		$scope.pd();
	        	}
	        	if($scope.grade==2){         
	        		$scope.pd();
	        	}
	        	if($scope.grade==3){
	        		$scope.dele($scope.selectIds);
	        		alert("删除成功");
	        	}
	        }
	      
	      //分页+模糊
	      $scope.searchEntity={};//定义搜索对象 	
	      $scope.search=function(page,rows){
	    		itemCatService.search(page,rows,$scope.searchEntity).success(
	    			function(response){
	    					$scope.paginationConf.totalItems=response.total;//总记录数 
	    					$scope.list=response.rows;//给列表变量赋值 
	    			}		
	    		);				
	    	}
		});	