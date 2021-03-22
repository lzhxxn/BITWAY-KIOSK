package five.gui.project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.List;
import java.io.*;
import java.net.*;



class Kiosk extends JFrame implements ActionListener{
	Socket s;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	FileInputStream fis; //Node 
	OutputStream os; //Node 
	BufferedInputStream bis; //Filter
	BufferedOutputStream bos; //Filter 

	String fname = "C:/Users/achoa/Desktop/Project/Kiosk/주문내역.txt";

	Container cp;
	JPanel pN, pS;
	JPanel pC1, pC2, pC3, pC4, pC5, pC6; // 가운데 패널
	JPanel pSp, pSpp, pSpN, pNp;
	JButton c1,c2,c3,c4,c5; //카테고리
	JButton menuList1[], menuList2[], menuList3[], menuList4[], menuList5[];  //메뉴리스트(카테고리별)
	JButton resetBtn, orderBtn;
	ImageIcon bread_1, bread_2,  bread_3, bread_4, bread_5, bread_6, bread_7, bread_8, bread_9;
	ImageIcon salad_1, salad_2, salad_3, salad_4, salad_5, salad_6, salad_7, salad_8, salad_9;
	ImageIcon wrap_1, wrap_2, wrap_3, wrap_4, wrap_5, wrap_6, wrap_7, wrap_8, wrap_9;
	ImageIcon side_1, side_2, side_3, side_4, side_5, side_6, side_7, side_8, side_9;
	ImageIcon another_1, another_2, another_3, another_4, another_5, another_6, another_7, another_8, another_9; 
	ImageIcon left, right, logo;
	JTable tb;
	JScrollPane js;
	DefaultTableModel model;
	JTable total;
	JScrollPane js2;
	DefaultTableModel model2;
	String[] ordering = {"메뉴", "수랑", "가격"};
	String[][] orderdata = {};
	String[] totaling = {"합계"};
	String[][] totaldata = {};
	JTextField jf;

	void init(){
		
		loadImg();
		setPanel();
		setUI();
	}

