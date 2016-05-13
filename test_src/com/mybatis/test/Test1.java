package com.mybatis.test;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.domain.User;
import com.mybatis.inter.IUserOperation;

public class Test1 {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public static void main(String[] args) {
		// mybatis的配置文件
		// String resource = "Configuration.xml";
		// //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		// InputStream is =
		// Test1.class.getClassLoader().getResourceAsStream(resource);
		// //构建sqlSession的工厂
		// SqlSessionFactory sessionFactory = new
		// SqlSessionFactoryBuilder().build(is);

		// 使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
		// Reader reader = Resources.getResourceAsReader(resource);
		// 构建sqlSession的工厂
		// SqlSessionFactory sessionFactory = new
		// SqlSessionFactoryBuilder().build(reader);
		

		//getUser(1);
		//getUserList("%ding");
//		User user1=new User("大家好",18,null);
//		addUser(user1);
		
//		User user=new User(5,"小冤家",30,null);
//		updateUser(user);
		
		//deleteUser(6);
	}

	public static void getUser(int id) {
		// 创建能执行映射文件中sql的sqlSession
			SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation=session.getMapper(IUserOperation.class);
			User user = userOperation.selectUserByID(1);
			System.out.println(user.getUserAddress());
			System.out.println(user.getUserName());
		} finally {
			session.close();
		}
	}
	
	public static void getUserList(String userName){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);           
            List<User> users = userOperation.selectUsers(userName);
            for(User user:users){
                System.out.println(user.getId()+":"+user.getUserName()+":"+user.getUserAddress());
            }
            
        } finally {
            session.close();
        }
    }
	
	/**
     * 测试增加,增加后，必须提交事务，否则不会写入到数据库.
     */
	public static void addUser(User user){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation=session.getMapper(IUserOperation.class);  
			
			userOperation.addUser(user);
			session.commit();
            System.out.println("当前增加的用户 id为:"+user.getId());
		} finally {
			session.close();
		}
	}
	
	
	/**
	 * 测试修改,修改后，必须提交事务，否则不会写入到数据库.
	 */
	public static void updateUser(User user){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation=session.getMapper(IUserOperation.class);  
			
			userOperation.updateUser(user);
			session.commit();
			System.out.println("当前增加的用户 id为:"+user.getId());
		} finally {
			session.close();
		}
	}
	
	
	/**
	 * 测试删除,删除后，必须提交事务，否则不会写入到数据库.
	 */
	public static void deleteUser(int id){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation=session.getMapper(IUserOperation.class);  
			
			userOperation.deleteUser(id);
			session.commit();
		} finally {
			session.close();
		}
	}
	
}
