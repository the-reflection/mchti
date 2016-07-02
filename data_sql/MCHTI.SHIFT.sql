SET DEFINE OFF;
Insert into MCHTI.SHIFT
   (ID, CODE, END_BUF_MIN, END_HR, END_MIN, 
    IS_ACTIVE, ORIENTATION_MIN, SHIFT_TYPE, START_BUF_MIN, START_HR, 
    START_MIN, TITLE, VERSION)
 Values
   (1, 'G', 5, 14, 30, 
    1, 60, 'GENERAL', 15, 9, 
    0, 'Morning General Ramadan', 2);
Insert into MCHTI.SHIFT
   (ID, CODE, END_BUF_MIN, END_HR, END_MIN, 
    IS_ACTIVE, ORIENTATION_MIN, SHIFT_TYPE, START_BUF_MIN, START_HR, 
    START_MIN, TITLE, VERSION)
 Values
   (2, 'E', 5, 22, 0, 
    1, 60, 'ROSTER', 15, 14, 
    0, 'Evening', 0);
Insert into MCHTI.SHIFT
   (ID, CODE, END_BUF_MIN, END_HR, END_MIN, 
    IS_ACTIVE, ORIENTATION_MIN, SHIFT_TYPE, START_BUF_MIN, START_HR, 
    START_MIN, TITLE, VERSION)
 Values
   (3, 'M', 5, 14, 0, 
    1, 60, 'ROSTER', 15, 7, 
    0, 'Morning', 0);
Insert into MCHTI.SHIFT
   (ID, CODE, END_BUF_MIN, END_HR, END_MIN, 
    IS_ACTIVE, ORIENTATION_MIN, SHIFT_TYPE, START_BUF_MIN, START_HR, 
    START_MIN, TITLE, VERSION)
 Values
   (4, 'N', 5, 8, 0, 
    1, 60, 'ROSTER', 15, 20, 
    0, 'Night', 0);
Insert into MCHTI.SHIFT
   (ID, CODE, END_BUF_MIN, END_HR, END_MIN, 
    IS_ACTIVE, ORIENTATION_MIN, SHIFT_TYPE, START_BUF_MIN, START_HR, 
    START_MIN, TITLE, VERSION)
 Values
   (5, 'O', 0, 0, 0, 
    1, 60, 'ROSTER', 0, 0, 
    0, 'Off Day', 0);
Insert into MCHTI.SHIFT
   (ID, CODE, END_BUF_MIN, END_HR, END_MIN, 
    IS_ACTIVE, ORIENTATION_MIN, SHIFT_TYPE, START_BUF_MIN, START_HR, 
    START_MIN, TITLE, VERSION)
 Values
   (6, 'R', 0, 0, 0, 
    1, 60, 'ROSTER', 0, 0, 
    0, 'Rest Day', 0);
Insert into MCHTI.SHIFT
   (ID, CODE, END_BUF_MIN, END_HR, END_MIN, 
    IS_ACTIVE, ORIENTATION_MIN, SHIFT_TYPE, START_BUF_MIN, START_HR, 
    START_MIN, TITLE, VERSION)
 Values
   (7, 'A', 5, 14, 30, 
    1, 60, 'GENERAL', 15, 8, 
    0, 'Morning General All Time', 0);
COMMIT;
