package com.dummy.myerp.testconsumer.consumer;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.testconsumer.consumer.SpringRegistry;

public abstract class ConsumerTestCase {

	static {
        SpringRegistry.init();
    }
	
	private static final DaoProxy DAO_PROXY = SpringRegistry.getDaoProxy();
	

    // ==================== Constructeurs ====================
    /**
     * Constructeur.
     */
    public ConsumerTestCase() {
    }
    
    // ==================== Getters/Setters ====================
    public static DaoProxy getDaoProxy() {
		return DAO_PROXY;
	}
}

