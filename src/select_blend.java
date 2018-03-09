import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.event.ListDataListener;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

public class select_blend {

	private JFrame frame;

	public boolean  isEdit = false;
	public select_blend(MethodData mt) {
		initialize();
		method = mt;
	   frame.setVisible(true);
	}
	public select_blend(MethodData mt,boolean isEdit,int type) {
		initialize();
		this.type = type;
		this.isEdit = isEdit;
		method =mt;
		 frame.setVisible(true);
	}

	 String [] mBlendFunction = new String[]{"SoftLight","Exclusion","Lighten","Add","Multiply","Average","Alpha","Subtract",
			"Difference","Negation","Screen","Overlay","HardLight","ColorDodge","ColorBurn"};
	 int type =0;
	 JLabel [] btnType = new JLabel[2];
	 public String function = mBlendFunction[0];
	 public int type_blend =2;
	 public JButton btSave;
	 public MethodData method;
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 300, 201);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnType[0] = new JLabel("Algorithms");
		btnType[0].setBackground(Color.PINK);
		btnType[0].setBounds(0, 0, 150, 201);
		btnType[0].setOpaque(true);
		btnType[0].setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(btnType[0]);
		btnType[1] = new JLabel("Blend");
		btnType[1].setOpaque(true);
		btnType[1] .setBackground(SystemColor.inactiveCaption);
		btnType[1] .setBounds(150, 0, 150, 201);
		btnType[1] .setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(btnType[1] );
		frame.setUndecorated(true);
		
		
		
		
		btnType[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnType[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnType[0].addMouseListener(new MouseAdapter(){
 
			@Override
			public void mousePressed(MouseEvent arg0) {
				 type =1;
				btnType[0].setVisible(false);
				btnType[1].setVisible(false);
				 CreateElm();
			}
			});
		btnType[1].addMouseListener(new MouseAdapter(){
			 
			@Override
			public void mousePressed(MouseEvent arg0) {
				 type =0;
				btnType[0].setVisible(false);
				btnType[1].setVisible(false);
				 CreateElm();
			}
			});
		
		if (this.isEdit)
		{
	 	 CreateElm();
		}
		 
		 
		  
	}
	JRadioButton [] Radbutton = new JRadioButton[3];
	JComboBox comboBox;
	private JTextField tColor;
	private JTextField tAlpha;
	void CreateElm()
	{
		comboBox = new JComboBox();
		comboBox.setBounds(30, 11, 200, 20);
		frame.getContentPane().add(comboBox);
		
		comboBox.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			function = (String) comboBox.getSelectedItem();
		
			
		}}); 
	
		DefaultComboBoxModel  data;
		if (type==0)
		{
		  data= new DefaultComboBoxModel (mBlendFunction);
		  comboBox.setModel(data);
		  ButtonGroup bG = new ButtonGroup();
		   Radbutton[0] = new JRadioButton("INV");
		   Radbutton[0].setBounds(42, 49, 109, 23);
			frame.getContentPane().add(Radbutton[0]);
			
			Radbutton[1] = new JRadioButton("Gray");
			Radbutton[1].setBounds(42, 80, 109, 23);
			frame.getContentPane().add(Radbutton[1]);
			
			Radbutton[2]= new JRadioButton("Soild");
			Radbutton[2].setBounds(42, 106, 109, 23);
			frame.getContentPane().add(Radbutton[2]);
			bG.add(Radbutton[0]);
			bG.add(Radbutton[1]);
			bG.add(Radbutton[2]);
			Radbutton[2].setSelected(true);
			
			tColor = new JTextField("#FFFFFF");
			tColor.setBounds(100, 136, 100, 20);
			frame.getContentPane().add(tColor);
			tColor.setColumns(10);
			
			
			
			Radbutton[2].addItemListener(new itemListener()); 
			Radbutton[1].addItemListener(new itemListener()); 
			Radbutton[0].addItemListener(new itemListener()); 
			
		}
		tAlpha = new JTextField("0.5");
		tAlpha.setBounds(100, 167, 50, 20);
		frame.getContentPane().add(tAlpha);
		tAlpha.setColumns(10);
		
		  btSave = new JButton("Close");
		  btSave.setBounds(200, 167, 89, 23);
	      frame.getContentPane().add(btSave);
	      btSave.addMouseListener(new MouseAdapter(){
	    	  
				@Override
				public void mousePressed(MouseEvent arg0) {
					 
				 frame.hide();
				
				}
				});
	}
	public void AllChange()
	{
		method.method = Parse.doFunction_1(function, "rgb[0,0,0],1").method;
		switch (type_blend)
		{
		case 0:
			method.type=0;
			break;
		case 1:
			method.type=-1;
			break;
		case 2:
			method.type=1;
			break;
		}
		
	}
	class itemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e) {
		
			if (e.getStateChange() == ItemEvent.SELECTED) {
				for (int i=0;i<3;i++)
			        if (e.getSource() ==Radbutton[i] )
			        {
			        	type_blend = i;
			        	if (i==2)
			        	tColor.setEnabled(true);
			        	
			        }
				AllChange();
		    }
		    else if (e.getStateChange() == ItemEvent.DESELECTED) {
		    	tColor.setEnabled(false);
		    }
		 
		}
		
	}
}
