package com.ey.application.model;

public class AmortizationRatios {
	
	private Integer amortization_type;
	private Integer amortization_year;
	private Double amortization_ratio;
	
	public Integer getAmortization_type() {
		return amortization_type;
	}
	public void setAmortization_type(Integer amortization_type) {
		this.amortization_type = amortization_type;
	}
	public Integer getAmortization_year() {
		return amortization_year;
	}
	public void setAmortization_year(Integer amortization_year) {
		this.amortization_year = amortization_year;
	}
	public Double getAmortization_ratio() {
		return amortization_ratio;
	}
	public void setAmortization_ratio(Double amortization_ratio) {
		this.amortization_ratio = amortization_ratio;
	}
}
