﻿SET DEFINE OFF;
Insert into LEAVE_APP
   (ID, ADDRESS_DURING_LEAVE, APP_DATE, CODE, CONTACT_NO, 
    END_DATE, LEAVE_TYPE, REASON_FOR_LEAVE, START_DATE, VERSION, 
    EMPLOYEE_ID)
 Values
   (1, 'sgdsg', TO_DATE('05/30/2016 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'LA-0002', '346346346', 
    TO_DATE('05/20/2016 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'CASUAL', 'dgdfg..', TO_DATE('05/07/2016 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 4, 
    3);
Insert into LEAVE_APP
   (ID, ADDRESS_DURING_LEAVE, APP_DATE, CODE, CONTACT_NO, 
    END_DATE, LEAVE_TYPE, REASON_FOR_LEAVE, START_DATE, VERSION, 
    EMPLOYEE_ID)
 Values
   (43711, 'sgdsg', TO_DATE('05/30/2016 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'LA-0001', '346346346', 
    TO_DATE('05/20/2016 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'CASUAL', 'dgdfg..', TO_DATE('05/07/2016 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 0, 
    1);
COMMIT;
