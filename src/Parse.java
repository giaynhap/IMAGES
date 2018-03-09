import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
public class Parse {
public ArrayList<MethodData> listMethod = new ArrayList<MethodData>(); 	;
	
	
 static String [] mBlendFunction = new String[]{"SoftLight","Exclusion","Lighten","Add","Multiply","Average","Alpha","Subtract",
		"Difference","Negation","Screen","Overlay","HardLight","ColorDodge","ColorBurn"};

 
 public void FromFile(String file) throws IOException  
 {
	File f = new File(file);
	if (! f.exists()) return;
	BufferedReader br = new BufferedReader(new FileReader(f));
	String line = br.readLine();
	while (line !=null)
	{
		PaserLine(line);
		line = br.readLine();
	}
	System.out.println(listMethod.size());
 }
 public     rgb Execute(rgb img)
 {
	 rgb rgb = new rgb();
	 
	 if (listMethod.size()<1) return img;
	 try {
		 
		 for (int index=0;index< listMethod.size();index++  )
		 {
			 MethodData mt =listMethod.get(index); 
			 if (mt.type==1)
			 {
				
			 rgb = (rgb) mt.method.invoke(new Blend(), mt.arg.get(0),img, mt.arg.get(1)   );
			
			 }
			 else  if (mt.type==2)
			 {
				
				 int a = (int) mt.method.invoke(new Algorithms(),ColorConvert.toRGB(img), mt.arg.get(0)   );
				 rgb = ColorConvert.deRGB(a);
			 }
			 else if (mt.type==3)
			 {
				 int a = (int) mt.method.invoke(new Algorithms(),ColorConvert.toRGB(img), mt.arg.get(0) , mt.arg.get(1), mt.arg.get(2), mt.arg.get(3)   );
				 rgb  =ColorConvert.deRGB(a);
			 }
			 else  if (mt.type==0)
			 {
				 
				 	rgb newRgb = new rgb(255-img.r,255-img.g,255-img.b);
					 rgb = (rgb) mt.method.invoke(new Blend(),newRgb,img, mt.arg.get(1)   );
			 } else  if (mt.type==-1)
			 {
				 	rgb newRgb = new rgb( );
				 	newRgb.r = (img.r+img.g+rgb.b)/3;
					newRgb.g=	newRgb.b=newRgb.r;
				 
					 rgb = (rgb) mt.method.invoke(new Blend(), newRgb,img, mt.arg.get(1)   );
			 }
			 else   {
				 	rgb newRgb = new rgb(rgb.r,rgb.g,rgb.b );
					rgb = (rgb) mt.method.invoke(new Blend(), newRgb,img, mt.arg.get(1)   );
			 }
			 img = rgb;
		 }
		 
	} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		e.printStackTrace() ;
		 return img;
	}
	 
	 return rgb;
 }
 public  void PaserLine(String Line )
{
 String pattern = "(.*?)\\.(.*?)\\((.*?)\\)";
 Pattern p = Pattern.compile(pattern);
 Matcher m = p.matcher(Line);
 
 MethodData data= null;
 if (m.find())
 {
	 int isClass = getClass(m.group(1));
	 switch (isClass)
	 {
	 case 0: 
		 data =  doFunction_1(m.group(2),m.group(3)); break;
	 case 1:
		   try {
			   data =  doFunction2(m.group(2),m.group(3)) ;
		} catch (NoSuchMethodException | SecurityException e) {
			 
			e.printStackTrace();
		} break;
 
	 }
 }
 if (data!=null)
	 this.listMethod.add(data);
 
}