	void setPanel(){
	 //jlb = new JLabel(logo);
	pN = new JPanel(){
	  public void paint(Graphics g){
		 g.drawImage(logo.getImage(),0,0,null);
		 setOpaque(false);
		 super.paint(g);
	  }
	 };
	pC1 = new JPanel();
	pC2 = new JPanel();
	pC3 = new JPanel();
	pC4 = new JPanel();
	pC5 = new JPanel();
	pC6 = new JPanel();
	pS = new JPanel();
	pSp = new JPanel();
	pNp = new JPanel();
	pSpp = new JPanel();
	pSpN = new JPanel();
	c1 = new JButton("샌드위치");
	c2 = new JButton("샐러드");
	c3 = new JButton("랩");
	c4 = new JButton("사이드");
	c5 = new JButton("기타");   
	resetBtn = new JButton("비우기");
	orderBtn = new JButton("주문하기");
	jf = new JTextField(33);
	model = new DefaultTableModel(orderdata, ordering);
	tb = new JTable(model);
	js = new JScrollPane(tb);

	c1.setBackground(Color.WHITE);
	c1.setBorderPainted(false);
	c2.setBackground(Color.WHITE);
	c2.setBorderPainted(false);
	c3.setBackground(Color.WHITE);
	c3.setBorderPainted(false);
	c4.setBackground(Color.WHITE);
	c4.setBorderPainted(false);
	c5.setBackground(Color.WHITE);
	c5.setBorderPainted(false);
	resetBtn.setBackground(Color.WHITE);
	orderBtn.setBackground(Color.WHITE);
      

      //pC1
      menuList1 = new JButton[9];
      for(int i = 0; i < menuList1.length; i++){
         menuList1[0]  = new JButton("베지", bread_1);
         menuList1[1]  = new JButton("서브웨이 멜트", bread_2);
         menuList1[2]  = new JButton("치킨 데리야끼", bread_3);
         menuList1[3]  = new JButton("로티세리 바비큐 치킨", bread_4);
         menuList1[4]  = new JButton("쉬림프", bread_5);
         menuList1[5]  = new JButton("풀드포크", bread_6);
         menuList1[6]  = new JButton("에그마요", bread_7);
         menuList1[7]  = new JButton("햄", bread_8);
         menuList1[8]  = new JButton("참치", bread_9);
      }

      //pC2
      menuList2 = new JButton[9];
      for(int i = 0; i < menuList2.length; i++){
      menuList2[0]  = new JButton("로티세리 치킨", salad_1);
      menuList2[1]  = new JButton("풀드포크", salad_2);
      menuList2[2]  = new JButton("이탈리안 비엠티", salad_3);
      menuList2[3]  = new JButton("비엘티", salad_4);
      menuList2[4]  = new JButton("미트볼", salad_5);
      menuList2[5]  = new JButton("햄", salad_6);
      menuList2[6]  = new JButton("참치", salad_7);
      menuList2[7]  = new JButton("에그마요", salad_8);
      menuList2[8]  = new JButton("로스트 치킨", salad_9);
      }
      for(int i = 0; i < menuList2.length; i++){
         pC2.add(menuList2[i]);
       menuList2[i].setBorderPainted(false);
      }

      //pC3
      menuList3 = new JButton[9];
      for(int i = 0; i < menuList3.length; i++){
      menuList3[0]  = new JButton("스테이크, 에그 & 치즈 랩", wrap_1);
      menuList3[1]  = new JButton("이탈리안 미니 랩", wrap_2);
      menuList3[2]  = new JButton("스테이크 & 치즈 아보카도 랩", wrap_3);
      menuList3[3]  = new JButton("웨스턴, 에그 & 치즈 랩", wrap_4);
      menuList3[4]  = new JButton("햄, 에그 & 치즈 랩", wrap_5);
      menuList3[5]  = new JButton("베이컨, 에그 & 치즈 랩", wrap_6);
      menuList3[6]  = new JButton("쉬림프 에그마요 랩", wrap_7);
      menuList3[7]  = new JButton("치킨 베이컨 미니 랩", wrap_8);
      menuList3[8]  = new JButton("치킨 베이컨 미니 랩", wrap_9);
      }
      for(int i = 0; i < menuList3.length; i++){
         pC3.add(menuList3[i]);
       menuList3[i].setBorderPainted(false);
      }
      //pC4
      menuList4 = new JButton[9];
      for(int i = 0; i < menuList4.length; i++){
        menuList4[0]  = new JButton("치즈 갈릭 브레드", side_1);
      menuList4[1]  = new JButton("머쉬룸 수프", side_2);
      menuList4[2]  = new JButton("이탈리안 플랫 피자", side_3);
      menuList4[3]  = new JButton("치즈 플랫 피자", side_4);
      menuList4[4]  = new JButton("이탈리안 미니 랩", side_5);
      menuList4[5]  = new JButton("우유", side_6);
      menuList4[6]  = new JButton("웨지 포테이토", side_7);
      menuList4[7]  = new JButton("Cheesy 웨지 포테이토", side_8);
      menuList4[8]  = new JButton("치킨 베이컨 미니 랩", side_9);
      }
      for(int i = 0; i < menuList4.length; i++){
         pC4.add(menuList4[i]);
       menuList4[i].setBorderPainted(false);
      }
      //pC5
      menuList5 = new JButton[9];
      for(int i = 0; i < menuList5.length; i++){
        menuList5[0]  = new JButton("기타1", another_1);
      menuList5[1]  = new JButton("기타2", another_2);
      menuList5[2]  = new JButton("기타3", another_3);
      menuList5[3]  = new JButton("기타4", another_4);
      menuList5[4]  = new JButton("기타5", another_5);
      menuList5[5]  = new JButton("기타6", another_6);
      menuList5[6]  = new JButton("기타7", another_7);
      menuList5[7]  = new JButton("기타8", another_8);
      menuList5[8]  = new JButton("기타9", another_9);
      }
      for(int i = 0; i < menuList3.length; i++){
         pC5.add(menuList5[i]);
       menuList5[i].setBorderPainted(false);
      }

            
      //버튼 크기 조절
      js.setPreferredSize(new Dimension(470, 140));   
      resetBtn.setPreferredSize(new Dimension(94, 70));
      orderBtn.setPreferredSize(new Dimension(94, 70));
     c1.setPreferredSize(new Dimension(90, 30));
     c2.setPreferredSize(new Dimension(90, 30));
     c3.setPreferredSize(new Dimension(90, 30));
     c4.setPreferredSize(new Dimension(90, 30));
     c5.setPreferredSize(new Dimension(90, 30));

      cp = getContentPane();
     cp.setBackground(Color.ORANGE);
      //cp.setLayout(new BorderLayout());
      pN.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
     pN.setBackground(new Color(255, 102, 000));//
      pC1.setLayout(new GridLayout(3, 3, 10, 10));
      pC1.setBackground(Color.ORANGE);
      pC2.setLayout(new GridLayout(3, 3, 10, 10));
      pC2.setBackground(Color.ORANGE);
      pC3.setLayout(new GridLayout(3, 3, 10, 10));
      pC3.setBackground(Color.ORANGE);
      pC4.setLayout(new GridLayout(3, 3, 10, 10));
      pC4.setBackground(Color.ORANGE);
      pC5.setLayout(new GridLayout(3, 3, 10, 10));
      pC5.setBackground(Color.ORANGE);
      pC6.setLayout(new GridLayout(3, 3));
      pC6.setBackground(Color.ORANGE);
      pS.setLayout(new BorderLayout());
	  pS.setBackground(Color.ORANGE);

      pSpp.setLayout(new GridLayout(2,0));
	  pSpp.setBackground(Color.ORANGE);

      pSpN.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
     pSpN.setBackground(Color.ORANGE);     
     pNp.setLayout(new BorderLayout());
	 pNp.setBackground(Color.ORANGE);

	 pSp.setBackground(Color.ORANGE);


     
     

      JTableHeader anHeader = tb.getTableHeader();
      anHeader.setForeground(new Color(0).black);
      anHeader.setBackground(Color.white);
	  tb.getParent().setBackground(Color.white);
   

      // 보더 공백 4
      pN.setBorder(BorderFactory.createEmptyBorder(100,0,10,0));
     pC1.setBorder(BorderFactory.createEmptyBorder(10,18,10,18));
     pC2.setBorder(BorderFactory.createEmptyBorder(10,18,10,18));
     pC3.setBorder(BorderFactory.createEmptyBorder(10,18,10,18));
     pC4.setBorder(BorderFactory.createEmptyBorder(10,18,10,18));
     pC5.setBorder(BorderFactory.createEmptyBorder(10,18,10,18));
      //pS.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

      // NORTH 영역
      cp.add(pN, BorderLayout.NORTH);
     //pN.add(jlb, BorderLayout.NORTH);
      pN.add(c1);
      pN.add(c2);
      pN.add(c3);
      pN.add(c4);
      pN.add(c5);
      
      // CENTER 영역
      cp.add(pC1, BorderLayout.CENTER);
      for(int i = 0; i < menuList1.length; i++){
         pC1.add(menuList1[i]);
       menuList1[i].setBorderPainted(false);
      }
      



      // SOUTH 영역
	  //jf.setBackground(Color.WHITE);
      jf.setBackground(new Color(255, 102, 000));
      //jf.setBackground(new Color(000, 153, 051));
      jf.setForeground(Color.white);
      jf.setOpaque(true);
      jf.setFont(new Font("맑은고딕", Font.BOLD, 19));
      jf.setBounds(0,0,100,0);

     c1.setFont(new Font("맑은고딕", Font.BOLD, 13));
     c2.setFont(new Font("맑은고딕", Font.BOLD, 13));
     c3.setFont(new Font("맑은고딕", Font.BOLD, 13));
     c4.setFont(new Font("맑은고딕", Font.BOLD, 13));
     c5.setFont(new Font("맑은고딕", Font.BOLD, 13));
	 orderBtn.setFont(new Font("맑은고딕", Font.BOLD, 14));
	 resetBtn.setFont(new Font("맑은고딕", Font.BOLD, 14));

      

      
      cp.add(pS, BorderLayout.SOUTH);
      pS.add(pSp,BorderLayout.CENTER);
     ////////
     pSp.add(pNp,BorderLayout.CENTER);
      pNp.add(js, BorderLayout.CENTER);
     
      pS.add(pSp,BorderLayout.EAST);
      pSp.add(pSpp);
      pSpp.add(resetBtn);
      pSpp.add(orderBtn);
      pS.add(pSpN, BorderLayout.NORTH);
      pSpN.add(jf);


      c1.addActionListener(this);
      c2.addActionListener(this);
      c3.addActionListener(this);
      c4.addActionListener(this);
      c5.addActionListener(this);
     resetBtn.addActionListener(this);
     orderBtn.addActionListener(this);
      menuList1[0].addActionListener(this); menuList1[1].addActionListener(this); menuList1[2].addActionListener(this); menuList1[3].addActionListener(this); menuList1[4].addActionListener(this); menuList1[5].addActionListener(this); menuList1[6].addActionListener(this); menuList1[7].addActionListener(this); menuList1[8].addActionListener(this); 
     menuList2[0].addActionListener(this); menuList2[1].addActionListener(this); menuList2[2].addActionListener(this); menuList2[3].addActionListener(this); menuList2[4].addActionListener(this); menuList2[5].addActionListener(this); menuList2[6].addActionListener(this); menuList2[7].addActionListener(this); menuList2[8].addActionListener(this); 
     menuList3[0].addActionListener(this); menuList3[1].addActionListener(this); menuList3[2].addActionListener(this); menuList3[3].addActionListener(this); menuList3[4].addActionListener(this); menuList3[5].addActionListener(this); menuList3[6].addActionListener(this); menuList3[7].addActionListener(this); menuList3[8].addActionListener(this); 
     menuList4[0].addActionListener(this); menuList4[1].addActionListener(this); menuList4[2].addActionListener(this); menuList4[3].addActionListener(this); menuList4[4].addActionListener(this); menuList4[5].addActionListener(this); menuList4[6].addActionListener(this); menuList4[7].addActionListener(this); menuList4[8].addActionListener(this); 
     menuList5[0].addActionListener(this); menuList5[1].addActionListener(this); menuList5[2].addActionListener(this); menuList5[3].addActionListener(this); menuList5[4].addActionListener(this); menuList5[5].addActionListener(this); menuList5[6].addActionListener(this); menuList5[7].addActionListener(this); menuList5[8].addActionListener(this); 
   }


