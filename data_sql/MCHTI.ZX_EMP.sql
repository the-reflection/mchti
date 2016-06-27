SET DEFINE OFF;
Insert into ZX_EMP
   (ID, CODE, FULL_NAME, IS_ACTIVE, PIN, 
    REMARKS, SAL, TAX_PAID, VERSION, ZX_GENDER, 
    ZX_LOOKUP_BLOOD_GROUP_ID, ZX_DEPT_ID, ZX_DESG_ID)
 Values
   (58328, '001', 'Mohammad Anisur Rahman Khanx', 1, '123', 
    'na', 111, 11, 1, 'MALE', 
    57271, 58330, 58332);
COMMIT;
