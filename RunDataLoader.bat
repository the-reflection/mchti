java -DsleepMin=10 -DdriverDest=oracle.jdbc.OracleDriver -DurlDest="jdbc:oracle:thin:@localhost:1521:xe" -DuserDest=mchti -DpasswordDest="mchti321#" -DdriverSrc=sun.jdbc.odbc.JdbcOdbcDriver -DurlSrc="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:\\Program Files\\ZKTeco\\att2000.mdb" -DuserSrc= -DpasswordSrc= -classpath "lib/*;classes/" org.reflection.service.DataLoader 