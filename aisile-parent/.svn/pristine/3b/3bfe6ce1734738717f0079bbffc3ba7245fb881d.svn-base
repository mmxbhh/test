
app.controller('sellerController' ,function($scope,$http,sellerService,$controller){	
	$controller('baseController',{$scope:$scope});//继承	
	$scope.list=[];
	        //读取列表数据绑定到表单中  
			$scope.findAll=function(){
				sellerService.findAll.success(
					function(response){
						$scope.list=response;
					}			
				);
			}
	        
		
	        //分页
	        $scope.findPage=function(page,rows){
	        	sellerService.findPage(page,rows).success(
	        			function(response){
	        				$scope.list=response.rows;	
	        				$scope.paginationConf.totalItems=response.total;//更新总记录数
	        			}			
	        	);
	        }
	      
			
	   
	     
	        //修改回显
	       $scope.findOne=function(id){
	    	   sellerService.findOne(id).success(
	    				function(response){
	    					$scope.entity= response;					
	    			     }
	    		);				
	    	}
	        //真正修改 和添加的保存方法写在了一块
	       $scope.save=function(){
	   		var methodName={};//方法名称
	   		if($scope.entity.id!=null){//如果有ID
	   			methodName=sellerService.update($scope.entity);//则执行修改方法 
	   		}else{
	   			methodName=sellerService.add($scope.entity);
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
	       //注册商家
	       $scope.add = function(){
	    	   sellerService.add($scope.entity).success(
	    		function(response){
	    			if(response.success){
	    				//如果成功跳转到登录页面
	    				location.href="shoplogin.html";
	    			}else{
	    				alert(response.message);
	    			}
	    		}
	    	   )
	       }
	       
	     
	      //删除 
	        $scope.dele=function(){			
	        		//获取选中的复选框			
	        	sellerService.dele.success(
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
	    	  sellerService.search(page,rows,$scope.searchEntity).success(
	    			function(response){
	    					$scope.paginationConf.totalItems=response.total;//总记录数 
	    					$scope.list=response.rows;//给列表变量赋值 
	    			}		
	    		);				
	    	}
		});	