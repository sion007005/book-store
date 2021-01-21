package sion.bookstore.domain.book.service;

public enum OrderType {
    TITLE("상품명", "title"),
	PRICE("낮은가격순", "sale_price"),
	YEAR("최신순", "year");
//	STOCK("재고", "stock");

	private String name;
	private String columnName;

	private OrderType(String name, String columnName) {
		this.name = name;
		this.columnName = columnName;
	}

	public String getName() {
		return name;
	}

	public String getColumnName() {
		return columnName;
	}
}
