
package sc.demo.androidcarmera;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity
{
	String AppDir = "/sdcard/Carmera/";
	final int Requset_Photo = 3000;
	final int Requset_Vedio = 3001;
	
	String path = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 创建目录
		File dir = new File(AppDir);
		if (!dir.exists()) dir.mkdirs();
	}
	
	public void TakePhoto(View view)
	{
		// 调用系统的照相机进行拍照
		Intent intent = new Intent();
		intent.setAction("android.media.action.IMAGE_CAPTURE");
		intent.addCategory("android.intent.category.DEFAULT");
		
		// 保存照片到指定的路径
		path = AppDir + NewName() + ".jpg";
		File file = new File(path);
		
		Uri uri = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		
		startActivityForResult(intent, Requset_Photo);
	}
	
	public void TakeVedio(View view)
	{
		// 调用系统的照相机进行录像
		Intent intent = new Intent();
		intent.setAction("android.media.action.VIDEO_CAPTURE");
		intent.addCategory("android.intent.category.DEFAULT");
		
		// 保存录像到指定的路径
		path = AppDir + NewName() + ".mp4";
		File file = new File(path);
		
		Uri uri = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		
		startActivityForResult(intent, Requset_Vedio);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_OK)
		{
			if (requestCode == Requset_Photo)
			{
				Toast.makeText(this, "拍照完成" + "\n" + path, Toast.LENGTH_SHORT).show();
				
			}
			else if (requestCode == Requset_Vedio)
			{
				Toast.makeText(this, "录像完成" + "\n" + path, Toast.LENGTH_SHORT).show();
			}
		}
		else Toast.makeText(this, "已取消操作", Toast.LENGTH_SHORT).show();
		
	}
	
	// ---------------
	
	private static int count = 0;
	private static String preTime = "";
	
	/** 生成新的名称 */
	public static String NewName()
	{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(new Date());
		
		DateFormat formatter2 = new SimpleDateFormat("HH.mm.ss");
		String time = formatter2.format(new Date());
		
		if (!preTime.equals(time))
		{
			count = 1;
			preTime = time;
		}
		else count++;
		
		String fileName = date + "_" + time + "_" + count;
		return fileName;
	}
	
}
