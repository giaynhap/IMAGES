
public class ColorBlend {
	
	public static int Normal(int A,int B)
	{
		return A;
	}
	public static int Lighten(int A,int B)
	{
		return (int)((B > A) ? B:A);
	}
	public static int Darken(int A,int B)
	{
		return (int)((B > A) ? A:B);
	}
	public static int Multiply(int A,int B)
	{
		return (int)((A * B) / 255.0);
	}
	public static int Average(int A,int B)
	{
		return (int)((A + B) / 2.0);
	}
	public static int Add(int A,int B)
	{
		return Math.min(255, (A + B));
	}
	public static int Subtract(int A,int B)
	{
		return (int)((A + B < 255) ? 0:(A + B - 255));
	}
	public static int Difference(int A,int B)
	{
		return Math.abs(A-B);
	}
	public static int  SoftLight(int A,int B)
	{  
		return ((int)((B < 128)?(2*((A>>1)+64))*((float)B/255.0):(255-(2*(255.0-((A>>1)+64))*(float)(255.0-B)/255.0))));
	}
	public static int Negation(int A,int B)
	{
		return (int)(255 - Math.abs(255 - A - B));
	}
	public static int Screen(int A,int B)
	{
		return (int)(255 - (((255 - A) * (255 - B)) >> 8));
	}
	public static int Exclusion(int A,int B)
	{
		return (int)(  A + B - 2.0 * A * B / 255.0 );
	}
	public static int Overlay(int A,int B)
	{
		return (int)( (B < 128) ? (2 * A * B / 255):(255 - 2 * (255 - A) * (255 - B) / 255) );
	}
	public static int HardLight(int A,int B)
	{
		return Overlay(B,A);
	}
	
	public static int ColorDodge(int A,int B)
	{
		return (int)((B == 255) ? B:Math.min(255, ((A << 8 ) / (255 - B))));
	}
	
	public static int ColorBurn(int A,int B)
	{
		return (int)((B == 0) ? B:Math.max(0, (255 - ((255 - A) << 8 ) / B)));
	}
	public static int LinearDodge(int A,int B)
	{
		return (int)((B == 0) ? B:Math.max(0, (255 - ((255 - A) << 8 ) / B)));
	}
	
	/*
 
#define ChannelBlend_LinearDodge(A,B)(ChannelBlend_Add(A,B))
#define ChannelBlend_LinearBurn(A,B) (ChannelBlend_Subtract(A,B))
#define ChannelBlend_LinearLight(A,B)((uint8)(B < 128)?ChannelBlend_LinearBurn(A,(2 * B)):ChannelBlend_LinearDodge(A,(2 * (B - 128))))
#define ChannelBlend_VividLight(A,B) ((uint8)(B < 128)?ChannelBlend_ColorBurn(A,(2 * B)):ChannelBlend_ColorDodge(A,(2 * (B - 128))))
#define ChannelBlend_PinLight(A,B)   ((uint8)(B < 128)?ChannelBlend_Darken(A,(2 * B)):ChannelBlend_Lighten(A,(2 * (B - 128))))
#define ChannelBlend_HardMix(A,B)    ((uint8)((ChannelBlend_VividLight(A,B) < 128) ? 0:255))
#define ChannelBlend_Reflect(A,B)    ((uint8)((B == 255) ? B:min(255, (A * A / (255 - B)))))
#define ChannelBlend_Glow(A,B)       (ChannelBlend_Reflect(B,A))
#define ChannelBlend_Phoenix(A,B)    ((uint8)(min(A,B) - max(A,B) + 255))
#define ChannelBlend_Alpha(A,B,O)    ((uint8)(O * A + (1 - O) * B))
#define ChannelBlend_AlphaF(A,B,F,O) (ChannelBlend_Alpha(F(A,B),A,O))
	*/
	
	
}
