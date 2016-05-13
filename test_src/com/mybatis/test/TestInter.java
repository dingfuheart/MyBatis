package com.mybatis.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.mybatis.domain.Article;
import com.mybatis.domain.User;
import com.mybatis.inter.IArticleOperation;
import com.mybatis.inter.IUserOperation;

public class TestInter {
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

	// 一对多，user（一） 级联查询书article（多）
	@Test
	public void testGetUserArticles() {
		int userid = 1;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session
					.getMapper(IUserOperation.class);
			User user = userOperation.getUserArticles(userid);

			System.out.println("作者是:" + user.getUserName() + ":地址:"
					+ user.getUserAddress() + "拥有的书本数为："
					+ user.getArList().get(1).getTitle());

		} finally {
			session.close();
		}
	}

	// 多对一，书article（多）级联查询user（一）
	@Test
	public void testGetUserArticles1() {
		int userid = 1;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IArticleOperation articleOperation = session
					.getMapper(IArticleOperation.class);
			List<Article> articles = articleOperation.getUserArticles(userid);
			System.out.println(articles.size());
			for (Article article : articles) {
				System.out.println(article.getTitle() + ":"
						+ article.getContent() + ":作者是:"
						+ article.getUser().getUserName() + ":地址:"
						+ article.getUser().getUserAddress());
			}
		} finally {
			session.close();
		}
	}

}
