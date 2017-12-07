package com.atguigu.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class MyCacheUtil {

	public static <T> int refresh_redis_key(String key, List<T> list) {
		try {
			Jedis jedis = JedisPoolUtils.getJedis();
			jedis.del(key);
			for (int i = 0; i < list.size(); i++) {

				jedis.zadd(key, i, MyJsonUtil.object_to_json(list.get(i)));
			}
			return list.size();
		} catch (Exception e) {
			return 0;
		}
	}

	public static <T> List<T> get_redis_key(String key, Class<T> t) {

		return null;
	}

}
