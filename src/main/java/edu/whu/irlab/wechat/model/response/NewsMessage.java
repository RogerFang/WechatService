package edu.whu.irlab.wechat.model.response;

import java.util.List;

/**
 * 多图文消息
 */
public class NewsMessage extends BaseMessage{
	private int ArticleCount;
	private List<Article> Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
	
	
	
}