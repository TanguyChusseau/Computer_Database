package main.java.com.excilys.cdb.core.page;

import org.springframework.data.domain.Page;

public class PageManagerFilterByName<T> extends PageManager<T> {
	
	protected TriFunction<Integer, Integer, String, Page<T>> getList;
	protected String filterField;

	public String getFilterField() {
		return filterField;
	}
	
	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}
	
	public PageManagerFilterByName(int limit, int numberOfComputers, TriFunction<Integer, Integer, String, Page<T>> getList) {
		this.offset = 0;
		this.limit = limit;
		this.max = numberOfComputers;
		refreshMaxPage();
		this.getList = getList;
	}
	
	protected boolean getItems() {
		pageData.clear();
		getList.apply(offset, limit, filterField).forEach(x -> pageData.add((T) x));
		return true;
	}

}
