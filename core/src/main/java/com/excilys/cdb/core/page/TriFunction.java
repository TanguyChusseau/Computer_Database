package main.java.com.excilys.cdb.core.page;
@FunctionalInterface
public interface TriFunction<A, B, C, R> {
	R apply(A a, B b, C c);
}
