使用了FileObserver，FileObserver是Android里的一个抽象类，继承自Object，主要用来提供文件或者文件夹的监控。



https://developer.android.google.cn/reference/android/os/FileObserver.html
详细介绍请参考上述链接

在我们监听屏幕截图中，主要使用到其中一个常量：CREATE 用来监听截图图片的创建。

现在已知截图文件夹名称为“Screenshots”

那么我们就要创建一个监听地址 /storage/emulated/0/DCIM/Screenshots

String path = Environment.getExternalStorageDirectory() 
+ File.separator + Environment.DIRECTORY_DCIM 
+ File.separator + "Screenshots" + File.separator;
截图文件夹的地址知道后，创建一个FileObserver

/** * @author yh */
public class ScreenshotsListener extends FileObserver {    
  private String filePath;    
public ScreenshotsListener(@NonNull File file) {        
    super(file);        
    this.filePath = file.getPath();        
    Log.i("ScreenshotsListener", file.getPath());    
}    
@SuppressLint("ShowToast")    
@Override    
public void onEvent(int event, @Nullable String path) {        
   Log.i("ScreenshotsListener", "event");        
      switch (event) {            
         case FileObserver.ALL_EVENTS:                
          Log.d("all", "path:" + path);                
          break;            
         case FileObserver.CREATE:               
          Log.d("Create", "path:" + filePath +"/"+ path);                
        //使用EventBus将path传到使用的地方          
         EventBus.getDefault().post(MessageWrap.getInstance( filePath +"/"+ path));               
          break;            
         default:                
           Log.d("default", "path:" + path);                
          break;        
}    
}}
在需要使用的地方调用：

ScreenshotsListener screenshotsListener = new ScreenshotsListener(new File(path))；
screenshotsListener.startWatching();
记得在onDestroy()里面

//关闭监听
screenshotsListener.stopWatching();
注：targetSdkVersion 29 在Android10的小米和pixel上测试是可以实现的，

具体就是path地址，pixel手机里的path要把DCIM修改成为picture，不同机型不同适配。

可能主流是监听媒体库，有点野路子的感觉。

勿喷，感谢

