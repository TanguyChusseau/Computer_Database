package main.java.com.excilys.cdb.core.page;

import java.util.function.BiFunction;

import org.springframework.data.domain.Page;

public class PageManagerGetAll<T> extends PageManager<T> {

	private BiFunction<Integer, Integer, Page<T>> getList;

	public PageManagerGetAll(int limit, int max, BiFunction<Integer, Integer, Page<T>> getList) {
		this.offset = 0;
		this.limit = limit;
		this.max = max;
		refreshMaxPage();
		this.getList = getList;
	}

	protected boolean getItems() {
		pageData.clear();
		getList.apply(offset, limit).forEach(x -> pageData.add((T) x));
		return true;
	}
}
