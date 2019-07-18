package cn.bucheng.flyway;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbUtil {
    private static Logger LOG = LoggerFactory.getLogger(DbUtil.class);
    private static final String NOT_DEFINED = "notDefined";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String SQL_QUERY_DB = "SHOW DATABASES";
    private static final String KEY_FLYWAY_URL = "flyway.url";
    private static final String KEY_FLYWAY_USER = "flyway.user";
    private static final String KEY_FLYWAY_PSW = "flyway.password";

    public DbUtil() {
    }

    public static void initDbAndFlyWay() throws Exception {
//        Properties p = loadPropertyFromStream();
//        checkDbAutoCreatedIfNotExists(p);
        Properties p = new Properties();
        p.put("flyway.url","jdbc:mysql://127.0.0.1:3306/ifaas_basicinfo?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&rewriteBatchedStatements=true&useSSL=false&serverTimezone=GMT%2B8");
        p.put("flyway.user","root");
        p.put("flyway.password","introcks1234");
        p.put("flyway.locations","classpath:db/migration");
        initFlyWayScripts(p);
    }

    private static void initFlyWayScripts(Properties property) {
        Flyway flyway = new Flyway();
        flyway.configure(property);
        flyway.repair();
        flyway.migrate();
    }

    private static void checkDbAutoCreatedIfNotExists(Properties property) throws IOException {
        Connection conn = null;
        Statement stmt = null;
        String flywayUrl = property.getProperty("flyway.url", "notDefined");
        String username = property.getProperty("flyway.user", "notDefined");
        String password = property.getProperty("flyway.password", "notDefined");
        if ("notDefined".equals(flywayUrl) || "notDefined".equals(username) || "notDefined".equals(password)) {
            LOG.warn("database info of dbUrl or username or password is wrong!");
        }

        String dbUrl = flywayUrl.substring(0, flywayUrl.indexOf(47, 15)) + "?connectTimeout=5000&socketTimeout=5000";
        int endOfDbName = flywayUrl.contains("?") ? flywayUrl.indexOf(63) : 100;
        String dbName = flywayUrl.substring(flywayUrl.indexOf(47, 15) + 1, endOfDbName);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            LOG.info("Connecting to database with url :" + dbUrl);
            DriverManager.setLoginTimeout(5);
            LOG.info("Before Connecting... ");
            conn = DriverManager.getConnection(dbUrl, username, password);
            LOG.info("End Of Connecting... ");
            LOG.info("Connect success and ready to check database...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW DATABASES");

            while(true) {
                String dbNameOfQueryResult;
                if (!rs.next()) {
                    LOG.info("Ready to create database : " + dbName);
                    dbNameOfQueryResult = "CREATE DATABASE IF NOT EXISTS " + dbName + " DEFAULT CHARSET utf8 COLLATE utf8_general_ci; ";
                    stmt.executeUpdate(dbNameOfQueryResult);
                    LOG.info("Database created successfully...");
                    break;
                }

                dbNameOfQueryResult = rs.getString("Database");
                LOG.debug("Found database: " + dbNameOfQueryResult);
                if (dbNameOfQueryResult != null && dbNameOfQueryResult.equalsIgnoreCase(dbName)) {
                    LOG.info("Database " + dbName + " is already exists in the DB : " + dbUrl + " , need not to created!");
                    return;
                }
            }
        } catch (SQLException var29) {
            var29.printStackTrace();
        } catch (Exception var30) {
            var30.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException var28) {
                var28.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var27) {
                var27.printStackTrace();
            }

        }

        LOG.info("Check database finished...");
    }

    private static Properties loadPropertyFromStream() throws Exception {
        Properties property = new Properties();

        try {
            InputStream fi = getFlywayConfigFileAsStream();
            property.load(fi);
            return property;
        } catch (FileNotFoundException var3) {
            LOG.warn("flyway.conf load failed!");
            throw var3;
        } catch (Exception var4) {
            LOG.warn("flyway init failed!");
            throw var4;
        }
    }

    private static InputStream getFlywayConfigFileAsStream() throws FileNotFoundException {
        ArrayList<File> candidateFileArr = new ArrayList();
        File fileDeploy = Paths.get("./", "flyway.conf").toFile();
        File fileConfig = Paths.get("./config/", "flyway.conf").toFile();
        candidateFileArr.add(fileDeploy);
        candidateFileArr.add(fileConfig);
        Iterator var3 = candidateFileArr.iterator();

        while(true) {
            File file;
            do {
                do {
                    do {
                        if (!var3.hasNext()) {
                            try {
                                return getFileFromClassPath();
                            } catch (Exception var6) {
                                LOG.warn("classpath found flyway.conf failed!");
                                LOG.warn("flyway config file no found! please check ...");
                                throw new FileNotFoundException();
                            }
                        }

                        file = (File)var3.next();
                    } while(!file.exists());
                } while(!file.isFile());
            } while(!file.canRead());

            LOG.warn("load flyway.conf ,the file location is :" + file.getPath());

            try {
                return new FileInputStream(file);
            } catch (Exception var7) {
                LOG.warn("load flyway.conf in :" + file.getPath() + " failed!");
            }
        }
    }

    private static InputStream getFileFromClassPath() throws FileNotFoundException {
        InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("db/flyway.conf");
        if (in != null) {
            LOG.info("load flyway.conf from classpath success!");
            return in;
        } else {
            throw new FileNotFoundException();
        }
    }
}