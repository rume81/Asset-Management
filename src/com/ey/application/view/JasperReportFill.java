package com.ey.application.view;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JInternalFrame;

import com.ey.application.model.DataBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

public class JasperReportFill extends JInternalFrame {
	private static JasperReportFill myInstance;
	private String month;
	private String year;
	private String range;

	public static JasperReportFill getInstance() {
		//if (myInstance == null) {
			return myInstance = new JasperReportFill();
		//}
		//return myInstance;
	}

	public JasperReportFill() {
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setTitle("備品台帳 兼 償却計算表");
		ReadReportParam();
		String workingDir = System.getProperty("user.dir");
		String sourceFileName = workingDir+"\\rpt\\jasper_report_template.jasper";
		//String sourceFileName = "C://tools/jasperreports-6.2.0/test/jasper_report_template.jasper";

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		DataBeanList DataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = DataBeanList.getDataBeanList(month, year, range);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

		Map parameters = new HashMap();
		/**
		 * Passing ReportTitle and Author as parameters
		 */
		parameters.put("ReportTitle", "備品台帳 兼 償却計算表");
		parameters.put("GetDate", year+" 年"+month+" 月末");
		parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));

		JasperPrint jasperPrint = null;

		try {
			jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}

		JRViewer viewer = new JRViewer(jasperPrint);
		//viewer.clear();
		//viewer = null;
		Container c = this.getContentPane();
		c.removeAll();
		c.add(viewer);
		this.setBounds(1, 1, 800, 600);

	}
	
	private void ReadReportParam()
	{
		// The name of the file to open.
		String workingDir = System.getProperty("user.dir");
		String sourceFileName = workingDir + "\\rpt\\param.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(sourceFileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String retval[] =  line.split("==");
                /*for (String retval: line.split("==")){
                    System.out.println(retval);
                    month 
                 }*/
                month = retval[0];
                year = retval[1];
                range = retval[2];
                //System.out.println(month);
                //System.out.println(year);
                
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		sourceFileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + sourceFileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	
	}
}