   void loadImg(){
      try{
         bread_1 = new ImageIcon(ImageIO.read(new File("Image/샌드위치_1.PNG")));
         bread_2 = new ImageIcon(ImageIO.read(new File("Image/샌드위치_2.PNG")));
         bread_3 = new ImageIcon(ImageIO.read(new File("Image/샌드위치_3.PNG")));
         bread_4 = new ImageIcon(ImageIO.read(new File("Image/샌드위치_4.PNG")));
         bread_5 = new ImageIcon(ImageIO.read(new File("Image/샌드위치_5.PNG")));
         bread_6 = new ImageIcon(ImageIO.read(new File("Image/샌드위치_6.PNG")));
         bread_7 = new ImageIcon(ImageIO.read(new File("Image/샌드위치_7.PNG")));
         bread_8 = new ImageIcon(ImageIO.read(new File("Image/샌드위치_8.PNG")));
         bread_9 = new ImageIcon(ImageIO.read(new File("Image/샌드위치_9.PNG")));

         salad_1 = new ImageIcon(ImageIO.read(new File("Image/salad_1.PNG")));
         salad_2 = new ImageIcon(ImageIO.read(new File("Image/salad_2.PNG")));
         salad_3 = new ImageIcon(ImageIO.read(new File("Image/salad_3.PNG")));
         salad_4 = new ImageIcon(ImageIO.read(new File("Image/salad_4.PNG")));
         salad_5 = new ImageIcon(ImageIO.read(new File("Image/salad_5.PNG")));
         salad_6 = new ImageIcon(ImageIO.read(new File("Image/salad_6.PNG")));
         salad_7 = new ImageIcon(ImageIO.read(new File("Image/salad_7.PNG")));
         salad_8 = new ImageIcon(ImageIO.read(new File("Image/salad_8.PNG")));
         salad_9 = new ImageIcon(ImageIO.read(new File("Image/salad_9.PNG")));

         wrap_1 = new ImageIcon(ImageIO.read(new File("Image/wrap_1.PNG")));
         wrap_2 = new ImageIcon(ImageIO.read(new File("Image/wrap_2.PNG")));
         wrap_3 = new ImageIcon(ImageIO.read(new File("Image/wrap_3.PNG")));
         wrap_4 = new ImageIcon(ImageIO.read(new File("Image/wrap_4.PNG")));
         wrap_5 = new ImageIcon(ImageIO.read(new File("Image/wrap_5.PNG")));
         wrap_6 = new ImageIcon(ImageIO.read(new File("Image/wrap_6.PNG")));
         wrap_7 = new ImageIcon(ImageIO.read(new File("Image/wrap_7.PNG")));
         wrap_8 = new ImageIcon(ImageIO.read(new File("Image/wrap_8.PNG")));
         wrap_9 = new ImageIcon(ImageIO.read(new File("Image/wrap_9.PNG")));

         side_1 = new ImageIcon(ImageIO.read(new File("Image/side_1.PNG")));
         side_2 = new ImageIcon(ImageIO.read(new File("Image/side_2.PNG")));
         side_3 = new ImageIcon(ImageIO.read(new File("Image/side_3.PNG")));
         side_4 = new ImageIcon(ImageIO.read(new File("Image/side_4.PNG")));
         side_5 = new ImageIcon(ImageIO.read(new File("Image/side_5.PNG")));
         side_6 = new ImageIcon(ImageIO.read(new File("Image/side_6.PNG")));
         side_7 = new ImageIcon(ImageIO.read(new File("Image/side_7.PNG")));
         side_8 = new ImageIcon(ImageIO.read(new File("Image/side_8.PNG")));
         side_9 = new ImageIcon(ImageIO.read(new File("Image/side_9.PNG")));

         another_1 = new ImageIcon(ImageIO.read(new File("Image/기타_1.PNG")));
         another_2 = new ImageIcon(ImageIO.read(new File("Image/기타_2.PNG")));
         another_3 = new ImageIcon(ImageIO.read(new File("Image/기타_3.PNG")));
         another_4 = new ImageIcon(ImageIO.read(new File("Image/기타_4.PNG")));
         another_5 = new ImageIcon(ImageIO.read(new File("Image/기타_5.PNG")));
         another_6 = new ImageIcon(ImageIO.read(new File("Image/기타_6.PNG")));
         another_7 = new ImageIcon(ImageIO.read(new File("Image/기타_7.PNG")));
         another_8 = new ImageIcon(ImageIO.read(new File("Image/기타_8.PNG")));
         another_9 = new ImageIcon(ImageIO.read(new File("Image/기타_9.PNG")));

         right = new ImageIcon(ImageIO.read(new File("Image/right.png")));
         left = new ImageIcon(ImageIO.read(new File("Image/left.png")));
         logo = new ImageIcon(ImageIO.read(new File("Image/logo.png")));

         
         
      }catch(IOException ie){
        System.out.println("ie: " + ie);
   
      }
   }

