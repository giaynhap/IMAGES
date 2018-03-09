
public class Algorithms {
public static int Hue(int RGB, int Degree) {
    hsl hsl = ColorConvert.rgbToHSL (RGB);
    hsl.h += Degree;
    if (hsl.h > 360) {
        hsl.h -= 360;
    }
    else if (hsl.h < 0) {
        hsl.h += 360;
    }

return ColorConvert.hslToRGB(hsl);
}

public static int Contrast(int colour, int contrast)
{
	rgb rgb = ColorConvert.deRGB(colour);
	int factor = (259 * (contrast + 255)) / (255 * (259 - contrast));
 
	int		newRed   = truncate(factor * (rgb.r   - 128) + 128);
	int		newGreen = truncate(factor * (rgb.g - 128) + 128);
	int		newBlue  = truncate(factor * (rgb.b  - 128) + 128);
	return ColorConvert.RGB(newRed, newGreen, newBlue);
}
public static int Brighrness(int colour, int contrast)
{
	rgb rgb = ColorConvert.deRGB(colour);
	 
	int		newRed   = truncate( rgb.r  +contrast);
	int		newGreen = truncate( rgb.g  +contrast);
	int		newBlue  = truncate( rgb.b  +contrast);
	return ColorConvert.RGB(newRed, newGreen, newBlue);
}
public static int GramaCorrection(int colour,float gamma)
{
 float gammaCorrection = 1.0f / gamma;
 rgb rgb = ColorConvert.deRGB(colour); 
 int newRed   = (int) (255 * Math.pow( (rgb.r   / 255.0f), gammaCorrection));
 int newGreen = (int) (255 * Math.pow(rgb.g / 255.0f, gammaCorrection));
 int newBlue  = (int) (255 * Math.pow(rgb.b  / 255.0f, gammaCorrection));
return ColorConvert.RGB(newRed, newGreen, newBlue);
}

static int truncate(int value)
{
    if(value < 0) return 0;
    if(value > 255) return 255;

    return value;
}
 

public static int ColorSelect (int rgbx,int color,int addr,int addg,int addb)
{
	 
	 hsv hsv= ColorConvert.rgbToHsv(rgbx);
	 double alpha =Math.cos((hsv.h+60*color)/180.0*Math.PI)*  hsv.v/255.0f * hsv.s/255.0f ;
	  if (color==7)
	  {
		  alpha = 1- hsv.v/255;
	  }
	  else if (color==8)
	  {
		  alpha = 1- hsv.s/255;
	  }

	  rgb rgb = ColorConvert.deRGB(rgbx);
 
	  rgb.r =(int) ( (rgb.r +addr)*alpha+ (1-alpha)*rgb.r    );
	  rgb.g =(int) ( (rgb.g +addg)*alpha+ (1-alpha)*rgb.g    );
	  rgb.b =(int) ( (rgb.b +addb)*alpha+ (1-alpha)*rgb.b    );
	  
	  
	  if ( rgb.r>255)  rgb.r= 255;
	  if ( rgb.r<0 ) rgb.r =0;
	  if ( rgb.g>255)  rgb.g= 255;
	  if ( rgb.g<0 ) rgb.g =0;
	  if ( rgb.b>255)  rgb.b= 255;
	  if ( rgb.b<0 ) rgb.b =0;
	 
	 return ColorConvert.toRGB(rgb); 
}

public static int   Screen_Effect_1(int color,int x ,int width,int height )
{
	 int posx = x%width;
	  int posy = x/height;
	  int distx = width/2 -posx;
	  int disty = height/2-posy;
	  double range = Math.sqrt(distx*distx+disty*disty);
	  float alpha = (float) (range/((width>height)?width:height));
      rgb  rgb = ColorConvert.deRGB( color);
	  
	
	  rgb=Blend.Alpha (new rgb(0, 0, 0),rgb,alpha);
	  return ColorConvert.toRGB(rgb);
}
}