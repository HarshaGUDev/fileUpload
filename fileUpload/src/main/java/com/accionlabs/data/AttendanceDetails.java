package com.accionlabs.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class AttendanceDetails {

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}

	public Date getTotal_hours() {
		return total_hours;
	}

	public void setTotal_hours(Date date2) {
		this.total_hours = date2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReporting_to() {
		return reporting_to;
	}

	public void setReporting_to(String reporting_to) {
		this.reporting_to = reporting_to;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOu_name() {
		return ou_name;
	}

	public void setOu_name(String ou_name) {
		this.ou_name = ou_name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSign_in_location() {
		return sign_in_location;
	}

	public void setSign_in_location(String sign_in_location) {
		this.sign_in_location = sign_in_location;
	}

	public String getSign_out_location() {
		return sign_out_location;
	}

	public void setSign_out_location(String sign_out_location) {
		this.sign_out_location = sign_out_location;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String employee_id;
	private String employee_name;
	private String designation;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String in_time;
	private String out_time;
	@DateTimeFormat(pattern = "hh:mm:ss")
	@Temporal(TemporalType.TIME)
	private Date total_hours;
	private String status;
	private String reporting_to;
	private String location;
	private String ou_name;
	private String grade;
	private String sign_in_location;
	private String sign_out_location;
	private double total_hours_in_percent;

	public double getTotal_hours_in_percent() {
		return total_hours_in_percent;
	}

	public void setTotal_hours_in_percent(double total_hours_in_percent) {
		this.total_hours_in_percent = total_hours_in_percent;
	}
}