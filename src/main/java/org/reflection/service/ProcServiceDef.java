package org.reflection.service;

import java.io.InputStream;
import java.util.Map;
import java.util.SortedSet;

/**
 *
 * @author mbadiuzzaman
 */
public interface ProcServiceDef {

    public void dummyUserData();

    public void fastMenuGen(SortedSet<String> list);

    public Map doUploadExcelFile(InputStream fis);
}
