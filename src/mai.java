import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class mai {

	private JFrame frame;
	BufferedImage img ;
	BufferedImage img2  ;
	public Parse parse= new Parse();
	JLabel btnAdd ;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mai window = new mai();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	BufferedImage buttonAdd ;
	public void resource ()
	{
		try {
			buttonAdd = ImageIO.read(this.getClass().getResourceAsStream("Addbutton.png"));
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	public mai() {
		initialize();
	}

	int numBlend = 7;
	JButton button[] = new JButton[8];
	clickEvent eventClick = new clickEvent();
	JPanel panel ;
	private void initialize() {
		try {
		 	 img =  ImageIO.read(new File("F://SomeThing//coder-wallpaper-4.jpg"));
		//	img =  ImageIO.read(new File("//media//giaynhap//Projecr_src//TestIMG.jpg"));
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		
		 try {
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			 
			e.printStackTrace();
		}
		resource ();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 999,547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
	 
	
		Paint Paint = new Paint(img.getWidth(),img.getHeight());
		
		JScrollPane scrollPane = new JScrollPane(Paint);
		scrollPane.setBounds(0, 0, 999, 363);
		frame.getContentPane().add(scrollPane,1,0);
		  
		
		
		panel = new JPanel();
	  
		panel.setLayout(null);
		
		btnAdd = new JLabel( new ImageIcon(buttonAdd));
		btnAdd.addMouseListener(eventClick);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(btnAdd);
		panel.setPreferredSize(new Dimension(315, 60));;
		
		   scrollPane_1 = new JScrollPane( panel);
		   scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		   scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		  scrollPane_1.setBounds(0, 365,  315, 132);
		  frame.getContentPane().add(scrollPane_1,1,0);
		  
		   
		 
		
		
	 
		updateButton();
	 
	}
	 
	 JScrollPane scrollPane_1;
	public void updateButton()
	{
		btnAdd.setBounds(parse.listMethod.size()*81, 0, 80, 108);
		panel.setPreferredSize(new Dimension((parse.listMethod.size()+5)*81, 60));;
	
	}
	 ArrayList<JLabel> listButton = new ArrayList<JLabel>();
	public void updateElement()
	{
		int n=0;
		for (MethodData method : parse.listMethod)
		{
			ColorPaint label = new ColorPaint(Color.cyan,80,108);
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label.addMouseListener(eventClick);
			label.setBounds(n*81, 0, 80, 108);
			panel.add(label) ;
			n++;
		}
		
	}
	
	class clickEvent implements MouseListener
	{

	 
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			 
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			 if (arg0.getSource() == btnAdd)
			 {
				parse.PaserLine("Blend.Add(rgb[255,0,255],1.0)");
				MethodData mt = parse.listMethod.get(parse.listMethod.size()-1);
				 updateButton();
				 updateElement();
				 select_blend blend = new select_blend(mt);
				 
			 }
		}
		
		
	}
	
	public void doAlgorithms (int index)
	{
		switch (index)
		{
		case 0:
			img2 = img;
			break;
		case 1:
			img2 = Blend_vintage(img);
			break;
		case 2:
			img2 = Blend_Skin_1(img);
			break;
		case 3:
			img2 = Blend_Dark_Blue(img);
			break;
		case 4:
			img2 = Blend_Instagram(img);
			break;
		case 5:
			img2 = Blend_Yellow(img);
			break;
		case 6:
			img2 = Blend_Yellow2(img);
			break;
		}
	}
	
	  class ColorPaint extends JPanel
	{
		  BufferedImage canvas  ;
		public ColorPaint(Color col,int w,int h)
		{
			canvas = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
			Graphics2D grap = canvas.createGraphics();
			JLabel lb = new JLabel(new ImageIcon(canvas));
			lb.setBounds(0, 0,w,h);
			grap.setColor(col);
			grap.fillRect (0, 0, w,h);
			this.repaint();
			this.add(lb);
		}
		
	}
	
	
	
	class Paint extends JPanel implements Runnable
	{
		
		
		
		
		
		private void LoadResource()
		{/*
			try {
			//	parse.FromFile("E:\\test.txt");
			} catch (IOException e) {
			 
				e.printStackTrace();
			}*/
	 
		}
		
		
		
		
		BufferedImage canvas ;
		Graphics2D hgrap; 
		
		public Paint(int width,int height)
		{
			 LoadResource();
			 img2 = new  BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			canvas = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			hgrap =canvas.createGraphics();
			
			JLabel lb = new JLabel (new ImageIcon(canvas));
			
			for (int i=0;i<width;i++)
			{
				for (int j=0;j<height;j++)
				{
				 
					float a =(float)i/width*360.0f;
					hsv hsl = new hsv();
					hsl.h = a;
					hsl.s =(float)j/height*255.0f;
					hsl.v = 255;
					 
					img2.setRGB(i,j, ColorConvert.hsvToRgb(hsl));
				
				}	
			}
			this.add(lb);
			new Thread(this).start();
		}
		
		 
		int a=0;
		
		public void colorDist(rgb a,rgb c)
		{
			
		}
		
	
		public  BufferedImage HUE(BufferedImage img )
		{
			 int imgW  = img.getWidth();
			 int imgH= img.getHeight();
			 a+=5;
			 int[] color = img.getRGB(0, 0, imgW, imgH, null, 0, imgW);
			 
			 float ColorSelect = 0;
			 for (int i=0;i<imgW*imgH;i++)
			 {
				 if (a>255) a=0;
 
				rgb rgb = ColorConvert.deRGB(color[i]);
				rgb =parse.Execute(rgb);
				  
				color[i]=   ColorConvert.toRGB(rgb);
			 
			 }
			 BufferedImage bufferedImage = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);
			 bufferedImage.setRGB(0, 0, imgW, imgH, color, 0, imgW);
			 
			return bufferedImage;
		}
		
		
		@Override
		public void run() {
		 while (true)
		 {
			 try {
			 
						
				// BufferedImage img2 = null ;
				// img2 = HUE(img);
				 
				 
				 hgrap.drawImage( HUE(img2),0, 0,null);
				 
				Thread.sleep(1);
				this.repaint();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
		}
		
		
	}
	public  BufferedImage Blend_vintage(BufferedImage img )
	{
		 
		 int imgW  = img.getWidth();
		 int imgH= img.getHeight();
		 int[] color = img.getRGB(0, 0, imgW, imgH, null, 0, imgW);
		 for (int i=0;i<imgW*imgH;i++)
		 {
		 
			  rgb  rgb = ColorConvert.deRGB( color[i]);
			  rgb color_2  =  ColorConvert.toRGB(174,109,190);
			  rgb color_1  =  ColorConvert.toRGB(109,139,190);
			  rgb color_0  = ColorConvert.toRGB(191,169,111);
			  int gray = (int)((rgb.r+5*rgb.g+2*rgb.b)/8.0);
			  rgb black  = ColorConvert.toRGB(gray,gray,gray);
			  rgb = Blend.SoftLight( black,rgb,1.0f);
			  rgb = Blend.SoftLight(black ,rgb,1.0f);
			  rgb = Blend.SoftLight(color_0,rgb,0.45f);
			  rgb = Blend.Exclusion(color_1,rgb ,0.32f);
		 	  rgb = Blend.Lighten(color_2,rgb,0.1f);
			  color[i]= ColorConvert.toRGB(rgb);
			  color[i] = Algorithms.Contrast(color[i],20);
		 }
		 BufferedImage bufferedImage = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);
		 bufferedImage.setRGB(0, 0, imgW, imgH, color, 0, imgW);
		 
		return bufferedImage;
		
	}

	public  BufferedImage Blend_Skin_1(BufferedImage img )
	{
		 
		 int imgW  = img.getWidth();
		 int imgH= img.getHeight();
		 int[] color = img.getRGB(0, 0, imgW, imgH, null, 0, imgW);
		 for (int i=0;i<imgW*imgH;i++)
		 {
			// color[i] = Algorithm_Hue( color[i],a);
			 color[i] = Algorithms.Brighrness(color[i],20);
			  color[i]= Algorithms.Contrast(color[i], 100);
			  rgb  rgb = ColorConvert.deRGB( color[i]);
			 
			  rgb color_0  = ColorConvert.toRGB(255,0,0);
			  int gray = (int)((rgb.r+5*rgb.g+2*rgb.b)/8.0);
			  
			  rgb black  = ColorConvert.toRGB(gray,gray,gray);
			  
			//  rgb = Blend.Add( color_0,rgb,0.2f);
			  
			//  rgb = Blend.Add(color_0 ,rgb,1.0f);
			//  rgb = Blend.Multiply (Blend.RED,rgb,0.2f);
			//  rgb = Blend.SoftLight(black,rgb,0.45f);
			 
			  rgb = Blend.Add (Blend.CYAN,rgb,0.07f);
			  rgb = Blend.Add (Blend.MEGENTA,rgb,0.07f);
			  rgb = Blend.Add (Blend.BLUE,rgb,0.07f);
			
			//  rgb = Blend.Exclusion(color_1,rgb ,0.32f);
			  rgb = Blend.Alpha (ColorConvert.deRGB( color[i]),rgb,0.5f);		 
			  
			  color[i]= ColorConvert.toRGB(rgb);
			  color[i]= Algorithms.GramaCorrection(color[i], 1.5f);
			
			  
		 }
		 BufferedImage bufferedImage = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);
		 bufferedImage.setRGB(0, 0, imgW, imgH, color, 0, imgW);
		 
		return bufferedImage;
		
	}
	
	public  BufferedImage Blend_Dark_Blue(BufferedImage img )
	{
		 int imgW  = img.getWidth();
		 int imgH= img.getHeight();
		 int[] color = img.getRGB(0, 0, imgW, imgH, null, 0, imgW);
		 for (int i=0;i<imgW*imgH;i++)
		 {
			// color[i] = Algorithm_Hue( color[i],a);
			 // color[i] = Algorithms.Brighrness(color[i],20);
			 rgb color_1 = new rgb(255,228,0);
			 color[i]= Algorithms.Contrast(color[i], 100);
			 rgb  rgb = ColorConvert.deRGB( color[i]);
			 rgb = Blend.Add(Blend.BLUE, rgb, 0.1f);
			 rgb = Blend.Multiply (color_1, rgb, 0.15f);
			 
			 rgb = Blend.Multiply(Blend.RED, rgb, 0.08f);
			 rgb = Blend.Multiply(Blend.BLUE, rgb, 0.07f);
			 
			 rgb = Blend.Multiply(Blend.CYAN, rgb, 0.07f);
			 rgb = Blend.Multiply(Blend.GREEN, rgb, 0.2f);
			 rgb = Blend.Multiply(Blend.BLUE, rgb, 0.15f);
			 rgb = Blend.Exclusion(Blend.RED, rgb, 0.08f);
			 int gray = (int)((rgb.r+5*rgb.g+2*rgb.b)/8.0);
			 rgb black  = ColorConvert.toRGB(gray,gray,gray);
			 rgb = Blend.SoftLight (black, rgb, 0.6f);
			 color[i]= ColorConvert.toRGB(rgb);
			 color[i]= Algorithms.GramaCorrection(color[i], 1.5f);
			
			  
		 }
		 BufferedImage bufferedImage = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);
		 bufferedImage.setRGB(0, 0, imgW, imgH, color, 0, imgW);
		 
		return bufferedImage;
	}
	
	public  BufferedImage Blend_Instagram(BufferedImage img )
	{
		 int imgW  = img.getWidth();
		 int imgH= img.getHeight();
		 int[] color = img.getRGB(0, 0, imgW, imgH, null, 0, imgW);
		 for (int i=0;i<imgW*imgH;i++)
		 {
			// color[i] = Algorithm_Hue( color[i],a);
			 // color[i] = Algorithms.Brighrness(color[i],20);
			 rgb color_1 = new rgb(255,228,0);
			  rgb color_0  = ColorConvert.toRGB(191,169,111);
			 rgb  rgb = ColorConvert.deRGB( color[i]);
			 rgb =  Blend.Exclusion(Blend.BLUE, rgb, 0.4f);
			 rgb =  Blend.Lighten (Blend.YELLOW, rgb, 0.2f);
			 
			 rgb = Blend.SoftLight(color_0,rgb,0.45f);
			 color[i]= ColorConvert.toRGB(rgb);
			 color[i]= Algorithms.Contrast(color[i],120);
			 color[i]= Algorithms.Brighrness(color[i],50);
			 color[i]= Algorithms.Hue( color[i], -10);
		 }
		 BufferedImage bufferedImage = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);
		 bufferedImage.setRGB(0, 0, imgW, imgH, color, 0, imgW);
		 
		return bufferedImage;
	}
	
	 /*0 RED
	  *1 Yellow
	  *2 Green
	  *3 MEGENTA 
	  *4 BLUE
	  *5 T
	  */
	
	
	public  BufferedImage Blend_Yellow(BufferedImage img )
	{
		 int imgW  = img.getWidth();
		 int imgH= img.getHeight();
	 
		 int[] color = img.getRGB(0, 0, imgW, imgH, null, 0, imgW);
		 
		 float ColorSelect = 0;
		 for (int i=0;i<imgW*imgH;i++)
		 {
			 
			 
			 color[i]=Algorithms.ColorSelect(color[i],5,9+15,-30,-30);
			 hsv hsv= ColorConvert.rgbToHsv(color[i]);
			 hsv.v+=10;
			  if (hsv.v>255) hsv.v=255;
				
			 color[i]= ColorConvert.hsvToRgb(hsv);
			 color[i]=Algorithms.ColorSelect(color[i],4,-52,-10+9-52,+9);
			 color[i]=Algorithms.ColorSelect(color[i],7,10,10,90);
			 hsv= ColorConvert.rgbToHsv(color[i]);
			 hsv.v+=30;
			  if (hsv.v>255) hsv.v=255;
		 
			  rgb  rgb = ColorConvert.deRGB( color[i]);
			  
			
			  rgb = Blend.Add(Blend.RED,rgb,0.2f);
			  
			  rgb = Blend.SoftLight (Blend.BLUE,rgb,0.2f);
			  rgb = Blend.SoftLight(Blend.YELLOW,rgb,0.6f);
			  
			  color[i]= ColorConvert.toRGB(rgb);
			  color[i]=Algorithms.Brighrness(color[i], -20);
		 }
		 BufferedImage bufferedImage = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);
		 bufferedImage.setRGB(0, 0, imgW, imgH, color, 0, imgW);
		 
		return bufferedImage;
	}
	public  BufferedImage Blend_Yellow2(BufferedImage img )
	{
		 int imgW  = img.getWidth();
		 int imgH= img.getHeight();
		 
		 int[] color = img.getRGB(0, 0, imgW, imgH, null, 0, imgW);
		 
		 float ColorSelect = 0;
		 for (int i=0;i<imgW*imgH;i++)
		 {
			 
			 
			 rgb  rgb = ColorConvert.deRGB( color[i]);
			 int  color2=Algorithms.ColorSelect(color[i],4,	(int)((51.0)/100.0*rgb.r) ,(int)((51.0-38)/100.0*rgb.g) ,(int)((-38.0 )/100.0*rgb.b)+200  );
			 color2=Algorithms.ColorSelect(color2,3, 0,-60,-40  ); 
			 rgb = ColorConvert.deRGB( color[i]);
			 
			 rgb rgb2 = ColorConvert.deRGB(color2);
			  
			  rgb.b=255-rgb.b;
			  
			  rgb = Blend.SoftLight (rgb,rgb2, 0.75f);
			
			  
			 
			  color[i]= ColorConvert.toRGB(rgb);
			  hsv hsv = ColorConvert.rgbToHsv(color[i]);
			  hsv.h = 18;
			  color[i]= Algorithms.ColorSelect(ColorConvert.hsvToRgb(hsv), 7, 20, 0,0);
			  color[i]= Algorithms.ColorSelect(ColorConvert.hsvToRgb(hsv), 0,40,0,0);
			  
			  color[i]=Algorithms.Screen_Effect_1( color[i],i ,imgW,imgH);
			  
			 
			  color[i]= Algorithms.Contrast(color[i], 100);
			  color[i]= Algorithms.Brighrness(color[i], 60);
			    
		 }
		 BufferedImage bufferedImage = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);
		 bufferedImage.setRGB(0, 0, imgW, imgH, color, 0, imgW);
		 
		return bufferedImage;
	}
}
