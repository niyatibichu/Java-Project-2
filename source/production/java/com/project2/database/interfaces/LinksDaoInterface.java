package com.project2.database.interfaces;

import java.util.ArrayList;

import com.project2.dao.Links;

public interface LinksDaoInterface {
public void insertLinks(Links links);
public ArrayList<Links> getLinksByUser(int userId);
public Links getLongUrl (String shortUrl);
}
