package com.jzx.db.generator;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public class WordStyle {
	/**
	 * 设置Table头
	 * 
	 * @param document
	 * @see
	 */
	public static XWPFTable setTableHeaderStyle(XWPFDocument document) {
		XWPFTable table = document.createTable();
		// CTTblBorders borders = table.getCTTbl().getTblPr().addNewTblBorders();
		// // 设置表头top
		// CTBorder top = borders.addNewTop();
		// top.setVal(STBorder.Enum.forString("single")); // 线条类型
		// top.setSz(new BigInteger("1")); // 线条大小
		// top.setColor("FF9900"); // 设置颜色
		//
		// // 设置表头bottom
		// CTBorder bottom = borders.addNewBottom();
		// bottom.setVal(STBorder.Enum.forString("single")); // 线条类型
		// bottom.setSz(new BigInteger("1")); // 线条大小
		// bottom.setColor("FF9900"); // 设置颜色
		//
		// // 设置表头left
		// CTBorder left = borders.addNewLeft();
		// left.setVal(STBorder.Enum.forString("single")); // 线条类型
		// left.setSz(new BigInteger("1")); // 线条大小
		// left.setColor("FF9900"); // 设置颜色
		//
		// // 设置表头right
		// CTBorder right = borders.addNewRight();
		// right.setVal(STBorder.Enum.forString("single")); // 线条类型
		// right.setSz(new BigInteger("1")); // 线条大小
		// right.setColor("FF9900"); // 设置颜色
		//
		// // 设置单元格内部横向
		// CTBorder hBorder = borders.addNewInsideH();
		// hBorder.setVal(STBorder.Enum.forString("single")); // 线条类型
		// hBorder.setSz(new BigInteger("1")); // 线条大小
		// hBorder.setColor("FF9900"); // 设置颜色
		//
		// // 设置单元格内部纵向
		// CTBorder vBorder = borders.addNewInsideV();
		// vBorder.setVal(STBorder.Enum.forString("single")); // 线条类型
		// vBorder.setSz(new BigInteger("1")); // 线条大小
		// vBorder.setColor("FF9900"); // 设置颜色
		return table;
	}

	/**
	 * 
	 * 创建表头
	 * 
	 * @param table
	 * @param headers
	 * @see
	 */
	public static void createTableHeader(XWPFTable table, String[] headers) {
		XWPFTableRow tableRowOne = table.getRow(0);
		for (int i = 0; i < headers.length; i++) {
			if (i == 0) {
				XWPFTableCell cell1 = tableRowOne.getCell(0);
				setCellText(cell1, headers[i], "FF9900");
			} else {
				XWPFTableCell cell1 = tableRowOne.addNewTableCell();
				setCellText(cell1, headers[i], "FF9900");
			}
		}
	}

	/**
	 * 设置单元格
	 * 
	 * @param cell
	 * @param text
	 * @param bgcolor
	 * @return
	 * @see
	 */
	public static XWPFTableCell setCellText(XWPFTableCell cell, String text, String bgcolor) {
		CTTc cttc = cell.getCTTc();
		// CTTcPr cellPr = cttc.addNewTcPr();
		// cellPr.addNewTcW().setW(BigInteger.valueOf(width));
		cell.setColor(bgcolor);
		CTTcPr ctPr = cttc.addNewTcPr();
		// CTShd ctshd = ctPr.addNewShd();
		// ctshd.setFill(bgcolor);
		ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
		cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
		cell.setText(text);
		// 设置表头加粗
		// XWPFParagraph p0 = cell.addParagraph();
		// cell.setParagraph(p0);
		// XWPFRun r0 = p0.createRun();
		// r0.setBold(true);
		// r0.setText(text);
		return cell;
	}

	/**
	 * 创建一行数据
	 * 
	 * @param table
	 * @param rows
	 * @see
	 */
	public static void createRows(XWPFTable table, String[] rows) {
		XWPFTableRow tableRow = table.createRow();
		for (int i = 0; i < rows.length; i++) {
			tableRow.getCell(i).setText(rows[i]);
		}
	}
}
