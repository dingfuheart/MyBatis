package com.mybatis.inter;

import java.util.List;

import com.mybatis.domain.Article;

public interface IArticleOperation {
	
	public List<Article> getUserArticles(int id);

}
