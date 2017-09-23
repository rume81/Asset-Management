package com.ey.application.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import com.ey.application.controller.DBManager;
import com.ey.application.model.DataBean;

public class DataBeanList {
	
	private double amortization_priod_price;
	private double amortization_total_price;
	private double on_book_price;

	public ArrayList<DataBean> getDataBeanList(String m, String y, String r) {
		ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();

		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				String sql = "Select * from Fixed_Assets WHERE get_year <=" + y /*+ " AND get_month <=" + m*/;
				//String sql = "Select * from Fixed_Assets";
				//System.out.println(sql);
				rs = db.getRecord(sql);
				while (rs.next()) {
						
						int Asset_number = rs.getInt(2);
						int year = rs.getInt(3);
						int month = rs.getInt(4);
						int day = rs.getInt(5);
						String date = String.valueOf(year) + "  " + String.valueOf(month) + "  " + String.valueOf(day);
						String name = rs.getString(6);
						String address = rs.getString(7);
						double get_price = rs.getDouble(8);
						int type = rs.getInt(9);
						int life_year = rs.getInt(10);
						double Amotization_ratio = rs.getDouble(11);
						
						if(year == Integer.parseInt(y) && month > Integer.parseInt(m)){
							
						} else{
							CalculateAmotization(m, y, r, type, month, get_price,Amotization_ratio,year);
							
							
		
							dataBeanList
									.add(produce(String.valueOf(Asset_number), date, name, address, (int) Math.round(get_price), (int) Math.round(amortization_priod_price), (int) Math.round(amortization_total_price),
											(int) Math.round(on_book_price), (type == 1) ? "定額法" : "定率法", String.valueOf(life_year), Amotization_ratio));
						}
					}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "検索データが見つかりませんが、有効なデータをお試しください", "エラー", JOptionPane.ERROR_MESSAGE);
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataBeanList;
	}

	/**
	 * This method returns a DataBean object
	 */
	private DataBean produce(String asset_number, String get_date, String name, String adress, int get_price,
			int amortization_priod_price, int amortization_total_price, int on_book_price,
			String amortization_type, String the_life_year, double amotization_ratio) {
		DataBean dataBean = new DataBean();
		dataBean.setAsset_number(asset_number);
		dataBean.setGet_date(get_date);
		dataBean.setName(name);
		dataBean.setAdress(adress);
		dataBean.setGet_price(get_price);
		dataBean.setAmortization_priod_price(amortization_priod_price);
		dataBean.setAmortization_total_price(amortization_total_price);
		dataBean.setOn_book_price(on_book_price);
		dataBean.setAmortization_type(amortization_type);
		dataBean.setThe_life_year(the_life_year);
		dataBean.setAmotization_ratio(amotization_ratio);
		return dataBean;
	}

	public void CalculateAmotization(String mon, String year, String range, int type, int fixed_assets_get_month,
			double fixed_assets_get_price, double fixed_assets_amotization_ratio, double fixed_assets_get_year) {
		int closing_year;
		int closing_month;
		int first_year_month;
		double first_year_total_price;
		double all_year_total_price;
		amortization_priod_price = 0.0;
		amortization_total_price =  0.0;
		on_book_price =  0.0;
		
		closing_year = Integer.parseInt(year);
		closing_month = Integer.parseInt(mon);

		if (type == 1) {
			// calculate first year
			if (fixed_assets_get_month <= 12)
				first_year_month = closing_month + 13 - fixed_assets_get_month;
			else
				first_year_month = closing_month - fixed_assets_get_month + 1;

			first_year_total_price = (int) (fixed_assets_get_price * 0.9 * fixed_assets_amotization_ratio / 12
					* first_year_month);
			all_year_total_price = first_year_total_price;

			// calculate second year
			if (fixed_assets_get_month <= 12)
				first_year_total_price = first_year_total_price
						+ (fixed_assets_get_price * 0.9 * fixed_assets_amotization_ratio)
								* (closing_year - fixed_assets_get_year - 2);
			else
				first_year_total_price = first_year_total_price
						+ (fixed_assets_get_price * 0.9 * fixed_assets_amotization_ratio)
								* (closing_year - fixed_assets_get_year - 3);

			if (first_year_total_price < 0)
				first_year_total_price = 0;

			if (fixed_assets_get_month <= 12)
				all_year_total_price = all_year_total_price
						+ (fixed_assets_get_price * 0.9 * fixed_assets_amotization_ratio)
								* (closing_year - fixed_assets_get_year - 1);
			else
				all_year_total_price = all_year_total_price
						+ (fixed_assets_get_price * 0.9 * fixed_assets_amotization_ratio)
								* (closing_year - fixed_assets_get_year - 2);

			if (first_year_total_price > fixed_assets_get_price * 0.95)
				first_year_total_price = fixed_assets_get_price * 0.95;

			if (all_year_total_price > fixed_assets_get_price * 0.95)
				all_year_total_price = fixed_assets_get_price * 0.95;

			amortization_priod_price = all_year_total_price - first_year_total_price;
			amortization_total_price = all_year_total_price;
			on_book_price = fixed_assets_get_price - all_year_total_price;

		} else {
			// we don’t have to make program.
		}
	}
}
