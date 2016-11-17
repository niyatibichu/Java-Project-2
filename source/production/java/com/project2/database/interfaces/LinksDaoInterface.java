package com.project2.database.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.project2.dao.Links;

public interface LinksDaoInterface {
public void insertLinks(Links links);
public List<Links> getLinksByUser(int userId);
public Links getLongUrl (String shortUrl);
}
