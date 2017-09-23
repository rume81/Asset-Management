package com.ey.application.view;

import java.awt.*;
import javax.swing.*;

import com.ey.application.controller.DBManager;

import java.awt.event.*;
import java.sql.*;

public class frmEY extends JFrame {
	JMenuBar EYMenu = new JMenuBar();

	JMenu mnuFile = new JMenu();
	JMenu mnuHelp = new JMenu();

	JToolBar toolbar = new JToolBar();

	JLabel status = new JLabel();

	JButton cmdRegistor = new JButton();
	JButton cmdPrintreport = new JButton();

	JMenuItem mnRegistor = new JMenuItem();
	JMenuItem mnmnPrintreport = new JMenuItem();
	JMenuItem mnExit = new JMenuItem();
	JMenuItem mnHelp = new JMenuItem();
	JMenuItem mnAbout = new JMenuItem();

	JDesktopPane Desktop = new JDesktopPane();

	public frmEY() {
		try {
			jbInit();
			DBManager db = new DBManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		if(System.getProperty("os.name").contains("Windows"))
			setLookup();
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, ScreenSize.width, ScreenSize.height - 30);
		this.setTitle("EY資産");
		this.setJMenuBar(EYMenu);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
			}

			public void windowDeativated(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				exitMsg();
			}
		});

		//mnuFile.setMnemonic('F');
		mnuFile.setText("ファイル");

		//mnuHelp.setMnemonic('H');
		mnuHelp.setText("Help");

		//mnRegistor.setIcon(new ImageIcon("IMAGE/Item.png"));
		mnRegistor.setMnemonic('I');
		mnRegistor.setText("登録・修正");
		mnRegistor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRegistor frmRegistor = new frmRegistor();
				createFrame(frmRegistor);
			}
		});

		//mnmnPrintreport.setIcon(new ImageIcon("IMAGE/Receive.png"));
		//mnmnPrintreport.setMnemonic('M');
		mnmnPrintreport.setText("帳票印刷");
		mnmnPrintreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPrintReport frmPrintReport = new frmPrintReport();
				createFrame(frmPrintReport);
			}
		});

		//mnExit.setMnemonic('X');
		mnExit.setText("出口");
		mnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitMsg();
			}
		});

		//mnHelp.setIcon(new ImageIcon("IMAGE/Help.gif"));
		//mnHelp.setMnemonic('H');
		mnHelp.setText("ヘルプトピック");

		//mnAbout.setMnemonic('A');
		mnAbout.setText("このソフトについて");
		mnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		toolbar.setBounds(new Rectangle(0, 27, 400, 34));
		// toolbar.setBorder(BorderFactory.createEtchedBorder());

		status.setFont(new java.awt.Font("Times New Romans", 3, 10));
		//status.setText("Developed By: ");
		status.setBounds(new Rectangle(-1, 284, 402, 17));

		cmdRegistor.setToolTipText("登録・修正");
		cmdRegistor.setText("登録・修正");
		//cmdRegistor.setIcon(new ImageIcon("IMAGE/Item.png"));
		cmdRegistor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRegistor frmRegistor = new frmRegistor();
				createFrame(frmRegistor);
			}
		});

		cmdPrintreport.setToolTipText("帳票印刷");
		//cmdPrintreport.setIcon(new ImageIcon("IMAGE/Receive.png"));
		cmdPrintreport.setText("帳票印刷");
		cmdPrintreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPrintReport frmPrintReport = new frmPrintReport();
				createFrame(frmPrintReport);
			}
		});

		this.getContentPane().add(toolbar, BorderLayout.NORTH);
		this.getContentPane().add(status, BorderLayout.SOUTH);
		this.getContentPane().add(Desktop, BorderLayout.CENTER);

		EYMenu.add(mnuFile);
		EYMenu.add(mnuHelp);

		mnuFile.add(mnRegistor);
		mnuFile.add(mnmnPrintreport);
		mnuFile.addSeparator();
		mnuFile.add(mnExit);

		toolbar.add(cmdRegistor, null);
		toolbar.addSeparator();
		toolbar.add(cmdPrintreport, null);		

		mnuHelp.add(mnHelp);
		mnuHelp.addSeparator();
		mnuHelp.add(mnAbout);

		Desktop.setBackground(new Color(50, 102, 204));

		loadBackgroundImage();

		// setUserMenu();

	}

	/*
	 * public void setUserMenu() { cmdUser.setEnabled(true); }
	 */

	protected void loadBackgroundImage() {
		ImageIcon icon = new ImageIcon("");

		int x = (this.getWidth() - icon.getIconWidth()) / 2;
		int y = (this.getHeight() - icon.getIconHeight()) / 2;

		JLabel l = new JLabel(icon);
		l.setBounds(x, y - 30, icon.getIconWidth(), icon.getIconHeight());

		// Place the image in the lowest possible layer so nothing
		// can ever be painted under it.
		Desktop.add(l, new Integer(Integer.MIN_VALUE));
	}

	public void createFrame(JInternalFrame frm) {
		JInternalFrame frame = frm;
		frame.setVisible(true);
		Desktop.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
	}
	public javax.swing.JDesktopPane getDesktopPane() {
	    return Desktop;
	}
	protected void exitMsg() {
		int opt = JOptionPane.showConfirmDialog(null, "あなたは、アプリケーションを終了しますか？",
				"終了確認", JOptionPane.YES_NO_OPTION);
		if (opt == JOptionPane.YES_OPTION) {
			/*
			 * DBManager db = new DBManager(); try { db.close(); } catch
			 * (SQLException ex) { System.out.println(ex); }
			 */
			System.exit(0);
		}
	}

	public void setLookup() {
		/*
		 * String lookAndFeel =
		 * "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"; String
		 * lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		 * String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
		 */

		try {
			UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
			// looks[0] = Default/ Metaal Style
			// looks[1] = Motif Style
			// looks[2] = Windows Style
			UIManager.setLookAndFeel(looks[1].getClassName());
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			// UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * try{ javax.swing.UIManager.setLookAndFeel(
		 * "com.birosoft.liquid.LiquidLookAndFeel"); } catch (Exception e) {
		 * System.out.println("Error Loading Theme:" + e.toString()); //If
		 * Failed to load the liquid them then load my own XPStyleTheme
		 * //MetalTheme myXPStyleTheme = new XPStyleTheme();
		 * //MetalLookAndFeel.setCurrentTheme(myXPStyleTheme); try {
		 * //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel")
		 * ; UIManager.LookAndFeelInfo looks[] =
		 * UIManager.getInstalledLookAndFeels();
		 * UIManager.setLookAndFeel(looks[2].getClassName()); } catch (Exception
		 * err) { System.out.println("Error loading myXPStyleTheme:" +
		 * err.toString()); } }
		 */
	}
}
