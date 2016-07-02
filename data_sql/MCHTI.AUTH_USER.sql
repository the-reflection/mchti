SET DEFINE OFF;
Insert into MCHTI.AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    EMAIL, ENABLED, FULL_NAME, GENDER, PASSWORD, 
    PIC_FILE, USERNAME)
 Values
   (43406, 0, 0, 0, 'Manik', 
    'manikmonir@gmail.com', 1, 'Mohammad Badiuzzaman', 'MALE', '123', 
    'aaaa.jpg', 'mac');
Insert into MCHTI.AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    EMAIL, ENABLED, FULL_NAME, GENDER, PASSWORD, 
    PIC_FILE, USERNAME)
 Values
   (43407, 0, 0, 0, 'Saif', 
    'saif_hmk@live.com', 1, 'MD. HOSHEN MAHMUD KHAN', 'MALE', '123', 
    'bbbb.jpg', 'saif_hmk');
Insert into MCHTI.AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    EMAIL, ENABLED, FULL_NAME, GENDER, PASSWORD, 
    PIC_FILE, USERNAME)
 Values
   (43408, 0, 0, 0, 'Anis', 
    'anis.object@gmail.com', 1, 'Mohammad Anisur Rahman Khan', 'MALE', '123', 
    'cccc.jpg', 'anis');
Insert into MCHTI.AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    ENABLED, FULL_NAME, GENDER, PASSWORD, PIC_FILE, 
    USERNAME)
 Values
   (1, 0, 0, 0, 'Ishrat', 
    1, 'Dr. Ishrat Jahan', 'FEMALE', '123', 'abcd.jpg', 
    '1');
Insert into MCHTI.AUTH_USER
   (ID, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, DISPLAY_NAME, 
    EMAIL, ENABLED, FULL_NAME, GENDER, PASSWORD, 
    PIC_FILE, USERNAME)
 Values
   (2, 0, 0, 0, 'Manzarul', 
    'unibd@yahoo.com', 1, 'Manzarul Islam', 'MALE', 'Howlader019', 
    'dddd.jpg', 'Manzarul');
COMMIT;
