package com.core.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class MyJdkSerializationRedisSerializer implements RedisSerializer<Object>{
	
	static final byte[] EMPTY_ARRAY = new byte[0];
	
	static boolean isEmpty(byte[] data) {

		return ( data == null || data.length == 0 );
	}
	
	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();

	@Override
	public byte[] serialize(Object object) throws SerializationException {
		if (object == null) {
			return EMPTY_ARRAY;
		}
		try {
			return serializer.convert(object);
		}
		catch (Exception ex) {
			throw new SerializationException("Cannot serialize", ex);
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {

		if (isEmpty(bytes)) {
			return null;
		}
		try {
			return deserializer.convert(bytes);
		}
		catch (Exception ex) {
			return null;
		}
	}

}
