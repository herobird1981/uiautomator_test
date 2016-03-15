/* Provide methods to make comparison between two images. */
// isSame(Bitmap bmp1, Bitmap bmp2)
// isSame(Bitmap bmp1, Bitmap bmp2, double percent)
// isSame(String path1, String path2)
// isSame(String path1, String path2,double percent)
// isSame(String path1, String path2, int x, int y, int width, int height)
// isSame(String path1, String path2, int x, int y, int width, int height, double percent)
// getSubImage(String path, int x, int y, int width, int height)
// createBmpImage(String path)

package libs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageCompare
{
	 public static boolean isSame(Bitmap bmp1, Bitmap bmp2)
	 {
		 boolean result;
		 result = bmp1.sameAs(bmp2);
		 return result;
	 }
	 
	 public static boolean isSame(Bitmap bmp1, Bitmap bmp2, double percent) 
	 {
		 int DiffPixelsCount = 0;
		 if(bmp1.getHeight() != bmp2.getHeight())
		 {
		    return false;
		 }
		    
		 if(bmp1.getWidth() != bmp2.getWidth())
		 {
			 return false;
		 }
		    
		 if(bmp1.getConfig() != bmp2.getConfig())
		 {
		    return false;
		 }

		 int width = bmp1.getWidth();
		 int height = bmp2.getHeight();		
		       
		 for (int y = 0; y < height; y++) 
		 {
		     for (int x = 0; x < width; x++) 
		     {
		        if (bmp1.getPixel(x, y) != bmp2.getPixel(x, y)) 
		        {
		        	DiffPixelsCount ++;
		        }
		      }
		  }
		  double TotalPixelsCount = height * width;
		  double diffPercent = DiffPixelsCount / TotalPixelsCount;
		  return percent <= 1.0D - diffPercent;
	 }
	 
	 public static boolean isSame(String path1, String path2)
	 {
		 boolean result = false;
		 Bitmap bmp1 = BitmapFactory.decodeFile(path1);
		 Bitmap bmp2 = BitmapFactory.decodeFile(path2);
		 
		 result = isSame(bmp1,bmp2);
		 return result;
	 }
	
	 public static boolean isSame(String path1, String path2,double percent) throws FileNotFoundException 
	 {
		 FileInputStream fis1 = new FileInputStream(path1);
		 Bitmap bmp1 = BitmapFactory.decodeStream(fis1);
	    
		 FileInputStream fis2 = new FileInputStream(path2);
		 Bitmap bmp2 = BitmapFactory.decodeStream(fis2);
		 
//		 log("bmp1 configuration is: "+ bmp1.getConfig());
//		 log("bmp1 width is: "+ bmp1.getWidth()+ " bmp1 width is: "+ bmp1.getHeight());
//		 log("bmp2 configuration is: "+ bmp2.getConfig());
//		 log("bmp2 width is: "+ bmp2.getWidth()+ " bmp1 width is: "+ bmp2.getHeight());
		
		 return isSame(bmp1,bmp2,percent);
	 }
	 
	 public static boolean isSame(String path1, String path2, int x, int y, int width, int height) throws FileNotFoundException
	 {
		 boolean result = false;
		 
		 Bitmap sub1 = getSubImage(path1, x, y, width, height);
		 Bitmap sub2 = getSubImage(path2, x, y, width, height);
		 
		 result = isSame(sub1,sub2);
		 return result;		 
	 }
	 
	 public static boolean isSame(String path1, String path2, int x, int y, int width, int height, double percent) throws FileNotFoundException
	 {
		 boolean result = false;
		 
		 Bitmap sub1 = getSubImage(path1, x, y, width, height);
		 Bitmap sub2 = getSubImage(path2, x, y, width, height);
		 
//		 log("sub1 configuration is: "+ sub1.getConfig());
//		 log("sub1 width is: "+ sub1.getWidth()+ " sub1 width is: "+ sub1.getHeight());
//		 log("sub2 configuration is: "+ sub2.getConfig());
//		 log("sub2 width is: "+ sub2.getWidth()+ " sub2 width is: "+ sub2.getHeight());
//		 log("sub1 isRecycled: "+ sub1.isRecycled());
//		 log("sub2 isRecycled: "+ sub2.isRecycled());
		 
		 result = isSame(sub1,sub2,percent);
		 return result;
	 }
	
	 public static Bitmap getSubImage(String path, int x, int y, int width, int height) throws FileNotFoundException
	 {
		 FileInputStream fis = new FileInputStream(path);
		 Bitmap bmp = BitmapFactory.decodeStream(fis);
		 Bitmap sub = Bitmap.createBitmap(bmp, x, y, width, height);
		 return sub;
	 }
	 
	 public static Bitmap createBmpImage(String path)
	 {
		 Bitmap bmp = BitmapFactory.decodeFile(path);
		 return bmp;
	 }
	 
	 public static void log(String str)
	 {
		 System.out.println(str);
	 }
}
