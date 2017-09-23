package com.ey.application.model;

public class FixedAssets {
	private Integer asset_number;
	private Integer get_year;
	private Integer get_month;
	private Integer get_day;
	private String name;
	private String address;
	private Double get_price;
	private Integer amortization_type;
	private Integer the_life_year;
	private Double amotization_ratio;
	private String memo;
	
	public Integer getAsset_number() {
		return asset_number;
	}
	public void setAsset_number(Integer asset_number) {
		this.asset_number = asset_number;
	}
	public Integer getGet_year() {
		return get_year;
	}
	public void setGet_year(Integer get_year) {
		this.get_year = get_year;
	}
	public Integer getGet_month() {
		return get_month;
	}
	public void setGet_month(Integer get_month) {
		this.get_month = get_month;
	}
	public Integer getGet_day() {
		return get_day;
	}
	public void setGet_day(Integer get_day) {
		this.get_day = get_day;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getGet_price() {
		return get_price;
	}
	public void setGet_price(Double get_price) {
		this.get_price = get_price;
	}
	public Integer getAmortization_type() {
		return amortization_type;
	}
	public void setAmortization_type(Integer amortization_type) {
		this.amortization_type = amortization_type;
	}
	public Integer getThe_life_year() {
		return the_life_year;
	}
	public void setThe_life_year(Integer the_life_year) {
		this.the_life_year = the_life_year;
	}
	public Double getAmotization_ratio() {
		return amotization_ratio;
	}
	public void setAmotization_ratio(Double amotization_ratio) {
		this.amotization_ratio = amotization_ratio;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
