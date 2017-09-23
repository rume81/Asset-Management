package com.ey.application.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.*;

import com.ey.application.controller.DBManager;
import com.ey.application.model.FixedAssets;

public class frmRegistor extends JInternalFrame {
	JPanel pnlControl = new JPanel();
	JLabel lblAssetnumber = new JLabel();
	JLabel lblGetdate = new JLabel();
	JLabel lblName = new JLabel();
	JLabel lblAddress = new JLabel();
	JLabel lblGetprice = new JLabel();
	JLabel lblAmortizationtype = new JLabel();
	JLabel lblLifeyear = new JLabel();
	JLabel lblAmotizationratio = new JLabel();
	JLabel lblMemo = new JLabel();

	JText txtAssetnumber = new JText(15, 'i');
	JText txtName = new JText(100, 's');
	JText txtAddress = new JText(100, 's');
	JText txtGetprice = new JText(8, 'f');
	JText txtAmotizationratio = new JText(5, 'f');
	JTextArea txtMemo = new JTextArea();
	JText txtYY = new JText(4, 'i');
	JText txtMM = new JText(2, 'i');
	JText txtDD = new JText(2, 'i');
	JTextField txtPosition = new JTextField();

	JComboBox cboAmortizationtype;
	JComboBox cboThelifeyear = new JComboBox();

	JButton cmdAddnew = new JButton();
	JButton cmdSave = new JButton();
	JButton cmdCancel = new JButton();
	JButton cmdECancel = new JButton();
	JButton cmdSearch = new JButton();
	JButton cmdUpdate = new JButton();
	JButton cmdEdit = new JButton();
	JButton cmdReturn = new JButton();
	JButton cmdPrevious = new JButton();
	JButton cmdNext = new JButton();

	FixedAssets fa = new FixedAssets();
	private List<FixedAssets> FixedAssets_all;
	private int rowIndex = 0;

	// JTable table;

	// DefaultTableModel model = new DefaultTableModel();

