package com.accionlabs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accionlabs.data.AttendanceDetails;
import com.accionlabs.data.EmployeeDetails;
import com.accionlabs.data.ResponseMessage;

@Service
public class EmployeeService {

	public ResponseMessage saveEmployeeDetails(MultipartFile file) throws IOException {
		
		ResponseMessage responseMessage = new ResponseMessage();
		String empNo = null;
		String empFirstName=null;
		String message = null;
		SessionFactory sf = new Configuration().configure("employee.cfg.xml").buildSessionFactory();
		Session session = null;
		Workbook workbook = null;
		Transaction t = null;
		try {
			session = sf.openSession();
			workbook = new HSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			Row row;
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				EmployeeDetails employeeDetails = new EmployeeDetails();
				if (sheet.getRow(i) != null) {
					row = (Row) sheet.getRow(i); // sheet number

					if (row.getCell(0) == null) {
						System.out.println("invalid details");
					} else {
						if (row.getCell(0) != null) {

							employeeDetails.setEmployee_id(row.getCell(0).toString());
							empNo = row.getCell(0).toString();
							empFirstName=row.getCell(2).toString();
							System.out.println("empNo "+empNo);
							String name = null;
							String name1=null;
							
							if (empNo.contains(".0")) {
								System.out.println("empNo "+empNo);
								name = empNo.replace(".", "");		
								name1=name.substring(0,name.length()-1);
								System.out.println(name1);
							}else{
								name1=empNo;
							}
							
							String absolutePath = "\\accion_emp_images";
							File dir = new File(absolutePath);

							File[] filesInDir = dir.listFiles();
							for (File images : filesInDir) {
				
								File file2 = new File(images.getPath());
						        byte[] bFile = new byte[(int) file2.length()];
						        
						 
						        try {
						            FileInputStream fileInputStream = new FileInputStream(file2);
						            fileInputStream.read(bFile);
						            
						            fileInputStream.close();
						        } catch (Exception e) {
						            e.printStackTrace();
						        }
						 
						     
								if (images.getName().contains(name1) && images.getName().contains(empFirstName)) {
									System.out.println(name1 + "    " + images.getName());
									if(bFile.length<1048576){
									 employeeDetails.setImage(bFile);
									}
								}
							}
						}
						if (row.getCell(1) != null) {
							employeeDetails.setEmployee_name(row.getCell(1).toString());
						}
						if (row.getCell(2) != null) {
							employeeDetails.setFirst_name(row.getCell(2).toString());
						}
						if (row.getCell(3) != null) {
							employeeDetails.setLast_name(row.getCell(3).toString());
						}
						if (row.getCell(4) != null) {
							employeeDetails.setMiddle_name(row.getCell(4).toString());
						}
						if (row.getCell(5) != null) {
							employeeDetails.setSalutation(row.getCell(5).toString());
						}
						if (row.getCell(6) != null) {
							employeeDetails.setEmail_Id(row.getCell(6).toString());
						}
						if (row.getCell(7) != null) {
							try {
								String name = row.getCell(7).toString().split("[\\(\\)]")[1];
								employeeDetails.setReporting_to_by_id(name);

							} catch (ArrayIndexOutOfBoundsException e) {
								// TODO: handle exception
							}
							employeeDetails.setReporting_to(row.getCell(7).toString());
						}
						if (row.getCell(8) != null) {
							employeeDetails.setFunctional_reporting_to(row.getCell(8).toString());
						}
						if (row.getCell(9) != null) {
							employeeDetails.setOu_name(row.getCell(9).toString());
						}
						if (row.getCell(10) != null) {
							employeeDetails.setDesignation_name(row.getCell(10).toString());
						}
						if (row.getCell(11) != null) {
							employeeDetails.setGrade_name(row.getCell(11).toString());
						}
						if (row.getCell(12) != null) {
							employeeDetails.setDepartment_name(row.getCell(12).toString());
						}

						if (row.getCell(13) != null) {
							employeeDetails.setPosition(row.getCell(13).toString());
						}
						if (row.getCell(14) != null) {
							employeeDetails.setEmployment_status(row.getCell(14).toString());
						}
						if (row.getCell(15) != null) {
							employeeDetails.setEmployee_status(row.getCell(15).toString());
						}
						if (row.getCell(16) != null) {
							employeeDetails.setEmployment_type(row.getCell(16).toString());
						}
						if (row.getCell(17) != null) {
							employeeDetails.setDate_of_joining(row.getCell(17).toString());
						}
						if (row.getCell(18) != null) {
							employeeDetails.setConfirmation_date(row.getCell(18).toString());
						}
						if (row.getCell(19) != null) {
							employeeDetails.setConfirmation_due_date(row.getCell(19).toString());
						}
						if (row.getCell(20) != null) {
							employeeDetails.setContract_end_date(row.getCell(20).toString());
						}
						if (row.getCell(21) != null) {
							employeeDetails.setAge(row.getCell(21).getNumericCellValue());
						}
						if (row.getCell(22) != null) {
							employeeDetails.setDate_of_birth(row.getCell(22).toString());
						}

						if (row.getCell(23) != null) {
							employeeDetails.setGender(row.getCell(23).toString());
						}
						if (row.getCell(24) != null) {
							employeeDetails.setAddress1(row.getCell(24).toString());
						}
						if (row.getCell(25) != null) {
							employeeDetails.setAddress2(row.getCell(25).toString());
						}
						if (row.getCell(26) != null) {
							employeeDetails.setCurrent_city(row.getCell(26).toString());
						}
						if (row.getCell(27) != null) {
							employeeDetails.setCurrent_state(row.getCell(27).toString());
						}
						if (row.getCell(28) != null) {
							employeeDetails.setCurrent_country(row.getCell(28).toString());
						}
						if (row.getCell(29) != null) {
							employeeDetails.setWork_mobile_number(row.getCell(29).toString());
						}
						if (row.getCell(30) != null) {
							employeeDetails.setPermanent_pincode(row.getCell(30).getNumericCellValue());
						}

						if (row.getCell(31) != null) {
							employeeDetails.setWork_phone((row.getCell(31).toString()));

						}
						if (row.getCell(32) != null) {
							employeeDetails.setPersonal_email(row.getCell(32).toString());
						}

						if (row.getCell(33) != null) {
							employeeDetails.setPermanent_address1(row.getCell(33).toString());
						}
						if (row.getCell(34) != null) {
							employeeDetails.setPermanent_address2(row.getCell(34).toString());
						}
						if (row.getCell(35) != null) {
							employeeDetails.setPermanent_address3(row.getCell(35).toString());
						}
						if (row.getCell(36) != null) {
							employeeDetails.setPermanent_city(row.getCell(36).toString());
						}
						if (row.getCell(37) != null) {
							employeeDetails.setPermanent_state(row.getCell(37).toString());
						}
						if (row.getCell(38) != null) {
							employeeDetails.setPermanent_country(row.getCell(38).toString());
						}
						if (row.getCell(39) != null) {
							employeeDetails.setPan_number(row.getCell(39).toString());
						}
						if (row.getCell(40) != null) {
							employeeDetails.setRegion(row.getCell(40).toString());
						}
						if (row.getCell(41) != null) {
							employeeDetails.setCity_name(row.getCell(41).toString());
						}
						if (row.getCell(42) != null) {
							employeeDetails.setLocation(row.getCell(42).toString());
						}

						if (row.getCell(43) != null) {
							employeeDetails.setLocationaddress(row.getCell(43).toString());
						}
						if (row.getCell(44) != null) {
							employeeDetails.setLocationstate(row.getCell(44).toString());
						}
						if (row.getCell(45) != null) {
							employeeDetails.setAdded_on(row.getCell(45).toString());
						}
						if (row.getCell(46) != null) {
							employeeDetails.setAdded_by(row.getCell(46).toString());
						}
						if (row.getCell(47) != null) {
							employeeDetails.setCalendar_name(row.getCell(47).toString());
						}
						if (row.getCell(48) != null) {
							employeeDetails.setRelieving_date(row.getCell(48).toString());
						}
						if (row.getCell(49) != null) {
							employeeDetails.setProject(row.getCell(49).toString());
						}
						if (row.getCell(50) != null) {
							employeeDetails.setPay_group(row.getCell(50).toString());
						}
						if (row.getCell(51) != null) {
							employeeDetails.setPhysically_challanged(row.getCell(51).toString());
						}
						if (row.getCell(52) != null) {
							employeeDetails.setDisability(row.getCell(52).toString());
						}
						if (row.getCell(53) != null) {
							employeeDetails.setAadhar_no(row.getCell(53).toString());
						}
						if (row.getCell(54) != null) {
							employeeDetails.setUan_number(row.getCell(54).toString());
						}

					}

					t = session.beginTransaction();
					session.saveOrUpdate(employeeDetails);
					t.commit();
					responseMessage.setSuccess("Uploaded Successfully!");
				}
			}

		}

