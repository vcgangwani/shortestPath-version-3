package project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UserInterface extends javax.swing.JFrame implements ActionListener{
	Map[] allMaps;
	Map currentMap;
	private MapDisplay Map;
	private DefaultListModel mapListElements = new DefaultListModel();
	private DefaultListModel algoListElements = new DefaultListModel();
	private DefaultListModel heurListElements = new DefaultListModel();
    private javax.swing.JButton changeMapButton;
    private javax.swing.JButton generateMapButton;
    private javax.swing.JButton startAIButton;
    private javax.swing.JButton cellStatsButton;
    private javax.swing.JList<String> mapList;
    private javax.swing.JList<String> algoList;
    private javax.swing.JList<String> heurList;
    private javax.swing.JPanel mapDisplay;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private javax.swing.JTextField xInput;
    private javax.swing.JTextField yInput;
    private javax.swing.JTextField wInput;
    private javax.swing.JTextArea stats;
    private javax.swing.JComboBox<String> algoChoice;
    private javax.swing.JComboBox<String> heurChoice;    
    private int[][] startToEnd;
    
	public UserInterface(){
		initComponents();
	}
	
	
	private void initComponents(){
		label1 = new java.awt.Label();
		label2 = new java.awt.Label();
		label3 = new java.awt.Label();
		label4 = new java.awt.Label();
		label5 = new java.awt.Label();
		label6 = new java.awt.Label();
		label7 = new java.awt.Label();
		Map = new MapDisplay();
		allMaps = getMapList();
		Timer timer;
		timer = new Timer(500,this);
		timer.start();
		mapDisplay = new javax.swing.JPanel();
		changeMapButton = new JButton();
		generateMapButton = new JButton();
		startAIButton = new JButton();
		cellStatsButton = new JButton();
		mapList = new javax.swing.JList<>(mapListElements);
		algoList = new javax.swing.JList<>(algoListElements);
		heurList = new javax.swing.JList<>(heurListElements);
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane2 = new javax.swing.JScrollPane();
		jScrollPane3 = new javax.swing.JScrollPane();
		xInput = new JTextField();
		yInput = new JTextField();
		wInput = new JTextField();
		stats = new JTextArea();
		algoChoice = new javax.swing.JComboBox<>();
		heurChoice = new javax.swing.JComboBox<>();
		
		
		label1.setName("MapsLabel");
		label1.setText("MAPS");		
		label2.setName("SearchLabel");
		label2.setText("SEARCH");	
		label3.setName("xInputLabel");
		label3.setText("X Coordinate:");		
		label4.setName("yInputLabel");
		label4.setText("Y Coordinate:");
		label5.setName("wInputLabel");
		label5.setText("Weight: ");
		label6.setName("algoLabel");
		label6.setText("Algorithm");
		label7.setName("heurLabel");
        label7.setText("Heuristic");
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(mapDisplay);
		mapDisplay.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Map, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Map, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        
        changeMapButton.setLabel("Change Map");
        changeMapButton.setName("ChangeMapButton"); // NOI18N
        changeMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeMapButtonActionPerformed(evt);
            }
        });
        
        generateMapButton.setText("Generate Map");
        generateMapButton.setName("MapGenerationButton"); // NOI18N
        generateMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateMapButtonActionPerformed(evt);
            }
        });
        
        startAIButton.setText("Start");
        startAIButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAIButtonActionPerformed(evt);
            }
        });
        
        cellStatsButton.setText("Calculate");
        cellStatsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cellStatsButtonActionPerformed(evt);
            }
        });
        
        Map[] temp = getMapList();
        for (int x = 0; x < temp.length; x++) {
        	//System.out.println("name" + x + ": " + temp[x].getName());
			mapListElements.addElement(temp[x].getName());
		}
        mapList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mapList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mapList.setName("Maps"); // NOI18N
        jScrollPane1.setViewportView(mapList);
        
        algoListElements.addElement("Uniform Cost Search");
        algoListElements.addElement("A* Search");
        algoListElements.addElement("Weighted A* Search");
        algoList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        algoList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        algoList.setName("Algorithms");
        jScrollPane2.setViewportView(algoList);
        
        heurListElements.addElement("heuristic1");
        heurListElements.addElement("heuristic2");
        heurListElements.addElement("heuristic3");
        heurListElements.addElement("heuristic4");
        heurList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        heurList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        heurList.setName("Heuristics");
        jScrollPane3.setViewportView(heurList);
        
        algoChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Uniform Cost Search", "A* Search", "Weighted A* Search" }));
        heurChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "heuristic1", "heuristic2", "heuristic3", "heuristic4" }));
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mapDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(label1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(changeMapButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(generateMapButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(label2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                    		.addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    		.addGap(12, 12, 12)
                                    		.addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                    		.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    		.addGap(12, 12, 12)
                                    		.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(wInput, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addGap(12, 12, 12)
                                            .addGap(javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(startAIButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(label3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(xInput, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addGap(12, 12, 12)
                                            .addGap(javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                            .addComponent(yInput, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12)
                                            .addComponent(cellStatsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(stats, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addContainerGap())))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(mapDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(changeMapButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(generateMapButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(wInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(startAIButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(xInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cellStatsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            ))
                    .addContainerGap())
    );
            pack();
	}
	
	protected void cellStatsButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int xCoord, yCoord;
        if(!xInput.getText().equals("")){
        	xCoord = Integer.parseInt(xInput.getText());
        }
        else xCoord = 0;
        if(!yInput.getText().equals("")){
        	yCoord = Integer.parseInt(yInput.getText());
        }
        else yCoord = 0;
        
        if(currentMap == null){
        	stats.setText("Stats: ");
        }
        else{
        	stats.setText("Stats: \n" + currentMap.getCell(xCoord,yCoord).toString());
        	currentMap.setSearch(new Coordinate(xCoord,yCoord), Integer.parseInt(currentMap.getName().charAt(currentMap.getName().length()-5) + ""));
        	Map.repaint();
        }
	}


	private void changeMapButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	// TODO add your handling code here:
        //change map
    	String mapWanted = mapList.getSelectedValue();
    	//System.out.println(mapWanted);
		for (int x = 0; x < allMaps.length; x++) {
			if (allMaps[x].getName().equals(mapWanted)) {
				Map.setMap(allMaps[x]);
				currentMap = allMaps[x];
				return;
			}
		}
	}  
	
	private void generateMapButtonActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        //generate map
    	boolean contains = false;
		String name = "";
		do {
			contains = false;
			name = JOptionPane.showInputDialog(
					"Enter a name for your map! (note cannot already exist and must be atleast 3 characters long!)");
			if (name == null)
				return;
			for (int x = 0; x < allMaps.length; x++) {
				if (allMaps[x].getName().equals(name)) {
					contains = true;
				}
			}
		} while (contains);
		if (name.trim().length() > 3) {
			try {
				Map[] tempMaps = allMaps;
				allMaps = new Map[tempMaps.length + 10];
				for (int x = 0; x < tempMaps.length; x++) {
					allMaps[x] = tempMaps[x];
				}
				Map[] newMaps;
				newMaps = MapGenerator.generate(name);
				for(int i = 0; i < 10; i++){
					allMaps[tempMaps.length+i] = newMaps[i];
					mapListElements.addElement(name + "_" + i + ".txt");
				}
				for(int i = 0; i < allMaps.length; i++){
					System.out.println(allMaps[i].getName());
				}
				System.out.println("Created new map!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
	
	private void startAIButtonActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        //AI start
		//System.out.println("Algorithm: " + algoList.getSelectedValue() + " " + algoList.getSelectedIndex());
		//System.out.println("Heuristic: " + heurList.getSelectedValue() + " " + heurList.getSelectedIndex());
		if(algoList.getSelectedValue() == null || heurList.getSelectedValue() == null){
			System.out.println("Select algorithm/heuristic.");
		}
		int weight, mapNum = 0;
		mapNum = Integer.parseInt(Map.getMap().getName().charAt(Map.getMap().getName().length()-5) + "");
        if(!wInput.getText().equals("")){
        	weight = Integer.parseInt(xInput.getText());
        }
        else weight = 0;
        switch(algoList.getSelectedValue()){
    	case "Uniform Cost Search":
    		startToEnd = ShortestPath.ShortestPath(Map.getMap().getCharMap(), Map.getMap().getCellStart(mapNum), Map.getMap().getCellEnd(mapNum), 0, heurList.getSelectedIndex()+1);
    		break;
    	case "A* Search":
    		startToEnd = ShortestPath.ShortestPath(Map.getMap().getCharMap(),Map.getMap().getCellStart(mapNum), Map.getMap().getCellEnd(mapNum), 1, heurList.getSelectedIndex()+1);
    		break;
    	case "Weighted A* Search":
    		startToEnd = ShortestPath.ShortestPath(Map.getMap().getCharMap(),Map.getMap().getCellStart(mapNum), Map.getMap().getCellEnd(mapNum), weight, heurList.getSelectedIndex()+1);
    		break;
}
    } 
	
	public static Map[] getMapList(){
		String currentDir = new File("").getAbsolutePath();
        File file= new File(currentDir);
        File[] files = file.listFiles();
        ArrayList<Map> test = new ArrayList<Map>();
        try{
        	for(int z=0;z<files.length;z++){
        		if(files[z].getName().contains(".txt")){
        			System.out.println(files[z].getAbsolutePath());
        			test.add(new Map(files[z].getAbsolutePath()));
        		}
        	}
        }catch(Exception e){
            System.out.println("error while grabbing map data. " + e.toString());
            
        }
        Map[] theMaps = new Map[test.size()];
        test.toArray(theMaps);
        return theMaps;
	}
	
	public static void main(String[] args) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	UserInterface test = new UserInterface();
				test.setVisible(true);
				test.setResizable(false);
				test.setTitle("Part 1");
            }
        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
