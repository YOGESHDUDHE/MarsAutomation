package com.framework;

public class ExcelCell {
	Integer row;
	Integer column;
	Integer value;

	public ExcelCell(Integer row, Integer column, Integer value) {
		super();
		this.row = row;
		this.column = column;
		this.value = value;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}