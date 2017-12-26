package hw9;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

@SuppressWarnings({ "unused", "serial" })
public class CampusPathsGuiView extends JFrame//http://www.cnblogs.com/pzy4447/p/4907461.html
{
	private MapPanel mapPanel;
	private JPanel compPanel;
	private JList<String> List0;
	private JList<String> List1;
	private DefaultListModel<String> Model0;
	private DefaultListModel<String> Model1;
	private JLabel Label0;
	private JLabel Label1;
	private JButton find;
	private JButton reset;
	public CampusPathsGuiView()
	{
		this.setSize(1024, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.GRAY);
		this.setLayout(new BorderLayout());//Lecture 23 Slide 23
		mapPanel = new MapPanel();
		mapPanel.setBackground(Color.GRAY);
		compPanel = new JPanel();
		compPanel.setPreferredSize(new Dimension(500, 500));
		compPanel.setBackground(Color.GRAY);
		compPanel.setLayout(new GridBagLayout());
		JPanel listPanel = new JPanel(new GridBagLayout());
		listPanel.setBackground(Color.GRAY);
		Label0 = new JLabel("START: ");
		Label0.setVerticalAlignment(SwingConstants.BOTTOM);
		Model0 = new DefaultListModel<String>();
		List0 = new JList<String>(Model0);
		List0.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		List0.setSelectedIndex(-1);
		List0.setBorder(new LineBorder(new Color(0, 0, 0)));
		Label1 = new JLabel("END: ");
		Label1.setVerticalAlignment(SwingConstants.BOTTOM);
		Model1 = new DefaultListModel<String>();
		List1 = new JList<String>(Model1);
		List1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		List1.setSelectedIndex(-1);
		List1.setBorder(new LineBorder(new Color(0, 0, 0)));
		addComp(listPanel, Label0, 0, 0, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH);
		addComp(listPanel, new JScrollPane(List0), 0, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH);
		addComp(listPanel, Label1, 0, 2, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH);
		addComp(listPanel, new JScrollPane(List1), 0, 3, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH);
		addComp(compPanel, listPanel, 0, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH);
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		find = new JButton("Find Route");
		reset = new JButton("Reset");
		addComp(buttonPanel, find, 0, 2, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH);
		addComp(buttonPanel, reset, 0, 3, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH);
		addComp(compPanel, buttonPanel, 0, 1, 2, 2, GridBagConstraints.NORTH,
				GridBagConstraints.BOTH);
		getContentPane().add(mapPanel, BorderLayout.WEST);
		getContentPane().add(compPanel, BorderLayout.EAST);
		this.pack();
		this.setResizable(true);
		this.setLocationRelativeTo(null);
	}
	private void addComp(JPanel jp, JComponent c, int x, int y, int w, int h, int a, int s)
	{
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = x;
		gridConstraints.gridy = y;
		gridConstraints.gridwidth = w;
		gridConstraints.gridheight = h;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(5, 5, 5, 5);
		gridConstraints.anchor = a;
		gridConstraints.fill = s;
		jp.add(c, gridConstraints);
	}
	public void reset()
	{
		List1.setSelectedIndex(0);
		List0.setSelectedIndex(0);		
		mapPanel.reset();
	}
	public void updateBuildingLists(String[] buildings)
	{
		Model0.removeAllElements();
		Model1.removeAllElements();
		for (String b : buildings)
		{
			Model0.addElement(b);
			Model1.addElement(b);
		}
	}
	public void updatePoints(double x0, double y0, double x1, double y1)
	{
		mapPanel.setStartingPoints(x0, y0);
		mapPanel.setDestinationPoints(x1, y1);
		mapPanel.repaint();
	}
	public void updatePath(ArrayList<ArrayList<Double>> coordinates)
	{
		mapPanel.setCurrentPath(coordinates);
		mapPanel.repaint();
	}
	public String getStart()
	{
		return List0.getSelectedValue();
	}
	public String getDestination()
	{
		return List1.getSelectedValue();
	}
	public void addStartListener(ListSelectionListener listenForSelection)
	{
		List0.addListSelectionListener(listenForSelection);
	}
	public void addDestListener(ListSelectionListener listenForSelection)
	{
		List1.addListSelectionListener(listenForSelection);
	}
	public void addResetListener(ActionListener listenForClick)
	{
		reset.addActionListener(listenForClick);
	}
	public void addFindPathtistener(ActionListener listenForClick)
	{
		find.addActionListener(listenForClick);
	}
	public void addClickListener(MouseAdapter listenForClick)
	{
		mapPanel.addMouseListener(listenForClick);
	}
}