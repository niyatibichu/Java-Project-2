package com.project1;


public class UserUrl {
	private String longUrl;

    private String shortUrl;

    private String userName;
    
    private int clicks;
    
    public String getlongUrl()
    {
        return longUrl;
    }

    public void setlongUrl(String longUrl)
    {
        this.longUrl = longUrl;
    }

    
    public String getuserName()
    {
        return userName;
    }

    public void setuserName(String userName)
    {
        this.userName = userName;
    }
    public String getshortUrl()
    {
        return shortUrl;
    }

    public void setshortUrl(String shortUrl)
    {
        this.shortUrl = shortUrl;
    }

    public int getclicks()
    {
        return clicks;
    }

    public void setclicks(int clicks)
    {
        this.clicks = clicks;
    }
}
