
app.controller('contentController' ,function($scope,$http,contentService,contentCategoryService,uploadService,$controller){	
	$controller('baseController',{$scope:$scope});//继承	
	$scope.list=[];
	$scope.status=["无效","有效"];
	        //读取列表数据绑定到表单中  
			$scope.findAll=function(){
				contentService.findAll().success(
					function(response){
						console.log(response);
						$scope.list=response;
					}			
				);
			}
	        
		
	        //分页
	        $scope.findPage=function(page,rows){
	        	contentService.findPage(page,rows).success(
	        			function(response){
	        				console.log(response);
	        				$scope.list=response.rows;	
	        				$scope.paginationConf.totalItems=response.total;//更新总记录数
	        			}			
	        	);
	        }
	        
			
	   
	     
	        //修改回显
	       $scope.findOne=function(id){
	    		contentService.findOne(id).success(
	    				function(response){
	    					console.log(response);
	    					$scope.entity= response;					
	    			     }
	    		);				
	    	}
	        //真正修改 和添加的保存方法写在了一块
	       $scope.save=function(){
	   		var methodName={};//方法名称
	   		if($scope.entity.id!=null){//如果有ID
	   			methodName=contentService.update($scope.entity);//则执行修改方法 
	   		}else{
	   			methodName=contentService.add($scope.entity);
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
	        		contentService.dele($scope.selectIds).success(
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
	    		contentService.search(page,rows,$scope.searchEntity).success(
	    			function(response){
	    				console.log(response);
	    					$scope.paginationConf.totalItems=response.total;//总记录数 
	    					$scope.list=response.rows;//给列表变量赋值 
	    			}		
	    		);				
	    	}
	      
	      //上传图片
	      $scope.entity={};
	      $scope.uploadFile=function(){	  
	  		uploadService.uploadFile().success(function(response) {        	
	          	if(response.success){//如果上传成功，取出url
	          		$scope.entity.pic=response.message;//设置文件地址
	          	}else{
	          		alert(response.message);
	          	}
	          }).error(function() {           
	          	     alert("上传发生错误");
	          });        
	      }; 
	      
	      //查询加载广告分类
	      $scope.findContentCategoryList = function(){
	    	  contentCategoryService.findAll().success(function(response){
	    		  $scope.contentCategoryList=response;
	    	  })
	      }
	      
	      //开启 关闭状态
	      $scope.updateStatus = function(status){
	    	  contentService.updateStatus($scope.selectIds,status).success(function(response){
	    		  if(response.success){
	    			  	alert(response.message);
	    				$scope.reloadList();//刷新列表
						$scope.selectIds=[];//清空ID集合
	    		  }else{
	    			  alert(response.message);
	    		  }
	    	  })
	      }
	      
		});	//总括号