   void setUI(){
      setTitle("Kiosk");
      setSize(600, 900);
      setVisible(true);
      setLocationRelativeTo(null);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }


   public void actionPerformed(ActionEvent e){
      Object obj = e.getSource();
      if(obj == resetBtn){//전체퓌소
         DefaultTableModel m = (DefaultTableModel)tb.getModel();
         m.setRowCount(0);
       int sum =0;
       jf.setText(String.valueOf(" 총 금액 : "+sum));
	   String saveStr = "----------주문내역----------"+"\n";
	   try{
			String filePath = "주문내역.txt" ;
			FileWriter fileWriter = new FileWriter(filePath);
			fileWriter.write(saveStr);
			fileWriter.close();

		}catch(IOException ie){}
      }

	

    if(obj == orderBtn){
        int rowCont = tb.getRowCount();
        int sum =0;
        for(int i=0;i<rowCont; i++) {
             sum += Integer.valueOf((String)tb.getValueAt(i, 2));	
        }

		jf.setText(String.valueOf(" 총 금액 : "+sum));
        jf.setFont(new Font("맑은고딕", Font.BOLD, 20));
        System.out.println("총 금액: " + sum);
        JOptionPane.showMessageDialog(null, "결제가 완료되었습니다. 고객님^-^");

		////
		String saveStr = "----------주문내역----------"+"\n";
		for(int i=0; i<ordermenu.size(); i++){
			saveStr = saveStr + ordermenu.get(i)+"\n";	
		}
		try{
			String filePath = "주문내역.txt" ;
			FileWriter fileWriter = new FileWriter(filePath);
			fileWriter.write(saveStr);
			fileWriter.close();
		}catch(IOException ie){}
		connect();
    } 


      if(obj == c1){
         pC2.setVisible(false);
         pC3.setVisible(false);
         pC4.setVisible(false);
         pC5.setVisible(false);
       pC6.setVisible(false);
         pC1.setVisible(true);
         cp.add(pC1);
      }else if(obj == c2){
         pC1.setVisible(false);
         pC3.setVisible(false);
         pC4.setVisible(false);
         pC5.setVisible(false);
       pC6.setVisible(false);
         pC2.setVisible(true);
         cp.add(pC2);
      }else if(obj == c3){
         pC1.setVisible(false);
         pC2.setVisible(false);
         pC4.setVisible(false);
         pC5.setVisible(false);
       pC6.setVisible(false);
         pC3.setVisible(true);
         cp.add(pC3);
      }else if(obj == c4){
         pC1.setVisible(false);
         pC2.setVisible(false);
         pC3.setVisible(false);
         pC5.setVisible(false);
       pC6.setVisible(false);
         pC4.setVisible(true);
         cp.add(pC4);
      }else if(obj == c5){
         pC1.setVisible(false);
         pC2.setVisible(false);
         pC3.setVisible(false);
         pC4.setVisible(false);
       pC6.setVisible(false);
         pC5.setVisible(true);
         cp.add(pC5);
      }

      String inputMenu = e.getActionCommand();
      System.out.println("inputMenu :" + inputMenu);
      int idx = isExistData(tb, inputMenu);
      String[] add = new String[9];

      if(obj == menuList1[0]){
        addRow(idx, 1000, inputMenu, add);
      }
      else if(obj == menuList1[1]){
        addRow(idx, 10000, inputMenu, add);
      }
      else if(obj == menuList1[2]){
         addRow(idx, 3000, inputMenu, add);
      }
      else if(obj == menuList1[3]){
         addRow(idx, 3000, inputMenu, add);
      }
      else if(obj == menuList1[4]){
         addRow(idx, 3000, inputMenu, add);
      }
      else if(obj == menuList1[5]){
         addRow(idx, 3000, inputMenu, add);
      }
      else if(obj == menuList1[6]){
         addRow(idx, 3000, inputMenu, add);
      }
      else if(obj == menuList1[7]){
         addRow(idx, 3000, inputMenu, add);
      }
      else if(obj == menuList1[8]){
         addRow(idx, 3000, inputMenu, add);
      }
      else if(obj == menuList2[0]){
         addRow(idx, 3000, inputMenu, add);
      }
      else if(obj == menuList2[1]){
         addRow(idx, 3000, inputMenu, add);
      }
      else if(obj == menuList2[2]){
         addRow(idx, 3000, inputMenu, add);
      }
     else if(obj == menuList2[3]){
         addRow(idx, 3000, inputMenu, add);
      }
     else if(obj == menuList2[4]){
         addRow(idx, 3000, inputMenu, add);
      }
     else if(obj == menuList2[5]){
         addRow(idx, 3000, inputMenu, add);
      }
     else if(obj == menuList2[6]){
         addRow(idx, 3000, inputMenu, add);
      }
     else if(obj == menuList2[7]){
         addRow(idx, 3000, inputMenu, add);
      }
     else if(obj == menuList2[8]){
        addRow(idx, 3000, inputMenu, add);
      }
     else if(obj == menuList3[0]){
        addRow(idx, 4000, inputMenu, add);
      }
     else if(obj == menuList3[1]){
        addRow(idx, 5000, inputMenu, add);
      }
     else if(obj == menuList3[2]){
        addRow(idx, 6000, inputMenu, add);
      }
     else if(obj == menuList3[3]){
        addRow(idx, 8000, inputMenu, add);
      }
     else if(obj == menuList3[4]){
        addRow(idx, 6500, inputMenu, add);
      }
     else if(obj == menuList3[5]){
        addRow(idx, 4500, inputMenu, add);
      }
     else if(obj == menuList3[6]){
        addRow(idx, 3500, inputMenu, add);
      }
     else if(obj == menuList3[7]){
        addRow(idx, 9800, inputMenu, add);
      }
     else if(obj == menuList3[8]){
        addRow(idx, 7700, inputMenu, add);
      }
     else if(obj == menuList4[0]){
        addRow(idx, 6600, inputMenu, add);
      }
     else if(obj == menuList4[1]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList4[2]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList4[3]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList4[4]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList4[5]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList4[6]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList4[7]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList4[8]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList5[0]){
        addRow(idx, 3300, inputMenu, add);
      }
     else if(obj == menuList5[1]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList5[2]){
       addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList5[3]){
       addRow(idx, 4300, inputMenu, add);
      }
     else if(obj == menuList5[4]){
        addRow(idx, 5300, inputMenu, add);
      }
     else if(obj == menuList5[5]){
        addRow(idx, 7600, inputMenu, add);
      }
     else if(obj == menuList5[6]){
        addRow(idx, 5500, inputMenu, add);
      }
     else if(obj == menuList5[7]){
        addRow(idx, 100000, inputMenu, add);
      }
     else if(obj == menuList5[8]){
        addRow(idx, 100000, inputMenu, add);
      }
   }
	/////////////////////////////////////////////////////////////////////////////////////////////////
	List<String> ordermenu = new ArrayList<>();


