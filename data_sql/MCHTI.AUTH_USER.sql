SET DEFINE OFF;
Insert into AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    EMAIL, ENABLED, FULL_NAME, GENDER, PASSWORD, 
    PIC_FILE, USERNAME)
 Values
   (43406, 0, 0, 0, 'Manik', 
    'manikmonir@gmail.com', 1, 'Mohammad Badiuzzaman', 'MALE', '123', 
    'aaaa.jpg', 'mac');
Insert into AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    DOB, EMAIL, ENABLED, FULL_NAME, GENDER, 
    PASSWORD, PIC_FILE, USERNAME)
 Values
   (43407, 1, 1, 1, 'Saif', 
    TO_DATE('04/04/2016 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'saif_hmk@live.com', 1, 'MD. HOSHEN MAHMUD KHAN', 'MALE', 
    '123', 'bbbb.jpg', 'saif');
Insert into AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    DOB, DOJ, EMAIL, ENABLED, FULL_NAME, 
    GENDER, PASSWORD, PIC_FILE, USERNAME)
 Values
   (43408, 1, 1, 1, 'Anis', 
    TO_DATE('05/29/2016 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('05/30/2016 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'anis.object@gmail.com', 1, 'Mohammad Anisur Rahman Khan', 
    'MALE', '123', 'cccc.jpg', 'anis');
Insert into AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    ENABLED, FULL_NAME, GENDER, PASSWORD, USERNAME)
 Values
   (1, 0, 0, 0, 'Ishrat', 
    1, 'Dr. Ishrat Jahan', 'FEMALE', '123', '1');
Insert into AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    EMAIL, ENABLED, FULL_NAME, GENDER, PASSWORD, 
    PIC_FILE, USERNAME)
 Values
   (2, 0, 0, 0, 'Manzarul', 
    'unibd@yahoo.com', 1, 'Manzarul Islam', 'MALE', 'Howlader019', 
    'dddd.jpg', 'Manzarul');
COMMIT;
