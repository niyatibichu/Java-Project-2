package com.project2.dao;

public class Links {
	private int linkId;
	private int userId;
	private String longUrl;
	private String shortUrl;
	private int clicks;

	/**
	 * @return the linkId
	 */
	public int getLinkId() {
		return linkId;
	}

	/**
	 * @param linkId
	 *            the linkId to set
	 */
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the longUrl
	 */
	public String getLongUrl() {
		return longUrl;
	}

	/**
	 * @param longUrl
	 *            the longUrl to set
	 */
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	/**
	 * @return the shortUrl
	 */
	public String getShortUrl() {
		return shortUrl;
	}

	/**
	 * @param shortUrl
	 *            the shortUrl to set
	 */
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	/**
	 * @return the clicks
	 */
	public int getClicks() {
		return clicks;
	}

	/**
	 * @param clicks
	 *            the clicks to set
	 */
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Links [linkId=" + linkId + ", userId=" + userId + ", longUrl=" + longUrl + ", shortUrl=" + shortUrl
				+ ", clicks=" + clicks + "]";
	}

}
