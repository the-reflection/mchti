package ServiceData;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataLoader {

    private Connection connDest;
    private Connection connSrc;
    /*
    java -DsleepMin=10 -DdriverDest=oracle.jdbc.OracleDriver -DurlDest="jdbc:oracle:thin:@localhost:1521:xe" -DuserDest=mchti -DpasswordDest="mchti321#" -DdriverSrc=sun.jdbc.odbc.JdbcOdbcDriver -DurlSrc="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\\Users\\mbadiuzzaman\\Desktop\\prog_support\\attBackup.mdb" -DuserSrc= -DpasswordSrc= -classpath "lib/*;classes/" org.reflection.service.DataLoader 

    java
    -DsleepMin=10
    -DdriverDest=oracle.jdbc.OracleDriver
    -DurlDest=jdbc:oracle:thin:@localhost:1521:xe
    -DuserDest=mchti
    -DpasswordDest=mchti321#
    -DdriverSrc=sun.jdbc.odbc.JdbcOdbcDriver
    -DurlSrc=jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\\Users\\mbadiuzzaman\\Desktop\\prog_support\\attBackup.mdb
    -DuserSrc=
    -DpasswordSrc=
    -classpath "lib/*;classes/" org.reflection.service.DataLoader 
    */
    private static final int SLEEP_MIN = Integer.parseInt(System.getProperty("sleepMin"));

    private static final String DRIVER_DEST = System.getProperty("driverDest");
    private static final String URL_DEST = System.getProperty("urlDest");
    private static final String USER_DEST = System.getProperty("userDest");
    private static final String PASSWORD_DEST = System.getProperty("passwordDest");

    private static final String DRIVER_SRC = System.getProperty("driverSrc");
    private static final String URL_SRC = System.getProperty("urlSrc");
    private static final String USER_SRC = System.getProperty("userSrc");
    private static final String PASSWORD_SRC = System.getProperty("passwordSrc");

    public static void main(String[] args) {
        final DataLoader aa = new DataLoader();

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                aa.init();
                while (true) {
                    aa.doit();
                    try {
                        Thread.sleep(SLEEP_MIN * 60 * 1000);
                    } catch (Exception e) {
                    }
                }
            }
        });
        th.start();
    }

    private synchronized void init() {
        try {
            Class.forName(DRIVER_SRC);
            Class.forName(DRIVER_DEST);

            connDest = DriverManager.getConnection(URL_DEST, USER_DEST, PASSWORD_DEST);
            connSrc = DriverManager.getConnection(URL_SRC, USER_SRC, PASSWORD_SRC);
            System.out.println("both connection ok");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("err init data load: " + e);
        }
    }

    private synchronized void doit() {
        try {
            Object mxTime = null;
            try {
                Statement st = connDest.createStatement();
                ResultSet rs = st.executeQuery("SELECT MAX(A.TRANSACTION_TIME) FROM PROC_OUT_ATTN_DAILY A");
                while (rs.next()) {
                    mxTime = rs.getObject(1);
                }
                rs.close();
                st.close();
            } catch (Exception e) {
                System.out.println("err 7456: " + e);
            }

            System.out.println("max now: " + mxTime);

            PreparedStatement stSrc;
            if (mxTime != null) {
                stSrc = connSrc.prepareStatement("SELECT DISTINCT U.BADGENUMBER, C.CHECKTIME FROM CHECKINOUT C, USERINFO U WHERE U.USERID=C.USERID AND C.CHECKTIME> ?-3 ");
                stSrc.setObject(1, mxTime);
            } else {
                stSrc = connSrc.prepareStatement("SELECT DISTINCT U.BADGENUMBER, C.CHECKTIME FROM CHECKINOUT C, USERINFO U WHERE U.USERID=C.USERID");
            }

            ResultSet rsSrc = stSrc.executeQuery();
            PreparedStatement stDest = connDest.prepareStatement("INSERT INTO PROC_OUT_ATTN_DAILY ( EMPLOYEE_ID, TRANSACTION_TIME, ATTN_MODE) VALUES ((SELECT ID FROM EMPLOYEE WHERE CODE=?), ?, 'FP')");

            int rec = 0;
            while (rsSrc.next()) {

                try {
                    stDest.setObject(1, rsSrc.getObject(1));
                    stDest.setObject(2, rsSrc.getObject(2));
                    stDest.executeUpdate();
                    rec++;
                } catch (Exception e) {
                    //System.out.println("err 7623: " + e);
                }
            }

            rsSrc.close();
            stSrc.close();
            stDest.close();
            System.out.println("upload rec. " + rec);
        } catch (Exception e) {
            System.out.println("err 1298: " + e);
        }
    }
}
