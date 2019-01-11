package EightPuzzle;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.GridLayout;

import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.swing.UIManager;


public class Application extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	long startTime;
	long end;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 388);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 56, 270, 270);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("宋体", Font.PLAIN, 25));
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_3.setEditable(false);
		textField_3.setBackground(Color.WHITE);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBackground(Color.WHITE);
		textField_4.setEditable(false);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_5.setEditable(false);
		textField_5.setBackground(Color.WHITE);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_6.setBackground(Color.WHITE);
		textField_6.setEditable(false);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_7.setEditable(false);
		textField_7.setBackground(Color.WHITE);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("宋体", Font.PLAIN, 25));
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setBackground(Color.WHITE);
		textField_8.setEditable(false);
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel label = new JLabel("八数码");
		label.setFont(new Font("楷体", Font.PLAIN, 23));
		label.setBounds(109, 13, 80, 30);
		contentPane.add(label);
		
		//随机数按钮
		JButton button = new JButton("生成棋盘");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RandomStart();
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		button.setBounds(335, 234, 113, 27);
		contentPane.add(button);
		
		//八数码解决按钮
		JButton button_1 = new JButton("开始");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			  int flag = Asearch(reFigure());	
			  ResultShow(flag);
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		button_1.setBounds(335, 286, 113, 27);
		contentPane.add(button_1);
	}
	
	
	//生成随机数
	public void RandomStart() {
		int[] figure = new int[8];
		int temp = 1+(int)(Math.random()*8);
	    figure[0] = temp;
		
		for (int i = 1; i < 8; i++) {
			  boolean flag = true; 
		      while(flag) {
		    	  temp = 1+(int)(Math.random()*8);
		  	      figure[i] = temp;
		    	  for (int j = 0; j < i; j++) {
				      if(figure[i] == figure[j]) {
				    	  flag = true;
				    	  break;
				      }
				      flag = false;
		          }
			}
		        		
		}
	//	int[] figure = {2,1,6,4,3,8,7,5,0};
		textField.setText(Integer.toString(figure[0]));
		textField_1.setText(Integer.toString(figure[1]));
		textField_2.setText(Integer.toString(figure[2]));
		textField_3.setText(Integer.toString(figure[3]));
		textField_4.setText(Integer.toString(figure[4]));
		textField_5.setText(Integer.toString(figure[5]));
		textField_6.setText(Integer.toString(figure[6]));
		textField_7.setText(Integer.toString(figure[7]));
		textField_8.setText("0");
	}
	
	public void setfigure(int[] figure) {
		
		textField.setText(Integer.toString(figure[0]));
		textField_1.setText(Integer.toString(figure[1]));
		textField_2.setText(Integer.toString(figure[2]));
		textField_3.setText(Integer.toString(figure[3]));
		textField_4.setText(Integer.toString(figure[4]));
		textField_5.setText(Integer.toString(figure[5]));
		textField_6.setText(Integer.toString(figure[6]));
		textField_7.setText(Integer.toString(figure[7]));
		textField_8.setText(Integer.toString(figure[8]));
		
	}
	
	//返回界面数值
	public int[] reFigure() {
		int[] figure = new int[9];
		figure[8] = 0;

		figure[0] = Integer.parseInt(textField.getText());
		figure[1] = Integer.parseInt(textField_1.getText());
		figure[2] = Integer.parseInt(textField_2.getText());
		figure[3] = Integer.parseInt(textField_3.getText());
		figure[4] = Integer.parseInt(textField_4.getText());
		figure[5] = Integer.parseInt(textField_5.getText());
		figure[6] = Integer.parseInt(textField_6.getText());
		figure[7] = Integer.parseInt(textField_7.getText());
		figure[8] = Integer.parseInt(textField_8.getText());
		
		return figure;
	}
	
	//显示最终结果
	public void ResultShow(int flag) {
		textField_9 = new JTextField();
		textField_9.setFont(new Font("楷体", Font.PLAIN, 15));
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField_9.setEditable(false);
		textField_9.setBounds(298, 96, 165, 77);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		if (flag==1) {
			textField_9.setText("成功"+"运行时间"+(end-startTime)+"ms");
		} else if(flag==0) {
			textField_9.setText("没有解,再次尝试");
		}
	}
	
	//a*算法
	public int Asearch(int[] figure) {
		//定义open表
        ArrayList<Node> open = new ArrayList<Node>();
        ArrayList<Node> close = new ArrayList<Node>();
        Node start = new Node();
        Node target = new Node();
        
        int flag =0;//用于判断最终提示框中输出是成功还是失败
        
        String lineContent = null;
        int stnum[] = figure;
        int tanum[] = {1,2,3,4,5,6,7,8,0};
       
        start.setNum(stnum);
        target.setNum(tanum);
        startTime=System.currentTimeMillis();   //获取开始时间
        if(start.isSolvable(target)){
            //初始化
            start.init(target);//获取初始状态对应的F函数值          
            open.add(start);
           
            while(open.isEmpty() == false){
            	
                Collections.sort(open);     //按照Fvalue的值排序
                Node best = open.get(0);    //从open表中取出最小估值的状态并移除open表
                open.remove(0);
                close.add(best);
                if(best.isTarget(target)){            
                    //输出
                	setfigure(stnum);
                	flag = 1;//flag为1最终提示框中会有成功的相关的信息
                    best.printRoute();//以九宫格打印出状态
                    end=System.currentTimeMillis(); //获取结束时间  
                    System.out.println("程序运行时间： "+(end-startTime)+"ms");
                    return flag;
                }
                
                int move;
                //由best状态进行扩展，寻找当前状态能直接达到的状态并加入到open表中
                
                //若能向上移动，利用启发式算法进行判断，并更新f(n)估值函数
                if(best.isMoveUp()){
                    move = 0;
                    Node up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                
                //若能向下移动，利用启发式算法进行判断，并更新f(n)估值函数
                if(best.isMoveDown()){
                    move = 1;
                    Node up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                
                //若能向左移动，利用启发式算法进行判断，并更新f(n)估值函数
                if(best.isMoveLeft()){
                    move = 2;
                    Node up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
                
                //若能向右移动，利用启发式算法进行判断，并更新f(n)估值函数
                if(best.isMoveRight()){
                    move = 3;
                    Node up = best.moveUp(move);
                    up.operation(open, close, best, target);
                }
               
                
            }//当open表为空结束循环，找到目标状态后返回flag值提示成功
          
        }
        	
        	return flag;
	}
	
	
}

