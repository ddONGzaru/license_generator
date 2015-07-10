package io.manasobi.commons.logger;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("rawtypes") 
public final class CommonLogger {
	
	private CommonLogger() { }
	
	private static Logger logger = LoggerFactory.getLogger("io.manasobi");
	
	public static void error(String msg, Class clazz) {
		
		if (logger.isErrorEnabled()) {
			logger.error(parseLogPrifix(clazz) + "::: ERROR ::: " + msg);
		}
	}

	private static String parseLogPrifix(Class clazz) {
		
		String packageName = ClassUtils.getPackageName(clazz);
		
		if (packageName.startsWith("io.manasobi.commons")) {
			return "[commons] "; 
		} else {
			return "[license] ";
		}		
	}

}
