package com.jzx.db.generator;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class GeneratorMain {

	public static void main(String[] args) throws Exception {
		if (args.length < 5) {
			System.out.println("参数错误");
			System.exit(0);
		}
		String dbName = args[0];
		String url = args[1];
		String user = args[2];
		String pwd = args[3];
		String path = args[4];

		FileOutputStream out = new FileOutputStream(path);

		List<TableDesc> tableDescList = new ArrayList<TableDesc>();
		// word对象
		XWPFDocument document = new XWPFDocument();
		Connection conection = JdbcHelper.initConnection(url, user, pwd);
		List<Map<String, Object>> tableList = JdbcHelper.getMysqlAllTable(conection, dbName);
		for (Map<String, Object> tableMap : tableList) {
			TableDesc tableDesc = new TableDesc();
			BeanUtils.populate(tableDesc, tableMap);

			conection = JdbcHelper.initConnection(url, user, pwd);
			List<Map<String, Object>> columnList = JdbcHelper.getTableInfo(conection, dbName, tableDesc.getTableName());
			for (Map<String, Object> columnMap : columnList) {
				TableDesc.Column column = new TableDesc.Column();
				BeanUtils.populate(column, columnMap);
				tableDesc.getColumns().add(column);
			}

			tableDescList.add(tableDesc);
		}

		for (int i = 0; i < tableDescList.size(); i++) {
			// 创建表描述
			XWPFParagraph paragraphX = document.createParagraph();
			XWPFRun runX = paragraphX.createRun();
			runX.setBold(true);
			runX.setText("表名：" + tableDescList.get(i).getTableName());

			XWPFParagraph paragraphY = document.createParagraph();
			XWPFRun runY = paragraphY.createRun();
			runY.setBold(false);
			runY.setText("表描述：" + tableDescList.get(i).getTableComment());
			runY.addCarriageReturn();// 回车键

			// 设置表头样式
			XWPFTable table = WordStyle.setTableHeaderStyle(document);
			// create table
			String[] headers = new String[] { "字段名称", "字段描述", "字段类型", "允许空", "缺省值" };
			// 创建表头
			WordStyle.createTableHeader(table, headers);

			List<String[]> tableBodyDataList = new ArrayList<String[]>();
			for (TableDesc.Column column : tableDescList.get(i).getColumns()) {
				tableBodyDataList.add(column.convertArr());
			}

			for (int j = 0; j < tableBodyDataList.size(); j++) {
				WordStyle.createRows(table, tableBodyDataList.get(j));
			}

			XWPFParagraph newline = document.createParagraph();
			XWPFRun newlineRun = newline.createRun();
			newlineRun.setBold(false);
			runX.addCarriageReturn();// 回车键

		}
		document.write(out);
		document.close();
		out.close();
		System.out.println("word文档生成成功，10秒后退出！路径：" + path);
		Thread.sleep(10000);
	}
}
