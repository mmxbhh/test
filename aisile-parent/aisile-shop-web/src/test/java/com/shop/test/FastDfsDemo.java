package com.shop.test;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.aisile.common.util.FastDFSClient;

public class FastDfsDemo {
	
	@Test
	public void FastdfsTest1(){
		try {
			FastDFSClient dfsClient = new FastDFSClient("E:/EclipseWorkspace/xy/aisile-parent/aisile-shop-web/src/main/resources/conf/client.conf");
			String path = dfsClient.uploadFile("c:/test2.jpg");
			System.out.println(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		@Test
		public void FastdfsTest(){
			
			try {
				//使用全局对象加载配置文件。
				ClientGlobal.init("E:/EclipseWorkspace/xy/aisile-parent/aisile-shop-web/src/main/resources/conf/client.conf");
				//创建一个TrackerClient对象
				TrackerClient trackerClient = new TrackerClient();
				//通过TrackClient获得一个TrackerServer对象
				TrackerServer trackerServer = trackerClient.getConnection();
				//创建一个StrorageServer的引用，可以是null
				StorageServer storageServer = null;
				//创建一个StorageClient，参数需要TrackerServer和StrorageServer
				StorageClient storageClient = new StorageClient(trackerServer, storageServer);
				//使用StorageClient上传文件。
				String[] strings = storageClient.upload_file("C:/test1.jpg","jpg", null);
				for (String string : strings) {
					System.out.println(string);
				}
			} catch (IOException | MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
