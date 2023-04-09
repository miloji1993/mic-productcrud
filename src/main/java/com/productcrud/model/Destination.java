/**
 * 
 */
package com.productcrud.model;


public enum Destination {
	MERCADONA_SPAIN_SHOP_1(1),
	MERCADONA_SPAIN_SHOP_2(2),
	MERCADONA_SPAIN_SHOP_3(3),
	MERCADONA_SPAIN_SHOP_4(4),
	MERCADONA_SPAIN_SHOP_5(5),
	MERCADONA_PORTUGAL_SHOP(6),
	MERCADONA_STORE(8),
	MERCADONA_OFFICE(9),
	MERCADONA_COLMENA(0);
	
	private final int order;
	
	Destination(final int order) {
		this.order = order;
	}
	
	public int getOrder() {
	   return this.order;
	}
}