   public void addRow(int idx, int payment, String inputMenu, String[] add) {
         if(idx != -1){

         model.getValueAt(idx, 1);
            String pre_count = (String)model.getValueAt(idx, 1);
            int tmp_count = Integer.parseInt(pre_count);
            String new_count = Integer.toString(tmp_count + 1);
      
            String pre_amount = (String) model.getValueAt(idx, 2);
            int tmp_amount = Integer.parseInt(pre_amount);
            String new_amount = Integer.toString(tmp_amount + payment);

            model.setValueAt(new_count, idx, 1);
            model.setValueAt(new_amount, idx, 2);
         }else{
            add[0] = inputMenu;
            add[1] = "1";
            add[2] = Integer.toString(payment);
            model.addRow(add);
         }
		 ordermenu.add(inputMenu);
   }
   

    public int isExistData(JTable tb, String menu) { //내가 이미 누른 스트링 메뉴의 버튼 //현재의 jtable 상태가 들어와있다 , 스트링 메뉴에는 내가 찾고자하는 메뉴가 들어간다,
        // 현재까지 들어있는 로우(행) 수만 큼 루프를 돌면서
        for (int i = 0; i < tb.getRowCount(); i++) { //현재 테이블의 행 갯수만큼 돌린다.
            // 해당 메뉴 이름이 Jtable에 존재하면
            if (tb.getValueAt(i, 0) == menu) { //i는 행의 0번째 컬럼이야 //행이가로 열이 세로
                // 몇 번째 행인지 반환
                return i;
            }
        }
        // 만약 해당 메뉴 이름이 Jtable이 존재 하지 않으면 -1 반환
        return -1;
    }

