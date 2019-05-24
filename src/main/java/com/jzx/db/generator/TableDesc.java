package com.jzx.db.generator;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDesc {
	// 表名
	private String tableName;
	// 类型
	private String tableType;
	// 引擎
	private String engine;
	// 字符集
	private String tableCollation;
	// 表描述
	private String tableComment;

	private String createOptions;

	// 当前表所有列
	private List<Column> columns = new ArrayList<Column>();

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Column {
		// 序号
		private String ordinalPosition;
		// 列名
		private String columnName;
		// 列类型 int(11)
		private String columnType;
		// 是否主键
		private String columnKey;
		// 是否自动增长
		private String extra;
		// 是否可为空
		private String isNullable;
		// 列缺省值
		private String columnDefault;
		// 列描述
		private String columnComment;
		// 基本列类型 int
		private String dataType;
		// 列长度
		private String characterMaximumLength;

		public String[] convertArr() {
			String[] arr = new String[5];
			arr[0] = this.columnName;
			arr[1] = this.columnComment;
			arr[2] = this.columnType;
			arr[3] = this.isNullable;
			arr[4] = this.columnDefault;
			return arr;
		}
	}
}
