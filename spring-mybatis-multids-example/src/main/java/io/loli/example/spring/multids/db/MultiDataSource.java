package io.loli.example.spring.multids.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiDataSource extends AbstractRoutingDataSource {

	protected Object determineCurrentLookupKey() {
		return ContextHolder.getCustomerType();
	}

}
