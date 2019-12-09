package com.weilingxiao.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weilingxiao.cms.bean.User;
import com.weilingxiao.cms.util.RandomUtil;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml")
public class UserTest {

	@Resource
	private RedisTemplate redisTemplate;
	
	@Test
	public void UserTestJdk(){
		//设置开始时间
		long time1 = System.currentTimeMillis();
		ValueOperations opsValue = redisTemplate.opsForValue();
		User user = new User();
		for (int i = 1; i <=50000; i++) {
			//设置id
			user.setId(i);
			//设置名字
			user.setName(RandomUtil.getChineseName());
			//设置性别
			user.setSex(RandomUtil.getSex());
			//设置手机号
			user.setPhone(RandomUtil.RandomPhone());
			//设置邮箱
			user.setEmail(RandomUtil.getEmail());
			//设置生日
			user.setBirthday(RandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00"));
			//System.out.println(user);
			opsValue.set(i+"", user);	
		}
		long time2 = System.currentTimeMillis();
		//求出所耗时间长
		long time = time2-time1;
		System.out.println("输出序列化的方式是 jdk，所耗时间"+time);
	}
	
	
	@Test
	public void UserTestJson(){
		//设置开始时间
		long time1 = System.currentTimeMillis();
		ValueOperations opsValue = redisTemplate.opsForValue();
		User user = new User();
		for (int i = 1; i <=50000; i++) {
			//设置id
			user.setId(i);
			//设置名字
			user.setName(RandomUtil.getChineseName());
			//设置性别
			user.setSex(RandomUtil.getSex());
			//设置手机号
			user.setPhone(RandomUtil.RandomPhone());
			//设置邮箱
			user.setEmail(RandomUtil.getEmail());
			//设置生日
			user.setBirthday(RandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00"));
			//System.out.println(user);
			opsValue.set(i+"", user);	
		}
		long time2 = System.currentTimeMillis();
		//求出所耗时间长
		long time = time2-time1;
		System.out.println("输出序列化的方式是 json，所耗时间"+time);
	}
	
	
	@Test
	public void UserTestHash(){
		//设置开始时间
		long time1 = System.currentTimeMillis();
		BoundHashOperations hashOps = redisTemplate.boundHashOps("user_hash");
		User user = new User();
		for (int i = 1; i <=50000; i++) {
			//设置id
			user.setId(i);
			//设置名字
			user.setName(RandomUtil.getChineseName());
			//设置性别
			user.setSex(RandomUtil.getSex());
			//设置手机号
			user.setPhone(RandomUtil.RandomPhone());
			//设置邮箱
			user.setEmail(RandomUtil.getEmail());
			//设置生日
			user.setBirthday(RandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00"));
			//System.out.println(user);
			hashOps.put(i+"", user.toString());
		}
		long time2 = System.currentTimeMillis();
		//求出所耗时间长
		long time = time2-time1;
		System.out.println("输出序列化的方式是 hash，所耗时间"+time);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
