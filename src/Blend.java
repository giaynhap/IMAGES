import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Blend {
	
	public static rgb RED = new  rgb(255,0,0);
	public static rgb CYAN = new  rgb(0,255,255);
	public static rgb GREEN = new  rgb(0,255,0);
	public static rgb MEGENTA = new  rgb(255,0,255);
	public static rgb BLUE = new  rgb(0,0,255);
	public static rgb YELLOW = new  rgb(255,255,0);
   static int TaskBlend(int A,int B,String function )
{
	Method method;
	try {
		method = ColorBlend.class.getDeclaredMethod(function);
		 return (int )method.invoke(A,B);
	} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	 
		return 0;
	}
}

public static rgb SoftLight(rgb A,rgb B,Float Alpha)
{
	// System.out.println(B);
	rgb nRgb = new rgb();
	 
	nRgb.r = (int)((ColorBlend.SoftLight(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.SoftLight(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.SoftLight(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
 
	return  nRgb ;
}
public static rgb Exclusion(rgb A,rgb B,Float Alpha)
{

	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Exclusion(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Exclusion(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Exclusion(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));

	return   nRgb ;
}
public static rgb Lighten(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Exclusion(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Exclusion(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Exclusion(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
public static rgb Add(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Add(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Add(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Add(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
public static rgb Multiply(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Multiply(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Multiply(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Multiply(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
public static rgb Average(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Average(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Average(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Average(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
public static rgb Alpha(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)(A.r*Alpha+(1.0f - Alpha)*B.r);
	nRgb.g = (int)(A.g*Alpha+(1.0f - Alpha)*B.g);
	nRgb.b = (int)(A.b*Alpha+(1.0f - Alpha)*B.b);
	return   nRgb ;
}
public static rgb Subtract(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Subtract(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Subtract(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Subtract(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
public static rgb Difference(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Difference(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Difference(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Difference(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
public static rgb Negation(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Negation(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Negation(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Negation(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
public static rgb Screen(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Screen(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Screen(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Screen(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
public static rgb Overlay(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.Overlay(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.Overlay(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.Overlay(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
public static rgb HardLight(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.HardLight(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.HardLight(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.HardLight(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}

public static rgb ColorDodge(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.ColorDodge(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.ColorDodge(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.ColorDodge(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}

public static rgb ColorBurn(rgb A,rgb B,Float Alpha)
{
	rgb nRgb = new rgb();
	nRgb.r = (int)((ColorBlend.ColorBurn(A.r,B.r )*Alpha+(1.0f - Alpha)*B.r));
	nRgb.g = (int)((ColorBlend.ColorBurn(A.g,B.g )*Alpha+(1.0f - Alpha)*B.g));
	nRgb.b = (int)((ColorBlend.ColorBurn(A.b,B.b )*Alpha+(1.0f - Alpha)*B.b));
	return   nRgb ;
}
}
