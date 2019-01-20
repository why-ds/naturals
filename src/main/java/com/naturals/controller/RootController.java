package com.naturals.controller;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naturals.domain.TimeAttendance;

@Controller
public class RootController {
	
	/*@Autowired
	TimeAttendance timeAttendance;*/
	
	@RequestMapping("/")
	public String index(){
	return "/index";
	}
	
	
	@RequestMapping("/importTA")
	public void importTA() {
		
		String file = "C:\\DEV\\190111.xlsx";
		
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			FileInputStream is = new FileInputStream(file);
			HSSFWorkbook hwb = new HSSFWorkbook(is);
			
           HSSFSheet curSheet; // 현재 sheet
           HSSFCell curCell; // 현재 cell
           HSSFRow curRow; // 현재 row
           
           int sCount = hwb.getNumberOfSheets();
           
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
