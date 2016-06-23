package org.reflection.service;

import java.io.InputStream;
import java.util.Map;
import java.util.SortedSet;

/**
 *
 * @author mbadiuzzaman
 */
public interface ProcService {

    public void refresh();

    public void daily(String attnDateStr);

    public Map doUploadExcelFile(InputStream fis);

    public void dummyUserData();

    public void fastMenuGen(SortedSet<String> list);
}
