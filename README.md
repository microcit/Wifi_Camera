# 深圳乐信 JoyHones WifiCamera 

1. 基本使用流程:   

    compile 'com.Joyhonest:Wifi_Camera:1.0.2'
    
    compile 'org.simple:androideventbus:1.0.5.1'    //Wifi_Camera 使用  androideventbus 来传递消息
    
    添加一个 JH_GLSurfaceView 。这个JH_GLSurfaceView 是显示视频的view。
  
   先依次调用
	  wifination.naSetIcType(wifination.IC_GPH64A);   //根据不同的模块，调用不同的设置参数
    wifination.naInit(“”);
    此时，屏幕上可以看到视频图像

2.  SDK 函数的具体说明：
    
    2.1	naSetIcType:(IC_TYPE)nICType;
    
    设定SDK支持的摄像头模块型号,。

    2.2 naInit(String pFileName)
    
    初始化摄像头模块并开始播放。对于不同的摄像头模块具体设置，对于GKA，可以设定为 “”或者为“2”，表示传到手机未640x360，如果设定为“1”，表示传回的的视频为720P

     2.3  naSetFlip(boolean b);     
	  			设定画面是否 旋转180度

     2.4 naSet3D(boolean b);     
 				设定3D显示
 				
 
 
    (目前只有GKA模块支持SD，对于其他模块，所有涉及SD的操作均无效）
     2.5  naGetFiles(int nType);       
	 			返回模块SDK卡中的文件列表和大小 
				
	      nType = wifination.TYPE_PHOTOS. 获取相片文件
				 nType = wifination.TYPE_VIDEOS. 获取视频文件
			
			  调用此函数后，获取的文件列表通过 

  			private static void GetFiles(byte[] filesname)   返回文件列表。

  			其中: “---start”,开始传输
  
  			“—end”，传输完毕
  
  			其他情况下，
  
  			filesname = 文件名称--- 大小.
  
   			比如： /mnt/sd_card/VIDEO/MOVI0000.mov—8989182
   
   2.6 naSnapPhoto(String pFileName,int PhoneOrSD);

		 	拍照命令
  
	     pFileName : 手机时录像文件的路径。
   
    		nType:  录像类型
		    0   	仅仅拍照到手机
		    1     仅仅拍照到SD
		    2 	  拍照到手机和SD
      

   2.7  naStartRecord(String pFileName,int PhoneOrSD);	
			   	录像命令
    
				pFileName : 手机时录像文件的路径。
 					nType:  录像类型
     		    	0   	仅仅拍照到手机
				    	1    仅仅拍照到SD
				    	2 	拍照到手机和SD
      
   2.8 naStopRecord_All()
   
		   	停止录像

2.9 int naGetThumb(String filename)

		获取SDK卡中视频文件的缩略图。（这一般是针对还没有下载到手机时调用，如果视频文件下载到手机，就不需要调用此函数来获取，通过Android系统函数就可以获取）。
      
		获取到的缩略图数据，通过 
		private static void GetThumb(byte[] data, String sFilename) 返回。
		其中，data是数据， sFilename表示是那个视频的缩略图。

4.11    int naDownloadFile(String sPath, String dPath)；
			下载SD卡中的文件到手机，其中
			sPath是SD中视频文件的全路径名称（通过 GetFlies 获取）
			dPath是储存到手机中的全路径名称
	 
			获取文件的数据，通过
			private static void DownloadFile_callback(int nPercentage, String sFileName, int nError) 返回。
					nPercentage    下载的百分比（0-100）
					sFileName		文件名称。
					nError		非0   表示下载出错。
	
4.12  naCancelGetThumb()
				终止获取视频缩略图
4.13  naCancelDownload()
			终止下载SD卡文件。
4.14   naDeleteSDFile(String full_fileName);
          删除SD卡中的名称为 full_fileName 文件。

4.15   naSentCmd(byte[] cmd, int nLen)
				发送命令自己个模块，模块再通过串口发送出去
	 

5.当模块状态改变：

	5.1 当模块状态改变时，会通过EventBus 发送@Subscriber(tag= "SDStatus_Changed")消息
			当模块的状态改变时，会返回状态个APP。
			bit0：Status_Connected,          1  已经连接到模块         0 未连接
			bit1:   LocalRecording,		1  正在录像到手机		0 未录像 
			bit2    SD_Ready,			1  SD已经准备好		0 SD已经移除
			bit3    SD_Recording,		1  正在录像到SD		0 未录像   
			bit4    SD_SNAP,			 1 正在拍照到SD		0 拍照完成

	5.2 GetFiles(byte[] dir)
			当发送 4.6  4.7  命令时，每找到一个符合条件的文件，就会通过此函数
			返回给APP
   
          
5. 注意事项：
     APP进入后台时，最好调用naStop，再进入APP时，在 naInit。	
     对于没有SD的模块，SD卡功能的函数无作用
     所有多SD卡文件操作，请放到线程中操作，以免影响UI的操控流畅