	public frmRegistor() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		frmRegistor frmProduct = new frmRegistor();
		frmProduct.setVisible(true);
	}

	private void jbInit() throws Exception {
		this.setBounds(0, 0, 579, 580);
		this.getContentPane().setBackground(new Color(210, 220, 255));
		this.setForeground(Color.black);
		this.setResizable(false);
		this.setTitle("登録・修正");
		this.getContentPane().setLayout(null);

		this.setIconifiable(true);
		this.setMaximizable(false);
		this.setClosable(true);

		this.getContentPane().add(pnlControl, null);

		pnlControl.setBackground(new Color(200, 210, 255));
		pnlControl.setBorder(BorderFactory.createLoweredBevelBorder());
		pnlControl.setBounds(new Rectangle(9, 9, 555, 502));
		pnlControl.setLayout(null);

		// lblAssetnumber.setFont(new java.awt.Font("Impact", 0, 13));
		lblAssetnumber.setForeground(new Color(180, 130, 255));
		lblAssetnumber.setBorder(BorderFactory.createLoweredBevelBorder());
		lblAssetnumber.setText("固定資産番号");
		lblAssetnumber.setBounds(new Rectangle(17, 93, 110, 23));

		lblGetdate.setBounds(new Rectangle(17, 123, 110, 23));
		lblGetdate.setText("取得年月日");
		// lblGetdate.setFont(new java.awt.Font("Impact", 0, 13));
		lblGetdate.setForeground(new Color(180, 130, 255));
		lblGetdate.setBorder(BorderFactory.createLoweredBevelBorder());

		lblName.setBounds(new Rectangle(17, 153, 110, 23));
		lblName.setText("固定資産名称");
		// lblName.setFont(new java.awt.Font("Impact", 0, 13));
		lblName.setForeground(new Color(180, 130, 255));
		lblName.setBorder(BorderFactory.createLoweredBevelBorder());

		// lblAddress.setFont(new java.awt.Font("Impact", 0, 13));
		lblAddress.setForeground(new Color(180, 130, 255));
		lblAddress.setBorder(BorderFactory.createLoweredBevelBorder());
		lblAddress.setText("所在地");
		lblAddress.setBounds(new Rectangle(17, 183, 110, 23));

		// lblGetprice.setFont(new java.awt.Font("Impact", 0, 13));
		lblGetprice.setForeground(new Color(180, 130, 255));
		lblGetprice.setBorder(BorderFactory.createLoweredBevelBorder());
		lblGetprice.setText("取得価額");
		lblGetprice.setBounds(new Rectangle(17, 213, 110, 23));

		// lblAmortizationtype.setFont(new java.awt.Font("Impact", 0, 13));
		lblAmortizationtype.setForeground(new Color(180, 130, 255));
		lblAmortizationtype.setBorder(BorderFactory.createLoweredBevelBorder());
		lblAmortizationtype.setText("償却方法");
		lblAmortizationtype.setBounds(new Rectangle(17, 243, 110, 23));

		// lblLifeyear.setFont(new java.awt.Font("Impact", 0, 13));
		lblLifeyear.setForeground(new Color(180, 130, 255));
		lblLifeyear.setBorder(BorderFactory.createLoweredBevelBorder());
		lblLifeyear.setText("耐用年数");
		lblLifeyear.setBounds(new Rectangle(17, 273, 110, 23));

		// lblAmotizationratio.setFont(new java.awt.Font("Impact", 0, 13));
		lblAmotizationratio.setForeground(new Color(180, 130, 255));
		lblAmotizationratio.setBorder(BorderFactory.createLoweredBevelBorder());
		lblAmotizationratio.setText("償却率");
		lblAmotizationratio.setBounds(new Rectangle(17, 303, 110, 23));

		// lblMemo.setFont(new java.awt.Font("Impact", 0, 13));
		lblMemo.setForeground(new Color(180, 130, 255));
		lblMemo.setBorder(BorderFactory.createLoweredBevelBorder());
		lblMemo.setText("摘要");
		lblMemo.setBounds(new Rectangle(17, 333, 110, 23));

		txtAssetnumber.setBackground(new Color(230, 230, 255));
		txtAssetnumber.setBorder(BorderFactory.createLoweredBevelBorder());
		txtAssetnumber.setBounds(new Rectangle(137, 93, 405, 23));

		txtYY.setBackground(new Color(230, 230, 255));
		txtYY.setBorder(BorderFactory.createLoweredBevelBorder());
		txtYY.setBounds(new Rectangle(137, 123, 50, 23));

		txtMM.setBackground(new Color(230, 230, 255));
		txtMM.setBorder(BorderFactory.createLoweredBevelBorder());
		txtMM.setBounds(new Rectangle(189, 123, 30, 23));

		txtDD.setBackground(new Color(230, 230, 255));
		txtDD.setBorder(BorderFactory.createLoweredBevelBorder());
		txtDD.setBounds(new Rectangle(221, 123, 30, 23));

		txtName.setBackground(new Color(230, 230, 255));
		txtName.setBorder(BorderFactory.createLoweredBevelBorder());
		txtName.setBounds(new Rectangle(137, 153, 405, 23));

		txtAddress.setBackground(new Color(230, 230, 255));
		txtAddress.setBorder(BorderFactory.createLoweredBevelBorder());
		txtAddress.setBounds(new Rectangle(137, 183, 405, 23));

		txtGetprice.setBackground(new Color(230, 230, 255));
		txtGetprice.setBorder(BorderFactory.createLoweredBevelBorder());
		txtGetprice.setBounds(new Rectangle(137, 213, 405, 23));

		Vector modelo = new Vector();
		modelo.addElement(new Item(1, "定額法"));
		modelo.addElement(new Item(2, "定率法"));
		cboAmortizationtype = new JComboBox(modelo);
		cboAmortizationtype.setBackground(new Color(230, 230, 255));
		cboAmortizationtype.setBounds(new Rectangle(137, 243, 136, 23));
		// cboAmortizationtype.addItem("");
		// cboAmortizationtype.addItem("定額法");
		// cboAmortizationtype.addItem("定率法");
		cboAmortizationtype.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				setAmortizationRatio();
			}
		});

		cboThelifeyear.setBackground(new Color(230, 230, 255));
		cboThelifeyear.setBounds(new Rectangle(137, 273, 136, 23));
		FindLifeYear();
		// cboThelifeyear.addItem("");
		//for (int i = 1; i <= 100; i++)
			//cboThelifeyear.addItem(String.valueOf(i));
		cboThelifeyear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				setAmortizationRatio();
			}
		});

		txtAmotizationratio.setBackground(new Color(230, 230, 255));
		txtAmotizationratio.setBorder(BorderFactory.createLoweredBevelBorder());
		txtAmotizationratio.setBounds(new Rectangle(137, 303, 405, 23));
		txtAmotizationratio.setEditable(false);

		txtMemo.setBackground(new Color(230, 230, 255));
		txtMemo.setBorder(BorderFactory.createLoweredBevelBorder());
		txtMemo.setBounds(new Rectangle(137, 333, 405, 69));

		//cmdPrevious.setBackground(new Color(200, 210, 255));
		//cmdPrevious.setBorder(null);
		cmdPrevious.setToolTipText("前");
		cmdPrevious.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdPrevious.setBounds(new Rectangle(32, 423, 120, 28));
		cmdPrevious.setVisible(true);
		cmdPrevious.setText("前");
		cmdPrevious.setEnabled(false);
		cmdPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rowIndex--;
				if (rowIndex == 0)
					cmdPrevious.setEnabled(false);
				setGUIText(FixedAssets_all.get(rowIndex));

				if (FixedAssets_all.size() - 1 > rowIndex)
					cmdNext.setEnabled(true);
				txtPosition.setText((rowIndex + 1) + " / " + FixedAssets_all.size());
			}
		});

		txtPosition.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		txtPosition.setBounds(157, 423, 245, 28);
		txtPosition.setText("");
		txtPosition.setEditable(false);

		cmdNext.setBounds(new Rectangle(407, 423, 120, 28));
		cmdNext.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdNext.setToolTipText("次");
		//cmdNext.setBorder(null);
		//cmdNext.setBackground(new Color(200, 210, 255));
		cmdNext.setText("次");
		cmdNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rowIndex++;
				if (FixedAssets_all.size() - 1 == rowIndex)
					cmdNext.setEnabled(false);
				setGUIText(FixedAssets_all.get(rowIndex));
				if (rowIndex > 0)
					cmdPrevious.setEnabled(true);
				txtPosition.setText((rowIndex + 1) + " / " + FixedAssets_all.size());
			}
		});

		//cmdAddnew.setBackground(new Color(200, 210, 255));
		//cmdAddnew.setBorder(null);
		cmdAddnew.setToolTipText("新しく追加する");
		cmdAddnew.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdAddnew.setBounds(new Rectangle(32, 461, 120, 28));
		cmdAddnew.setVisible(true);
		cmdAddnew.setText("新しく追加する");
		cmdAddnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearGUI();
				cmdAddnew.setVisible(false);
				cmdSave.setVisible(true);
				cmdCancel.setVisible(true);
				cmdPrevious.setEnabled(false);
				cmdNext.setEnabled(false);
				changInputColor(true);
				cboThelifeyear.setSelectedItem("");
			}
		});

		//cmdSave.setBackground(new Color(200, 210, 255));
		cmdSave.setVisible(false);
		//cmdSave.setBorder(null);
		cmdSave.setToolTipText("レコードの保存");
		cmdSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdSave.setBounds(new Rectangle(32, 461, 120, 28));
		cmdSave.setVisible(true);
		cmdSave.setText("レコードの保存");
		cmdSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveRecord();
			}
		});

		cmdCancel.setBounds(new Rectangle(157, 461, 120, 28));
		cmdCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
		// cmdCancel.setMnemonic('C');
		cmdCancel.setToolTipText("操作をキャンセル");
		//cmdCancel.setBorder(null);
		// cmdCancel.setForeground(new Color(180, 130, 255));
		// cmdCancel.setFont(new java.awt.Font("Times New Roman", 1, 12));
		//cmdCancel.setBackground(new Color(200, 210, 255));
		cmdCancel.setEnabled(true);
		cmdCancel.setText("キャンセル");
		cmdCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancel();
			}
		});

		cmdSearch.setBounds(new Rectangle(282, 461, 120, 28));
		cmdSearch.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdSearch.setToolTipText("検索レコード");
		cmdSearch.setVisible(true);
		cmdSearch.setText("サーチ");
		cmdSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchRecord();
			}
		});
		
		cmdEdit.setBounds(new Rectangle(407, 23, 120, 28));
		cmdEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdEdit.setToolTipText("修正");
		cmdEdit.setVisible(true);
		cmdEdit.setText("修正");
		cmdEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAddnew.setEnabled(false);
				cmdCancel.setEnabled(false);
				cmdSearch.setEnabled(false);
				cmdECancel.setVisible(true);
				cmdEdit.setVisible(false);
				cmdUpdate.setVisible(true);
				cmdPrevious.setEnabled(false);
				cmdNext.setEnabled(false);
				txtAssetnumber.setEditable(false);
				changInputColor(true);
			}
		});
		
		cmdECancel.setBounds(new Rectangle(282, 23, 120, 28));
		cmdECancel.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdECancel.setToolTipText("キャンセル");
		cmdECancel.setEnabled(true);
		cmdECancel.setText("キャンセル");
		cmdECancel.setVisible(false);
		cmdECancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmdAddnew.setEnabled(true);
				cmdCancel.setEnabled(true);
				cmdSearch.setEnabled(true);
				cmdECancel.setVisible(false);
				cmdEdit.setVisible(true);
				cmdUpdate.setVisible(false);
				
				if(rowIndex > 0)
					cmdPrevious.setEnabled(true);
				else
					cmdPrevious.setEnabled(false);
				
				if(rowIndex < FixedAssets_all.size()-1)
					cmdNext.setEnabled(true);
				else
					cmdNext.setEnabled(false);
				txtAssetnumber.setEditable(true);
				changInputColor(false);
			}
		});

		cmdUpdate.setBounds(new Rectangle(407, 23, 120, 28));
		cmdUpdate.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdUpdate.setToolTipText("更新");
		cmdUpdate.setVisible(false);
		cmdUpdate.setText("更新");
		cmdUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveEdit();
			}
		});

		cmdReturn.setBounds(new Rectangle(407, 461, 120, 28));
		cmdReturn.setVerticalTextPosition(SwingConstants.BOTTOM);
		// cmdReturn.setMnemonic('R');
		cmdReturn.setToolTipText("メインに戻ります");
		//cmdReturn.setBorder(null);
		// cmdReturn.setForeground(new Color(180, 130, 255));
		//cmdReturn.setBackground(new Color(200, 210, 255));
		cmdReturn.setText("戻る");
		cmdReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		pnlControl.add(lblAssetnumber, null);
		pnlControl.add(lblGetdate, null);
		pnlControl.add(lblName, null);
		pnlControl.add(lblAddress, null);
		pnlControl.add(lblGetprice, null);
		pnlControl.add(lblAmortizationtype, null);
		pnlControl.add(lblLifeyear, null);
		pnlControl.add(lblAmotizationratio, null);
		pnlControl.add(lblMemo, null);

		pnlControl.add(txtAssetnumber, null);
		pnlControl.add(txtYY, null);
		pnlControl.add(txtMM, null);
		pnlControl.add(txtDD, null);
		pnlControl.add(txtName, null);
		pnlControl.add(txtAddress, null);
		pnlControl.add(txtGetprice, null);
		pnlControl.add(cboAmortizationtype);
		pnlControl.add(cboThelifeyear);
		pnlControl.add(txtAmotizationratio, null);
		pnlControl.add(txtMemo, null);

		pnlControl.add(cmdSearch, null);
		pnlControl.add(cmdUpdate, null);
		pnlControl.add(cmdEdit, null);
		pnlControl.add(cmdECancel,null);
		pnlControl.add(cmdReturn, null);
		pnlControl.add(cmdAddnew, null);
		pnlControl.add(cmdSave, null);
		pnlControl.add(cmdCancel, null);
		pnlControl.add(cmdPrevious, null);
		pnlControl.add(txtPosition, null);
		pnlControl.add(cmdNext, null);

		ScrollRecord();
		setGUIText(FixedAssets_all.get(rowIndex));
		txtPosition.setText((rowIndex + 1) + " / " + FixedAssets_all.size());
	}

	public void SaveRecord() {
		getFixedAssets();
		if (txtAssetnumber.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "資産番号を挿入してください", "エラー情報", JOptionPane.OK_OPTION);
			return;
		}

		if (FindAssets(txtAssetnumber.getText())) {
			JOptionPane.showMessageDialog(null, "資産番号は、データベースに存在します", "エラー情報", JOptionPane.OK_OPTION);
			return;
		}

		if (txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "資産名を挿入してください", "エラー情報", JOptionPane.OK_OPTION);
			return;
		}

		if (txtGetprice.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "価格を挿入してください", "エラー情報", JOptionPane.OK_OPTION);
			return;
		}

		PutData();
	}

	public void PutData() {

		String inQry = "INSERT into Fixed_Assets (asset_number, get_year,get_month,get_day,name,address,get_price,amortization_type,the_life_year,amotization_ratio,memo)"
				+ "values(" + fa.getAsset_number() + "," + fa.getGet_year() + "," + fa.getGet_month() + ","
				+ fa.getGet_day() + ",'" + fa.getName() + "','" + fa.getAddress() + "'," + fa.getGet_price() + ","
				+ fa.getAmortization_type() + "," + fa.getThe_life_year() + "," + fa.getAmotization_ratio() + ",'"
				+ fa.getMemo() + "')";

		// System.out.println(inQry);
		try {
			DBManager dbI = new DBManager();
			try {
				boolean i = dbI.doQuery(inQry);

				if (i) {
					JOptionPane.showMessageDialog(null, "固定資産の情報が保存されました");
					ClearGUI();
					changInputColor(false);
					cmdAddnew.setVisible(true);
					cmdSave.setVisible(false);
					ScrollRecord();
					rowIndex = 0;
					setGUIText(FixedAssets_all.get(rowIndex));
					txtPosition.setText((rowIndex + 1) + " / " + FixedAssets_all.size());
					cmdPrevious.setEnabled(false);
					cmdNext.setEnabled(true);
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbI.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SaveEdit() {
		getFixedAssets();
		String EditQry = "UPDATE Fixed_Assets SET get_year=" + fa.getGet_year() + ", get_month=" + fa.getGet_month()
				+ ", get_day=" + fa.getGet_day() + ", name='" + fa.getName() + "', address='" + fa.getAddress()
				+ "', get_price=" + fa.getGet_price() + ", amortization_type=" + fa.getAmortization_type()
				+ ", the_life_year=" + fa.getThe_life_year() + ", amotization_ratio=" + fa.getAmotization_ratio()
				+ ", memo='" + fa.getMemo() + "'  WHERE asset_number=" + fa.getAsset_number();

		// System.out.println(EditQry);
		try {
			DBManager dbP = new DBManager();
			try {
				boolean PFo = dbP.doQuery(EditQry);

				if (PFo) {
					JOptionPane.showMessageDialog(null, "固定資産の情報が更新されました");
					ClearGUI();

					ScrollRecord();
					rowIndex = 0;
					setGUIText(FixedAssets_all.get(rowIndex));
					txtPosition.setText((rowIndex + 1) + " / " + FixedAssets_all.size());
					cmdPrevious.setEnabled(false);
					cmdNext.setEnabled(true);
					txtAssetnumber.setEditable(true);
								
					cmdAddnew.setEnabled(true);
					cmdCancel.setEnabled(true);
					cmdSearch.setEnabled(true);
					cmdECancel.setVisible(false);
					cmdEdit.setVisible(true);
					cmdUpdate.setVisible(false);
					
					changInputColor(false);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex, "エラー", JOptionPane.ERROR_MESSAGE);
			} finally {
				dbP.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SearchRecord() {
		String val = JOptionPane.showInputDialog(null, "検索の資産番号を挿入してください", "サーチ", JOptionPane.DEFAULT_OPTION);
		if (null == val)
			return;
		boolean Fo = false;
		for(int i=0;i<FixedAssets_all.size();i++)
		{
			if(String.valueOf(FixedAssets_all.get(i).getAsset_number()).equals(val))
			{
				Fo = true;
				rowIndex = i;
				break;
			}
		}
		if(Fo)
		{
			setGUIText(FixedAssets_all.get(rowIndex));
			txtPosition.setText((rowIndex + 1) + " / " + FixedAssets_all.size());
			if(rowIndex > 0)
				cmdPrevious.setEnabled(true);
			else
				cmdPrevious.setEnabled(false);
			
			if(rowIndex < FixedAssets_all.size()-1)
				cmdNext.setEnabled(true);
			else
				cmdNext.setEnabled(false);
		}
		else {
			JOptionPane.showMessageDialog(null, "検索データが見つかりませんが、有効なデータをお試しください", "エラー", JOptionPane.ERROR_MESSAGE);
		}
		/*try {
			DBManager db = new DBManager();
			try {
				ResultSet rs;
				rs = db.getRecord("Select * from Fixed_Assets where asset_number=" + val + "");
				if (rs.next()) {
					Fo = true;
					fa.setAsset_number(rs.getInt(2));
					fa.setGet_year(rs.getInt(3));
					fa.setGet_month(rs.getInt(4));
					fa.setGet_day(rs.getInt(5));
					fa.setName(rs.getString(6));
					fa.setAddress(rs.getString(7));
					fa.setGet_price(rs.getDouble(8));
					fa.setAmortization_type(rs.getInt(9));
					fa.setThe_life_year(rs.getInt(10));
					fa.setAmotization_ratio(rs.getDouble(11));
					fa.setMemo(rs.getString(12));
					setGUIText();
				}
				if (Fo) {
					cmdCancel.setVisible(true);
					cmdAddnew.setEnabled(false);
					cmdPrevious.setEnabled(false);
					cmdNext.setEnabled(false);
					
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "検索データが見つかりませんが、有効なデータをお試しください", "エラー", JOptionPane.ERROR_MESSAGE);
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
	}

	public void getFixedAssets() {

		try {
			fa.setAsset_number(Integer.parseInt(txtAssetnumber.getText()));
		} catch (NumberFormatException n) {
			txtAssetnumber.setText("");
		}
		fa.setName(txtName.getText());
		fa.setAddress(txtAddress.getText());
		try {
			String price = getNumericValue(txtGetprice.getText());
			fa.setGet_price(Double.parseDouble(price));
		} catch (NumberFormatException n) {
			txtGetprice.setText("");
		}
		try {
			fa.setAmotization_ratio(Double.parseDouble(txtAmotizationratio.getText()));
		} catch (NumberFormatException n) {
			txtAmotizationratio.setText("");
		}

		fa.setGet_year(Integer.parseInt(txtYY.getText()));
		fa.setGet_month(Integer.parseInt(txtMM.getText()));
		fa.setGet_day(Integer.parseInt(txtDD.getText()));
		fa.setMemo(txtMemo.getText());

		Item item = (Item) cboAmortizationtype.getSelectedItem();

		fa.setAmortization_type(item.getId());
		if(String.valueOf(cboThelifeyear.getSelectedItem()).equals(""))
			fa.setThe_life_year(0);
		else
			fa.setThe_life_year(Integer.parseInt(String.valueOf(cboThelifeyear.getSelectedItem())));

	}



	public void setGUIText() {
		txtAssetnumber.setText(String.valueOf(fa.getAsset_number()));
		txtName.setText(fa.getName());
		txtAddress.setText(fa.getAddress());
		txtGetprice.setText(String.valueOf(fa.getGet_price()));
		txtAmotizationratio.setText(String.valueOf(fa.getAmotization_ratio()));
		txtMemo.setText(fa.getMemo());
		txtYY.setText(String.valueOf(fa.getGet_year()));
		txtMM.setText(String.valueOf(fa.getGet_month()));
		txtDD.setText(String.valueOf(fa.getGet_day()));

		Vector modelo = new Vector();
		if (fa.getAmortization_type() == 1)
			modelo.addElement(new Item(1, "定額法"));
		else
			modelo.addElement(new Item(2, "定率法"));
		cboAmortizationtype.setSelectedItem(modelo);
		cboThelifeyear.setSelectedItem(String.valueOf(fa.getThe_life_year()));
	}

	public void setGUIText(FixedAssets faa) {
		txtAssetnumber.setText(String.valueOf(faa.getAsset_number()));
		txtName.setText(faa.getName());
		txtAddress.setText(faa.getAddress());
		DecimalFormat formatter = new DecimalFormat("#,###");
		txtGetprice.setText(String.valueOf(formatter.format(faa.getGet_price())));
		txtAmotizationratio.setText(String.valueOf(faa.getAmotization_ratio()));
		txtMemo.setText(faa.getMemo());
		txtYY.setText(String.valueOf(faa.getGet_year()));
		txtMM.setText(String.valueOf(faa.getGet_month()));
		txtDD.setText(String.valueOf(faa.getGet_day()));

		Vector modelo = new Vector();
		if (faa.getAmortization_type() == 1)
			modelo.addElement(new Item(1, "定額法"));
		else
			modelo.addElement(new Item(2, "定率法"));
		cboAmortizationtype.setSelectedItem(modelo);
		if(faa.getThe_life_year() == 0)
			cboThelifeyear.setSelectedItem("");
		else
			cboThelifeyear.setSelectedItem(String.valueOf(faa.getThe_life_year()));
	}

	public void ClearGUI() {
		txtAssetnumber.setText("");
		txtName.setText("");
		txtAddress.setText("");
		txtGetprice.setText("");
		txtAmotizationratio.setText("");
		txtMemo.setText("");
		txtYY.setText("");
		txtMM.setText("");
		txtDD.setText("");

		Vector modelo = new Vector();
		modelo.addElement(new Item(1, "定額法"));
		cboAmortizationtype.setSelectedItem(modelo);
		cboThelifeyear.setSelectedItem("1");
	}

	public void setAmortizationRatio() {

		Item item = (Item) cboAmortizationtype.getSelectedItem();
		if(!String.valueOf(cboThelifeyear.getSelectedItem()).equals(""))
		{
			try {
				DBManager db = new DBManager();
				try {
					ResultSet rsData;
					rsData = db.getRecord("SELECT amortization_ratio FROM Amortization_Ratio WHERE amortization_type="
							+ item.getId() + " AND amortization_year=" + cboThelifeyear.getSelectedItem());
					if (rsData.next()) {
						txtAmotizationratio.setText(rsData.getString(1));
					} else {
						txtAmotizationratio.setText("");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void Cancel() {
		ClearGUI();
		changInputColor(false);
		cmdSearch.setVisible(true);
		cmdUpdate.setVisible(false);
		cmdAddnew.setVisible(true);
		cmdSave.setVisible(false);
		cmdAddnew.setEnabled(true);
		cmdCancel.setVisible(true);
		ScrollRecord();
		rowIndex = 0;
		setGUIText(FixedAssets_all.get(rowIndex));
		txtPosition.setText((rowIndex + 1) + " / " + FixedAssets_all.size());
		cmdPrevious.setEnabled(false);
		cmdNext.setEnabled(true);
	}
	
	public void changInputColor(boolean state)
	{
		if(state)
		{
			txtAssetnumber.setBackground(new Color(255, 255, 255));
			txtYY.setBackground(new Color(255, 255, 255));
			txtMM.setBackground(new Color(255, 255, 255));
			txtDD.setBackground(new Color(255, 255, 255));
			txtName.setBackground(new Color(255, 255, 255));
			txtAddress.setBackground(new Color(255, 255, 255));
			txtGetprice.setBackground(new Color(255, 255, 255));
			cboAmortizationtype.setBackground(new Color(255, 255, 255));
			cboThelifeyear.setBackground(new Color(255, 255, 255));
			txtAmotizationratio.setBackground(new Color(255, 255, 255));
			txtMemo.setBackground(new Color(255, 255, 255));
		} else{
			txtAssetnumber.setBackground(new Color(230, 230, 255));
			txtYY.setBackground(new Color(230, 230, 255));
			txtMM.setBackground(new Color(230, 230, 255));
			txtDD.setBackground(new Color(230, 230, 255));
			txtName.setBackground(new Color(230, 230, 255));
			txtAddress.setBackground(new Color(230, 230, 255));
			txtGetprice.setBackground(new Color(230, 230, 255));
			cboAmortizationtype.setBackground(new Color(230, 230, 255));
			cboThelifeyear.setBackground(new Color(230, 230, 255));
			txtAmotizationratio.setBackground(new Color(230, 230, 255));
			txtMemo.setBackground(new Color(230, 230, 255));
		}
		
	}

	public void ScrollRecord() {
		try {
			DBManager db = new DBManager();
			try {
				FixedAssets_all = new ArrayList<FixedAssets>();
				ResultSet rs;
				rs = db.getRecord("Select * from Fixed_Assets");
				while (rs.next()) {
					FixedAssets fasset = new FixedAssets();
					fasset.setAsset_number(rs.getInt(2));
					fasset.setGet_year(rs.getInt(3));
					fasset.setGet_month(rs.getInt(4));
					fasset.setGet_day(rs.getInt(5));
					fasset.setName(rs.getString(6));
					fasset.setAddress(rs.getString(7));
					fasset.setGet_price(rs.getDouble(8));
					fasset.setAmortization_type(rs.getInt(9));
					fasset.setThe_life_year(rs.getInt(10));
					fasset.setAmotization_ratio(rs.getDouble(11));
					fasset.setMemo(rs.getString(12));
					FixedAssets_all.add(fasset);
					// setGUIText();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getNumericValue(String text) {
		String output = "";
		for (int i = 0; i < text.length(); i++) {
			if (Character.isDigit(text.charAt(i)))
				output = output + text.charAt(i);
			else if (text.charAt(i) == 46)
				output = output + text.charAt(i);
		}
		return output;
	}

	public boolean FindAssets(String AssetNo) {
		boolean Fo = false;
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("Select * from Fixed_Assets where asset_number=" + AssetNo + "");
				if (rs.next()) {
					Fo = true;
				}
			} catch (SQLException ex) {

			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Fo;
	}
	
	public void FindLifeYear() {
		
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("Select DISTINCT amortization_year from Amortization_Ratio");
				cboThelifeyear.addItem("");
				while(rs.next()) {
					cboThelifeyear.addItem(String.valueOf(rs.getInt(1)));
				}
			} catch (SQLException ex) {

			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class Item {
		private int id;
		private String description;

		public Item(int id, String description) {
			this.id = id;
			this.description = description;
		}

		public int getId() {
			return id;
		}

		public String getDescription() {
			return description;
		}

		public String toString() {
			return description;
		}
	}
}
