package org.reflection.service;

import java.util.Date;

/**
 *
 * @author mbadiuzzaman
 */
public interface ProcService {

    public void timerSingleton();

    public void empCascadeDeleteWithCode(String empCode);

    public void genCalender(Date fromDate, Date toDate);

    public void refresh();

    public void daily(Date fromDate, Date toDate);

    public void daily(Date attnDate);

}
