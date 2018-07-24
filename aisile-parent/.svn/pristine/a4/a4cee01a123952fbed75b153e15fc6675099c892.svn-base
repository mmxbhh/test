//$location 是一个服务
app.controller('goodsController' ,function($scope,$http,goodsService,$controller,itemCatService,templateService,$location,uploadService){	
	$controller('baseController',{$scope:$scope});//继承	
	
	
	$scope.list=[];
	        //读取列表数据绑定到表单中  
			$scope.findAll=function(){
				goodsService.findAll.success(
					function(response){
						$scope.list=response;
					}			
				);
			}
	        
		
	        //分页
	        $scope.findPage=function(page,rows){
	        	goodsService.findPage(page,rows).success(
	        			function(response){
	        				$scope.list=response.rows;	
	        				$scope.paginationConf.totalItems=response.total;//更新总记录数
	        			}			
	        	);
	        }
	      
			
	   
	     
	        //修改回显
	       $scope.findOne=function(id){
	    				
	    	  var id =  $location.search()['id'];//获取参数值
	    	  if(id==null){
	    		  return;
	    	  }
	    	  goodsService.findOne(id).success(
	    			  function(response){
	    					$scope.entity= response;
	    					//富文本编辑区赋值
	    					editor.html($scope.entity.tbGoodsDesc.introduction);
	    					//商品图片展示
	    					$scope.entity.tbGoodsDesc.itemImages= JSON.parse($scope.entity.tbGoodsDesc.itemImages);
	    					//商品扩展属性展示
	    					 $scope.entity.tbGoodsDesc.customAttributeItems = JSON.parse( $scope.entity.tbGoodsDesc.customAttributeItems);
	    					//商品规格展示
	    					 $scope.entity.tbGoodsDesc.specificationItems =  JSON.parse($scope.entity.tbGoodsDesc.specificationItems);
	    					 //商品sku表
	    					 for (var i = 0; i < $scope.entity.itemsList.length; i++) {
	    						 $scope.entity.itemsList[i].spec = JSON.parse( $scope.entity.itemsList[i].spec);
							}
	    			     }
	    		);
	    	}
	       
	       $scope.save=function(){
	    	   //把富本编辑器数据加载进来，描述数据
	    	   $scope.entity.tbGoodsDesc.introduction = editor.html();
	    	   var serviceObject={};//服务成对象
	    	   if($scope.entity.tbgoods.id!=null){
	    		   serviceObject = goodsService.update($scope.entity);
	    	   }else{
	    		   serviceObject = goodsService.addd($scope.entity);
	    	   }
	    	   serviceObject.success(
		   				function(response){
		   					if(response.success){
		   						$scope.entity={};
		   						
		   						aditor.html('');//清空富文本编辑器
		   						alert(response.message);
		   					}else{
		   						alert("失败"+response.message);
		   					 }
		   				}		
		   		);		
	   }
	       
	     
	      //删除 
	        $scope.dele=function(){			
	        		//获取选中的复选框			
	        	goodsService.dele($scope.selectIds).success(
	        				function(response){
	        					if(response.success){
	        							alert(response.message);
	        							$scope.reloadList();//刷新列表
	        					}else{
	        						alert(response.message);
	        					}					
	        					
	        				}		
	        		);				
	        }
	      
	      //分页+模糊
	      $scope.searchEntity={};//定义搜索对象 	
	      $scope.search=function(page,rows){
	    	  goodsService.search(page,rows,$scope.searchEntity).success(
	    			function(response){
	    					$scope.paginationConf.totalItems=response.total;//总记录数 
	    					$scope.list=response.rows;//给列表变量赋值 
	    			}		
	    		);				
	    	}
	      
	      /**
	       * 	1、在后端写关联查询的语句，返回的数据中直接有分类名称,就麻烦，双表查询就麻烦，
	       *  	2、在前端代码用ID去查询后端，异步返回商品分类名称，在页面上进行循环，这样就减少了服务器的压力
	       * 		集合的下标==id 集合的值就是名字
	       * */
	      //查询商品分类名称方法
	      $scope.status=["","未审核","审核通过","审核未通过"];
	      $scope.itemCatList=[];
	      	$scope.findItemCatList = function(){
	      		itemCatService.findAll().success(function(response){
	      			console.log(response);
	      			for (var i = 0; i < response.length; i++) {
	      				$scope.itemCatList[response[i].id]=response[i].name;
					}
	      			
	      		})
	      	}
	      	
	    	
			//更改状态
			$scope.updateStatus=function(status){
				goodsService.updateStatus($scope.selectIds,status).success(
					function(response){
						if(response.success){//成功
							$scope.reloadList();//刷新列表
							$scope.selectIds=[];//清空ID集合
						}else{
							alert(response.message);
						}
					}
				);		
			}
			 //上传图片
		      $scope.uploadFile=function(){	  
		  		uploadService.uploadFile().success(function(response) {        	
		          	if(response.success){//如果上传成功，取出url
		          		$scope.image_entity.url=response.message;//设置文件地址
		          	}else{
		          		alert(response.message);
		          	}
		          }).error(function() {           
		          	     alert("上传发生错误");
		          });        
		      }; 
		      //先实例化一下,定义页面实体结构
		     // $scope.entity={goods:{},tbGoodsDesc:{itemImages:[]}};
		      //添加图片到列表
		      $scope.add_image_entity = function(){
		    	  console.log($scope.entity.tbGoodsDesc.itemImages)
		    	  $scope.entity.tbGoodsDesc.itemImages.push($scope.image_entity);
		      }
		      //删除图片列表根据对象的下标删除
		      $scope.remove_image_entity = function(index){
		    	  $scope.entity.tbGoodsDesc.itemImages.splice(index,1);
		      }
			 $scope.checkAttributeValue= function(specName,optionName){
		    	 var items = $scope.entity.tbGoodsDesc.specificationItems;
		    	  var object =  $scope.searchObjectKey(items,'attributeName',specName);
		    	  if(object==null){
		    		  return false;
		    	  }else{
		    		 if(object.attributeValue.indexOf(optionName)>=0){
		    			 return true;
		    		 }else{
		    			 return false;
		    		 }
		    	  }
		       }
			
			 //获取一级分类
		      $scope.selectItemCatList = function(){
		    	 itemCatService.findAllByParentId(0).success(function(response){
		    		 $scope.itemCat1List = response;
		    	 }) 
		      }
		      //获取二级分类 第一个参数监控谁？？ 监控entity.tbgoods.category1Id
		      $scope.$watch("entity.tbgoods.category1Id",function(newVal,oldVal){
		    	  itemCatService.findAllByParentId(newVal).success(function(response){
			    		 $scope.itemCat2List = response;
			    	 }) 
		      })
		      //获取三级分类
		       $scope.$watch("entity.tbgoods.category2Id",function(newVal,oldVal){
		    	  itemCatService.findAllByParentId(newVal).success(function(response){
			    		 $scope.itemCat3List = response;
			    	 }) 
		      })
		      
		      //监控三级分类，获取模板Id 
		      $scope.$watch("entity.tbgoods.category3Id",function(newVal,oldVal){
		    	  itemCatService.findOne(newVal).success(function(response){
			    		 $scope.entity.tbgoods.typeTemplateId = response.typeId;//更新模板Id
			    	 }) 
		      })
		      //获取品牌列表,获取拓展属性,获取规格列表
		      //模板iD选择后更新品牌列表
		      $scope.$watch('entity.tbgoods.typeTemplateId',function(newVal,oldVal){
		    	  templateService.findOne(newVal).success(function(response){
		    		  //获取的结果是一个对象，需要把品牌的列表取出来。
		    		  $scope.typeTemplate = response;
		    		  $scope.typeTemplate.brandIds = JSON.parse(response.brandIds);
		    		  if($location.search()['id']==null){
		    		  $scope.entity.tbGoodsDesc.customAttributeItems = JSON.parse(response.customAttributeItems);
		    		  }
		    		 
		    	  })
		    	  	//获取规格列表
		      templateService.findSpecList(newVal).success(function(response){
		    	  $scope.specList = response;
		      })
		    	  
		      })
		      $scope.updateSpecAttribute = function($event,name,value){
		    	  //下面的参数:第一个:要查询的集合，第二个：查询哪个属性,第三个:关键字
		    	  var object = $scope.searchObjectKey($scope.entity.tbGoodsDesc.specificationItems,'attributeName',name);
		    	if(object!=null){
		    		if($event.target.checked){
		    			//如果没有就加进去
		    			object.attributeValue.push(value);
		    		}else{
		    			//如果有就删了
		    			object.attributeValue.splice(object.attributeValue.indexOf(value),1);
		    			//如果选项都取消了，将此条记录移除
		    			if(object.attributeValue.length==0){
		    				$scope.entity.tbGoodsDesc.specificationItems.splice($scope.entity.tbGoodsDesc.specificationItems.indexOf(object),1)
		    			}
		    		}
		    	}else{
		    		//新增
		    		$scope.entity.tbGoodsDesc.specificationItems.push({"attributeName":name,"attributeValue":[value]});
		    						
		    	}
		      }
		      //生成sku表
		      $scope.createItemList = function(){
		    	  //初始化一个列表
		    	  $scope.entity.itemsList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0'}];
		    	  //获取用户选中的规格列表
		    	  var items = $scope.entity.tbGoodsDesc.specificationItems;
		    	  //循环
		    	  for (var i = 0; i < items.length; i++) {
		    		  $scope.entity.itemsList = addColumn($scope.entity.itemsList,items[i].attributeName,items[i].attributeValue);
				}
		    	 
		    	  
		      }
		      
		      addColumn=function(list,columnName,conlumnValues){
		    	  var newList=[];
		    	  for (var i = 0; i < list.length; i++) {
		    		  var oldRow=list[i];
					for (var j = 0; j < conlumnValues.length; j++) {
					var newRow = JSON.parse(JSON.stringify(oldRow))//深克隆
					newRow.spec[columnName]=conlumnValues[j];
					newList.push(newRow);
					}
				}
		    	  return newList;
		      }
		      
	     
		});	//总括号