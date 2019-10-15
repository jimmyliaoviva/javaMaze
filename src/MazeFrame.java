//105403517
//廖顥軒
//資管3A
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;



public class MazeFrame extends JFrame{

	
	//裡面的new imageIcon 是抓取檔案並存成image 型態來做大小更改，外面再new 依次是將image 型態再變回imageIcon
	public ImageIcon wallIcon = new ImageIcon(
				new ImageIcon(getClass().getResource("brickwall.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	public Icon heartIcon = new ImageIcon(
				new ImageIcon(getClass().getResource("heart.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	public Icon diamondIcon = new ImageIcon(
				new ImageIcon(getClass().getResource("diamond.png")).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	//private final JLabel structureLabel[];
	private final JPanel allPnl = new JPanel();
	private final List<JLabel> blockLabel = new ArrayList<JLabel>();
	private final BloodPanel bloodPnl = new BloodPanel();
	
	public MazeFrame() {
		super("Maze");
		//設定最大的Frame 排版
		setLayout(new BorderLayout());
		//設定10*10的迷宮地圖排版
		allPnl.setLayout(new GridLayout(10,10));
		
		//ImageIcon wallImg = new ImageIcon(wallIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		
		
	/*	try(
				Stream<String> stream =
					Files.lines(Paths.get("map.txt"))){
			stream.forEach(System.out::println);
		}catch(Exception e) {
			
		}
		*/
			SecureRandom random = new SecureRandom();
			Pattern pattern = Pattern.compile("\\s+");   //用來分隔地圖0,1中間的空格
		List<String> list;
		try {
			 list = 
					Files.lines(Paths.get("map.txt"))//讀黨
					.flatMap(line->pattern.splitAsStream(line))   //用空格隔開並且展開
					.collect(Collectors.toList());
			 
			 
			 random.ints(10,0,100)  //隨機十個變數，0到100   當list 在x 的時候數值是1(代表牆壁)，變成3
			 		.boxed()
			 		.sorted()
			 		.collect(Collectors.groupingBy(Function.identity()))
			 		//.forEach((a,b)->System.out.println(a));
			 		.forEach((x,y)->{System.out.println(x);
			 						if(list.get(x).equals("1")) {
			 							list.set(x, "3");
			 						}//end if
			 		});
			 
			 list.stream()
				.forEach(x->{
					//System.out.println(x);
					if(x.equals("0")) {
						blockLabel.add(new JLabel());
						
						allPnl.add(blockLabel.get(blockLabel.size()-1));
						//這裡放listener 或是Anonymous 都沒有作用
						blockLabel.get(blockLabel.size()-1).addMouseListener(new mouseHandler(0));
				
					}//end 
					else if(x.equals("1")) {
						
						blockLabel.add(new JLabel(wallIcon));
						allPnl.add(blockLabel.get(blockLabel.size()-1));
						blockLabel.get(blockLabel.size()-1).addMouseListener(new mouseHandler(1));
					}//end else if
					else if(x.equals("2")) {
						blockLabel.add(new JLabel(diamondIcon));
						allPnl.add(blockLabel.get(blockLabel.size()-1));
						
					}//end else if
					else if(x.equals("3")) {
						blockLabel.add(new JLabel(heartIcon));
						allPnl.add(blockLabel.get(blockLabel.size()-1));
						
					}//end else if
					
				});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//blockLabel.get(0).addMouseListener(new mouseHandler());
		//allPnl.addMouseListener(new mouseHandler());
		
		add(allPnl,BorderLayout.CENTER);
		add(bloodPnl,BorderLayout.NORTH);
		
		
	}//end constructor
	
	
	
	private class mouseHandler extends MouseAdapter{
		public int blocktype=0;
		public mouseHandler(int blocktype) {
			this.blocktype = blocktype;
		}//end constructor
	
		
		@Override
		public void mouseEntered(MouseEvent e) {
			switch(blocktype) {
			case 0:
				System.out.println("-5");
				bloodPnl.hitRoad();
				break;
			case 1:
				System.out.println("-20");
				bloodPnl.hitWall();
				break;
			}//end switch
		}//end mouseMoved
	}//end mouseHandler
	
}//end mazeFrame
