app.controller('specificationController' ,function($scope,$http,specificationService,$controller){	
	
	$controller('baseController',{$scope:$scope});//继承	
	$scope.list=[];
	        //读取列表数据绑定到表单中  
			$scope.findAll=function(){
				specificationService.findAll.success(
					function(response){
						$scope.list=response;
					}			
				);
			}
	        
		
	        //分页
	        $scope.findPage=function(page,rows){
	        	specificationService.findPage(page,rows).success(
	        			function(response){
	        				$scope.list=response.rows;	
	        				$scope.paginationConf.totalItems=response.total;//更新总记录数
	        			}			
	        	);
	        }
	        
			
	   
	     
	        //修改回显
	       $scope.findOne=function(id){
	    	   specificationService.findOne(id).success(
	    				function(response){
	    					$scope.entity= response;					
	    			     }
	    		);				
	    	}
	       //新增
	       $scope.entity={specificationOptionList:[]};
	       $scope.addTableRow=function(){	
	    	   //添加一行
	   		$scope.entity.specificationOptionList.push({});
	   	}
	     //批量选项删除行 
	   	$scope.deleTablenRow=function(index){
	   		//splice方法  有两个参数， 第一个是从谁开始删除，第二个是删除介个
	   		//splice从1开始
	   		$scope.entity.specificationOptionList.splice(index,1);//删除			
	   	} 
	       //添加
	    //真正修改 和添加的保存方法写在了一块
	       $scope.save=function(){
	   		var methodName={};//方法名称
	   		if($scope.entity.specification.id!=null){//如果有ID
	   			methodName=specificationService.update($scope.entity);//则执行修改方法 
	   		}else{
	   			methodName=specificationService.add($scope.entity);
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
	        	specificationService.dele($scope.selectIds).success(
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
	    	  specificationService.search(page,rows,$scope.searchEntity).success(
	    			function(response){
	    					$scope.paginationConf.totalItems=response.total;//总记录数 
	    					$scope.list=response.rows;//给列表变量赋值 
	    			}		
	    		);				
	    	}
		});	