		catch (Exception e) {

			e.printStackTrace();
			message = "failed";
			responseMessage.setSuccess("Error in employee number " + empNo.toString() + " " + e.toString());
		} finally {

			workbook.close();
			session.close();
			sf.close();
		}
		return responseMessage;
	}

	public ResponseMessage saveAttendanceDetails(MultipartFile file) throws IOException, ParseException {
		ResponseMessage responseMessage = new ResponseMessage();
		String empNo = null;
		String message = null;
		SessionFactory sf = new Configuration().configure("attendance.cfg.xml").buildSessionFactory();
		Session session = null;
		Workbook workbook = null;
		Transaction t = null;
		try {
			session = sf.openSession();

			workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			Row row;
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				AttendanceDetails attendanceDetails = new AttendanceDetails();
				row = (Row) sheet.getRow(i); // sheet number
				if (row.getCell(0) == null) {
					System.out.println("invalid details");
				} else {
					if (row.getCell(0) != null) {
						attendanceDetails.setEmployee_id(row.getCell(0).toString());
						empNo = row.getCell(0).toString();
					}
					if (row.getCell(1) != null) {
						attendanceDetails.setEmployee_name(row.getCell(1).toString());
					}
					if (row.getCell(2) != null) {
						attendanceDetails.setDesignation(row.getCell(2).toString());
					}
					if (row.getCell(3) != null) {
						String date = row.getCell(3).toString();
						SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MMM-dd");
						Date date2 = formatter2.parse(date);
						System.out.println(date2);
						attendanceDetails.setDate(date2);
					}
					if (row.getCell(4) != null) {
						attendanceDetails.setIn_time(row.getCell(4).toString());
					}
					if (row.getCell(5) != null) {
						attendanceDetails.setOut_time(row.getCell(5).toString());
					}
					if (row.getCell(6) != null) {
						attendanceDetails.setTotal_hours((row.getCell(6).getDateCellValue()));
						if (row.getCell(6).getDateCellValue() != null) {
							double hr = row.getCell(6).getDateCellValue().getHours();
							double mm = row.getCell(6).getDateCellValue().getMinutes();
							double total = Math.round((hr + (mm / 60)) * 100.0) / 100.0;
							attendanceDetails.setTotal_hours_in_percent(total);
						}
					}
					if (row.getCell(7) != null) {
						attendanceDetails.setStatus(row.getCell(7).toString());
					}
					if (row.getCell(8) != null) {
						attendanceDetails.setReporting_to(row.getCell(8).toString());
					}
					if (row.getCell(9) != null) {
						attendanceDetails.setLocation(row.getCell(9).toString());
					}
					if (row.getCell(10) != null) {
						attendanceDetails.setOu_name(row.getCell(10).toString());
					}
					if (row.getCell(11) != null) {
						attendanceDetails.setGrade(row.getCell(11).toString());
					}
					if (row.getCell(12) != null) {
						attendanceDetails.setSign_in_location(row.getCell(12).toString());
					}
					if (row.getCell(13) != null) {
						attendanceDetails.setSign_out_location(row.getCell(13).toString());
					}

					t = session.beginTransaction();
					session.save(attendanceDetails);
					t.commit();
					responseMessage.setSuccess("Uploaded Successfully!");
				}
			}
		
		} catch (Exception e) {

			e.printStackTrace();
			message = "failed";
			responseMessage.setSuccess("Error in employee number " + empNo.toString() + " " + e.toString());
		} finally {

			workbook.close();
			session.close();
			sf.close();
		}
		return responseMessage;
	}
}
