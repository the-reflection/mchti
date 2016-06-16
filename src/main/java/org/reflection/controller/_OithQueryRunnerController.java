package org.reflection.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class _OithQueryRunnerController {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "/queryRunner/execNativeQuery", method = RequestMethod.POST)
    public @ResponseBody
    String execNativeQuery(@RequestParam(value = "query") String query) {
        System.out.println("finding execNativeQuery: code: " + query);

        try {
            Connection conn = dataSource.getConnection();
            return "Native result: " + getTableOnly(conn, query);
        } catch (Exception e) {
            return "err: " + e;
        }
    }

    @RequestMapping(value = "/queryRunner/execOrmQuery", method = RequestMethod.POST)
    public @ResponseBody
    String execOrmQuery(@RequestParam(value = "query") String query) {
        System.out.println("finding execOrmQuery: code: " + query);

        try {
            Session session = sessionFactory.openSession();
            Query query1 = session.createQuery(query);
            List gg = query1.list();

            session.close();

            return "ORM result: " + getTableOnly(gg);
        } catch (Exception e) {
            return "err: " + e;
        }
    }

    public String getTableOnly(List query) {
        String tableHead = new String();
        String tableRow = new String();

        //            int numberOfColumns = metaData.getColumnCount();
        //            
        //            for (int column = 1; column <= numberOfColumns; column++) {
        //                String tableHeadx = metaData.getColumnLabel(column);
        //                tableHeadx = tableHeadx.toUpperCase();
        //                tableHead += "<th>" + tableHeadx + "</th>";
        //            }
        //            
        for (Object object : query) {
            tableRow += "<tr>";
            if (object instanceof Object[]) {
                Object[] mmm = (Object[]) object;

                for (Object object1 : mmm) {
                    tableRow += "<td>" + object1 + "</td>";
                }
            } else {
                tableRow += "<td>" + object + "</td>";
            }
            tableRow += "</tr>";
        }
        return "<table class=\"style-table\">" + tableHead + tableRow + "</table>";
        //return "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"style-table\">" + tableHead + tableRow + "</table>";
    }

    public String getTableOnly(Connection conn, String query) {
        String tableHead = new String();
        String tableRow = new String();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            for (int column = 1; column <= numberOfColumns; column++) {
                String tableHeadx = metaData.getColumnLabel(column);
                tableHeadx = tableHeadx.toUpperCase();
                tableHead += "<th>" + tableHeadx + "</th>";
            }

            while (resultSet.next()) {
                tableRow += "<tr>";
                for (int i = 1; i <= numberOfColumns; i++) {
                    tableRow += "<td>" + resultSet.getObject(i) + "</td>";
                }
                tableRow += "</tr>";
            }

            resultSet.close();
            statement.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        //return "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"style-table\">" + tableHead + tableRow + "</table>";
        return "<table class=\"style-table\">" + tableHead + tableRow + "</table>";
    }

    @RequestMapping(value = "/queryRunner", method = RequestMethod.GET)
    public String getQueryRunnerPage() {

        return "etc/queryRunnerPage";
    }
}
