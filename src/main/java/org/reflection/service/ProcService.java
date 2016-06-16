package org.reflection.service;

import java.io.InputStream;
import java.util.Map;

/**
 *
 * @author mbadiuzzaman
 */
public interface ProcService {

    public void refresh();

    public void daily(String attnDateStr);

    public Map doUploadExcelFile(InputStream fis);

    public void dummyUserData();
}
