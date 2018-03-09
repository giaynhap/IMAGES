import java.awt.Color;


public class ColorConvert {
	
	
	public static hsl rgbToHSL(int rgb) {
	    // strip the leading # if it's there
		hsl mhsl = new hsl();

		float	b =  (rgb & 255)/255.0f;
		float	g = ((rgb >> 8) & 255)/255.0f;
		float	r =   ((rgb >> 16) & 255)/255.0f;
		
		
	    float cMax = Math.max(r, Math.max(g, b));
	    float  cMin = Math.min(r, Math.min( g, b));
	    float delta = cMax - cMin;
	    float l = (cMax + cMin) / 2.0f;
	    float h = 0,
	        s = 0;

	    if (delta == 0) {
	        h = 0;
	    }
	    else if (cMax == r) {
	        h =  60.0f * (((g - b) / delta) % 6);
	    }
	    else if (cMax == g) {
	        h =  60.0f * (((b - r) / delta) + 2);
	    }
	    else {
	        h = 60.0f * (((r - g) / delta) + 4);
	    }

	    if (delta == 0) {
	        s = 0;
	    }
	    else {
	        s = (delta/(1-Math.abs(2*l - 1)));
	    }
	    mhsl.s =s;
	    mhsl.l=l;
	    mhsl.h=h;
	    return mhsl;
	}
	
	public static float normalize_rgb_value(float color,float m) {
	    color =  (float) Math.floor((color + m) * 255);
	    if (color < 0) {
	        color = 0;
	    }
	    return color;
	}
	
	public static int  hslToRGB(hsl hsl) {
	    float h = hsl.h,
	        s = hsl.s,
	        l = hsl.l,
	        c = (1 - Math.abs(2*l - 1)) * s,
	        x = c * ( 1 - Math.abs((h / 60 ) % 2 - 1 )),
	        m = l - c/ 2,
	        r, g, b;

	    if (h < 60) {
	        r = c;
	        g = x;
	        b = 0;
	    }
	    else if (h < 120) {
	        r = x;
	        g = c;
	        b = 0;
	    }
	    else if (h < 180) {
	        r = 0;
	        g = c;
	        b = x;
	    }
	    else if (h < 240) {
	        r = 0;
	        g = x;
	        b = c;
	    }
	    else if (h < 300) {
	        r = x;
	        g = 0;
	        b = c;
	    }
	    else {
	        r = c;
	        g = 0;
	        b = x;
	    }

	    int dr = (int)normalize_rgb_value(r, m);
	    int dg = (int)normalize_rgb_value(g, m);
	    int db = (int)normalize_rgb_value(b, m);

	    return	  ((dr&0x0ff)<<16)|((dg&0x0ff)<<8)|(db&0x0ff) ;
	}
	
	public static hsv rgbToHsv(int rgb){
		float	b =  (rgb & 255)/255.0f;
		float	g = ((rgb >> 8) & 255)/255.0f;
		float	r =   ((rgb >> 16) & 255)/255.0f;
		
	    float max = Math.max(r, Math.max(g, b));
	    float min = Math.min(r,  Math.min(g, b));
	    float h = 0, s, v = max;

	    float d = max - min;
	    s = max == 0 ? 0 : d / max;

	    if(max == min){
	        h = 0; // achromatic
	    }else{
	       if (max==r)
	       {
	            h = (g - b) / d + (g < b ? 6.0f : 0);
	       }
	       else if (max==g)
	       {
	            h =  h = (b - r) / d + 2.0f;
	       }
	       else if (max==b)
	       {
	    	   h = (r - g) / d + 4.0f;
	       }
	             
	    
	        h /= 6.0f;
	    }

	    return new hsv(h*360,s*255,v*255);
	}
	
	public static int hsvToRgb( hsv hsv)
	{
		 int rColor ;
		int i;
	    float f, p, q, t,s=(float)hsv.s/255.0f,v=(float)hsv.v/255.0f;
	    if( s == 0 ) {
	       
	       rColor = ColorConvert.RGB(v,v,v);
	        
	        return rColor;
	    }
	    float h = (((float)hsv.h)/60.0f);            // sector 0 to 5
	    i = (int)Math.floor( h );
	    f = h - i;          // factorial part of h
	    p = v * ( 1 - s );
	    q = v * ( 1 - s * f );
	    t = v * ( 1 - s * ( 1 - f ) );
	    switch( i ) {
	        case 0:
	        	 rColor =  ColorConvert.RGB(v,t,p);
	            break;
	        case 1:
	        	rColor = ColorConvert.RGB(q,v,p);
	            break;
	        case 2:
	        	rColor =  ColorConvert.RGB(p,v,t);
	            break;
	        case 3:
	        	rColor =  ColorConvert.RGB(p,q,v);
	            break;
	        case 4:
	        	rColor =  ColorConvert.RGB(t,p,v);
	            break;
	        default:        // case 5:
	        	rColor =  ColorConvert.RGB(v,p,q);
	            break;
	    }
	    return rColor ;
	}
	
	
	public static int RGB(int r,int g, int b)
	{
		return ((r&0x0ff)<<16)|((g&0x0ff)<<8)|(b&0x0ff) ;
	}
	
	public static int RGB(float r,float g, float b)
	{
		return (((int)(r*255)&0x0ff)<<16)|(((int)(g*255)&0x0ff)<<8)|((int)(b*255)&0x0ff) ;
	}
	
	public static rgb  toRGB(int r,int g, int b)
	{
		 rgb rgb = new rgb();
		 rgb.r= r;
		 rgb.g=g;
		 rgb.b=b;
		 return rgb;
	}
	public static int toRGB(rgb rgb)
	{
		return ((rgb.r&0x0ff)<<16)|((rgb.g&0x0ff)<<8)|(rgb.b&0x0ff) ;
	}
	public static rgb deRGB(int rgb)
	{
		rgb mrgb = new rgb();
		mrgb.b =  (rgb & 255);
		mrgb.g = ((rgb >> 8) & 255);
		mrgb.r =   ((rgb >> 16) & 255);
		return mrgb;
	}
}