	   void connect(){ //서버와 접속
		try{
			
			String ip = "192.168.0.136";
			String portStr = "4000";
			
			int port = Integer.parseInt(portStr);
			s = new Socket(ip, port);
//
			ready();
		}catch(IOException ie){}
	}
	void ready(){
		try{
			fis = new FileInputStream(fname);
			bis = new BufferedInputStream(fis, 4096);
			os = s.getOutputStream();
			bos = new BufferedOutputStream(os, 4096);

			send();
		}catch(FileNotFoundException fe){
		}catch(IOException ie){}
	}
	void send(){ //file -> socket
		byte bs[] = new byte[1024];
		int i=0;
		long total = 0L;
		try{
			while((i=bis.read(bs)) != -1){
				bos.write(bs, 0, i);
				System.out.println("전송 중..."+ ( total+=i ) + "bytes");
			}
			bos.flush();
			System.out.println("파일("+fname+": "+total+"bytes) 전송 완료!!");
		}catch(IOException ie){
		}finally{
			closeAll();	
		}
	}
	void closeAll(){
		try{
			bis.close();
			bos.close();
			fis.close();
			os.close();
			s.close();
		}catch(IOException ie){}
	}
	
   public static void main(String[] args) {
      Kiosk ki = new Kiosk();
      ki.init();
      
   }
}