private  int getClass(String classname)
{
	 if (classname.equals("Algorithms"))
	 {
		 return 1;
	 }else return 0;
	
}
public static MethodData doFunction_1(String function,String value)
{
	MethodData data = null;
	int id = -1;
	for (int i=0;i<mBlendFunction.length;i++)
	{
		if (function.equals(mBlendFunction[i]) )
		{
			id=i;
			break;
		}
	}
	
	if (id<0) return data;
	
	String patternValue = "(.*?)\\[(.*?)\\]\\,(.*?)($|\\n)";
	Pattern p = Pattern.compile(patternValue);
	Matcher m = p.matcher(value);
	 
	if (!m.find()) return data;
	
	String type = m.group(1);
	float mAlpha = Float.parseFloat(m.group(3));

	if (type.equals("rgb"))
	{
		String [] val = m.group(2).split(",");
		int r = Integer.parseInt(val[0]);
		int g = Integer.parseInt(val[1]);
		int b = Integer.parseInt(val[2]);
		 data = functionBlenCall(id,new rgb(r,g,b),mAlpha,1);
	}
	else if (type.equals("image"))
	{
		rgb newImg = new rgb() ;
		String val = m.group(2);
		if (val.equals("inv"))
		{
			
			  functionBlenCall(id,new rgb(),mAlpha,0);
		}
		else if (val.equals("gray")) 
		{
			  functionBlenCall(id,new rgb(),mAlpha,-1);
			 
		}
		else
		{
			  functionBlenCall(id,new rgb(),mAlpha,-2);
		}
		 
	}
	 
	
	return  data ;
}
public static MethodData  doFunction2(String function,String value ) throws NoSuchMethodException, SecurityException
{
	MethodData method = null;
	if (function.equals("Hue")||function.equals("Contrast")||function.equals("Brighrness"))
	{
		int degree = Integer.parseInt(value);
		 
		MethodData mt = new MethodData();
	
			Class[] cArg = new Class[2];
			cArg[0] = int.class;
	        cArg[1] = int.class;
			mt.method = Algorithms.class.getMethod(function, cArg);
			mt.arg.add(degree);
			mt.type =2;
			method =mt;
		
	}	
	 
	else if  (function.equals("GramaCorrection"))
	{
			float val = Float.parseFloat(value);
			MethodData mt = new MethodData();
			Class[] cArg = new Class[2];
			cArg[0] = int.class;
	        cArg[1] = float.class;
			mt.method = Algorithms.class.getMethod(function, cArg);
			mt.arg.add(val);
			mt.type =2;
			method =mt;
		
	}	
	
	else if  (function.equals("ColorSelect"))
	{
		String [] val =value.split(",");
		int type = Integer.parseInt(val[0]);
		int addr = Integer.parseInt(val[1]);
		int addg = Integer.parseInt(val[2]);
		int addb = Integer.parseInt(val[3]);
		//return ColorConvert.deRGB(Algorithms.ColorSelect(ColorConvert.toRGB(img), type,addr,addg,addb));
		
		MethodData mt = new MethodData();
		Class[] cArg = new Class[5];
		cArg[0] = int.class;
        cArg[1] = int.class;
        cArg[2] = int.class;
        cArg[3] = int.class;
        cArg[4] = int.class;
        
		mt.method = Algorithms.class.getMethod(function, cArg);
		mt.arg.add(type);
		mt.arg.add(addr);
		mt.arg.add(addg);
		mt.arg.add(addb);
		mt.type =3;
		method =mt;
		
	}	
	 
	 return method;
}
private static MethodData functionBlenCall(int func,rgb value1,float value2,int type)
{
	
	MethodData mt = null;new MethodData();
	try {
		mt =  new MethodData();
		 Class[] cArg = new Class[3];
         cArg[0] = rgb.class;
         cArg[1] = rgb.class;
         cArg[2] = Float.class;
		mt.method = Blend.class.getMethod(mBlendFunction[func], cArg);
		mt.arg.add(value1);
		mt.arg.add(value2);
		mt.type =type;
		
		
	} catch (NoSuchMethodException | SecurityException e) {
		 
		e.printStackTrace();
	}
	return mt;
	/*
	switch (func+1)
	{
		case 1: return Blend.SoftLight(value1, img, value2);
		case 2: return Blend.Exclusion(value1, img, value2);
		case 3: return Blend.Lighten(value1, img, value2);
		case 4: return Blend.Add(value1, img, value2);
		case 5: return Blend.Multiply(value1, img, value2);
		case 6: return Blend.Average(value1, img, value2);
		case 7: return Blend.Alpha(value1, img, value2);
		case 8: return Blend.Subtract(value1, img, value2);
		case 9: return Blend.Difference(value1, img, value2);
		case 10: return Blend.Negation(value1, img, value2);
		case 11: return Blend.Screen(value1, img, value2);
		case 12: return Blend.Overlay(value1, img, value2);
		case 13: return Blend.HardLight(value1, img, value2);
		case 14: return Blend.ColorDodge(value1, img, value2);
		case 15: return Blend.ColorBurn(value1, img, value2);
		default: return Blend.Alpha(value1, img, value2);  
	}
	*/
 
}
}
