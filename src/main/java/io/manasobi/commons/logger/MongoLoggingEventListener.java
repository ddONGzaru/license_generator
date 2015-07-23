package io.manasobi.commons.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

import com.mongodb.DBObject;

public class MongoLoggingEventListener extends AbstractMongoEventListener<Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoLoggingEventListener.class);

	@Override
	public void onBeforeConvert(Object source) {
		LOGGER.info("[mongodb] onBeforeConvert: {}", source);
	}

	@Override
	public void onBeforeSave(Object source, DBObject dbo) {
		LOGGER.info("[mongodb] onBeforeSave: {}, {}", source, dbo);
	}

	@Override
	public void onAfterSave(Object source, DBObject dbo) {
		LOGGER.info("[mongodb] onAfterSave: {}, {}", source, dbo);
	}

	@Override
	public void onAfterLoad(DBObject dbo) {
		LOGGER.info("[mongodb] onAfterLoad: {}", dbo);
	}

	@Override
	public void onAfterConvert(DBObject dbo, Object source) {
		LOGGER.info("[mongodb] onAfterConvert: {}, {}", dbo, source);
	}

	@Override
	public void onAfterDelete(DBObject dbo) {
		LOGGER.info("[mongodb] onAfterDelete: {}", dbo);
	}

	@Override
	public void onBeforeDelete(DBObject dbo) {
		LOGGER.info("[mongodb] onBeforeDelete: {}", dbo);
	}
}