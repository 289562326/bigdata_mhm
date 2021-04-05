//package com.mhm.bghbase;
//
//import ch.qos.logback.classic.db.names.TableName;
//import com.mhm.bghbase.security.LoginUtil;
//import javafx.scene.control.Cell;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.CellUtil;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.apache.hadoop.hbase.client.HBaseAdmin;
//import org.apache.hadoop.hbase.client.ResultScanner;
//import org.apache.hadoop.hbase.security.User;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.*;
//
///**
// * @author MHm
// * @date 2020-8-25 15:59
// */
//public class HBaseApi {
//
//    private static String CONF_DIR = null;
//    private static final String ZOOKEEPER_DEFAULT_LOGIN_CONTEXT_NAME = "Client";
//    private static final String ZOOKEEPER_DEFAULT_SERVER_PRINCIPAL = "zookeeper/hadoop";
//
//    //HBase client configuration
//    public static final String HBASE_CLIENT_PROPERTIES = "hbaseclient.properties";
//    private static Configuration conf = null;
//    private static String krb5File = null;
//    private static String userName = null;
//    private static String userKeytabFile = null;
//    private static ClientInfo clientInfo = null;
//    private static String restServerInfo = null;
//
//    private static void login() throws IOException {
//        if (User.isHBaseSecurityEnabled(conf)) {
//
//            userName = clientInfo.getUserName();
//            if (CONF_DIR == null) {
//                ClassLoader classloader = HBaseApi.class.getClassLoader();
//                krb5File = new File(classloader.getResource(clientInfo.getKrb5File()).getPath()).getPath();
//                userKeytabFile = new File(classloader.getResource(clientInfo.getUserKeytabFile()).getPath()).getPath();
//            } else {
//                krb5File = CONF_DIR + clientInfo.getKrb5File();
//                userKeytabFile = CONF_DIR + clientInfo.getUserKeytabFile();
//            }
//            System.out.println("userKeytabFile: " + userKeytabFile);
//            System.out.println("krb5File: " + krb5File);
//            /**
//             * if need to connect zk, please provide jaas info about zk. of course,
//             * you can do it as below:
//             * System.setProperty("java.security.auth.login.config", confDirPath +
//             * "jaas.conf"); but the demo can help you more : Note: if this process
//             * will connect more than one zk cluster, the demo may be not proper. you
//             * can contact us for more help
//             */
//            LoginUtil.setJaasConf(ZOOKEEPER_DEFAULT_LOGIN_CONTEXT_NAME, userName, userKeytabFile);
//            LoginUtil.setZookeeperServerPrincipal(ZOOKEEPER_DEFAULT_SERVER_PRINCIPAL);
//            LoginUtil.login(userName, userKeytabFile, krb5File, conf);
//        }
//    }
//
//    private static void init() throws IOException {
//        // load hbase client info
//        if (clientInfo == null) {
//            clientInfo = new ClientInfo(CONF_DIR, HBASE_CLIENT_PROPERTIES);
//            restServerInfo = clientInfo.getRestServerInfo();
//        }
//        // Default load from conf directory
//        conf = HBaseConfiguration.create();
//
//        conf.addResource("hbase-site.xml");
//
//    }
//
//    /**
//     * 建立连接
//     *
//     * @return
//     */
//    public static Connection getConnection() {
//        try {
//            //获取配置
//            Configuration configuration = getConfiguration();
//            //检查配置
//            HBaseAdmin.checkHBaseAvailable(configuration);
//            return ConnectionFactory.createConnection(configuration);
//        } catch (IOException | ServiceException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 获取配置
//     *
//     * @return
//     */
//    private static Configuration getConfiguration() {
//        try {
//            Properties props = PropertiesLoaderUtils.loadAllProperties("hbase.properties");
//            String clientPort = props.getProperty("hbase.zookeeper.property.clientPort");
//            String quorum = props.getProperty("hbase.zookeeper.quorum");
//
//            logger.info("connect to zookeeper {}:{}", quorum, clientPort);
//
//            Configuration config = HBaseConfiguration.create();
//            config.set("hbase.zookeeper.property.clientPort", clientPort);
//            config.set("hbase.zookeeper.quorum", quorum);
//            return config;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 创建表
//     * @param connection
//     * @param tableName
//     * @param columnFamilies
//     * @throws IOException
//     */
//    public static void createTable(Connection connection, TableName tableName, String... columnFamilies) throws IOException {
//        Admin admin = null;
//        try {
//            admin = connection.getAdmin();
//            if (admin.tableExists(tableName)) {
//                logger.warn("table:{} exists!", tableName.getName());
//            } else {
//                TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(tableName);
//                for (String columnFamily : columnFamilies) {
//                    builder.setColumnFamily(ColumnFamilyDescriptorBuilder.of(columnFamily));
//                }
//                admin.createTable(builder.build());
//                logger.info("create table:{} success!", tableName.getName());
//            }
//        } finally {
//            if (admin != null) {
//                admin.close();
//            }
//        }
//    }
//
//
//    /**
//     * 插入数据
//     *
//     * @param connection
//     * @param tableName
//     * @param rowKey
//     * @param columnFamily
//     * @param column
//     * @param data
//     * @throws IOException
//     */
//    public static void put(Connection connection, TableName tableName,
//    String rowKey, String columnFamily, String column, String data) throws IOException {
//
//        Table table = null;
//        try {
//            table = connection.getTable(tableName);
//            Put put = new Put(Bytes.toBytes(rowKey));
//            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
//            table.put(put);
//        } finally {
//            if (table != null) {
//                table.close();
//            }
//        }
//    }
//
//    /**
//     * 根据row key、column 读取
//     *
//     * @param connection
//     * @param tableName
//     * @param rowKey
//     * @param columnFamily
//     * @param column
//     * @throws IOException
//     */
//    public static String getCell(Connection connection, TableName tableName, String rowKey, String columnFamily, String column) throws IOException {
//        Table table = null;
//        try {
//            table = connection.getTable(tableName);
//            Get get = new Get(Bytes.toBytes(rowKey));
//            get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
//
//            Result result = table.get(get);
//            List<Cell> cells = result.listCells();
//
//            if (CollectionUtils.isEmpty(cells)) {
//                return null;
//            }
//            String value = new String(CellUtil.cloneValue(cells.get(0)), "UTF-8");
//            return value;
//        } finally {
//            if (table != null) {
//                table.close();
//            }
//        }
//    }
//
//    /**
//     * 根据rowkey 获取一行
//     *
//     * @param connection
//     * @param tableName
//     * @param rowKey
//     * @return
//     * @throws IOException
//     */
//    public static Map<String, String> getRow(Connection connection, TableName tableName, String rowKey) throws IOException {
//        Table table = null;
//        try {
//            table = connection.getTable(tableName);
//            Get get = new Get(Bytes.toBytes(rowKey));
//
//            Result result = table.get(get);
//            List<Cell> cells = result.listCells();
//
//            if (CollectionUtils.isEmpty(cells)) {
//                return Collections.emptyMap();
//            }
//            Map<String, String> objectMap = new HashMap<>();
//            for (Cell cell : cells) {
//                String qualifier = new String(CellUtil.cloneQualifier(cell));
//                String value = new String(CellUtil.cloneValue(cell), "UTF-8");
//                objectMap.put(qualifier, value);
//            }
//            return objectMap;
//        } finally {
//            if (table != null) {
//                table.close();
//            }
//        }
//    }
//
//    /**
//     * 扫描权标的内容
//     *
//     * @param connection
//     * @param tableName
//     * @param rowkeyStart
//     * @param rowkeyEnd
//     * @throws IOException
//     */
//    public static List<Map<String, String>> scan(Connection connection, TableName tableName, String rowkeyStart, String rowkeyEnd) throws IOException {
//        Table table = null;
//        try {
//            table = connection.getTable(tableName);
//            ResultScanner rs = null;
//            try {
//                Scan scan = new Scan();
//                if (!StringUtils.isEmpty(rowkeyStart)) {
//                    scan.withStartRow(Bytes.toBytes(rowkeyStart));
//                }
//                if (!StringUtils.isEmpty(rowkeyEnd)) {
//                    scan.withStopRow(Bytes.toBytes(rowkeyEnd));
//                }
//                rs = table.getScanner(scan);
//
//                List<Map<String, String>> dataList = new ArrayList<>();
//                for (Result r : rs) {
//                    Map<String, String> objectMap = new HashMap<>();
//                    for (Cell cell : r.listCells()) {
//                        String qualifier = new String(CellUtil.cloneQualifier(cell));
//                        String value = new String(CellUtil.cloneValue(cell), "UTF-8");
//                        objectMap.put(qualifier, value);
//                    }
//                    dataList.add(objectMap);
//                }
//                return dataList;
//            } finally {
//                if (rs != null) {
//                    rs.close();
//                }
//            }
//        } finally {
//            if (table != null) {
//                table.close();
//            }
//        }
//    }
//
//    /**
//     * 删除表
//     *
//     * @param connection
//     * @param tableName
//     * @throws IOException
//     */
//    public static void deleteTable(Connection connection, TableName tableName) throws IOException {
//        Admin admin = null;
//        try {
//            admin = connection.getAdmin();
//            if (admin.tableExists(tableName)) {
//                //现执行disable
//                admin.disableTable(tableName);
//                admin.deleteTable(tableName);
//            }
//        } finally {
//            if (admin != null) {
//                admin.close();
//            }
//        }
//    }
//}
//
///**
// * hbase client info.
// */
//class ClientInfo {
//    //The rest server info, format like: ip1:port,ip2:port...
//    private String restServerInfo = null;
//    private String userName = null;
//    private String userKeytabFile = null;
//    private String krb5File = null;
//    private String jaasConf = null;
//
//    private Properties clientInfo = null;
//
//    public ClientInfo(String confDir, String hbaseclientFile) throws IOException {
//        InputStream fileInputStream = null;
//        try {
//            clientInfo = new Properties();
//            if (confDir == null) {
//                clientInfo.load(this.getClass().getClassLoader().getResourceAsStream(hbaseclientFile));
//            } else {
//                clientInfo.load(new FileInputStream(new File(confDir + hbaseclientFile)));
//            }
//        } catch (Exception e) {
//            throw new IOException(e);
//        } finally {
//            if (fileInputStream != null) {
//                fileInputStream.close();
//                fileInputStream = null;
//            }
//        }
//        initialize();
//    }
//
//    private void initialize() {
//        restServerInfo = clientInfo.getProperty("rest.server.info");
//        userName = clientInfo.getProperty("user.name");
//        userKeytabFile = clientInfo.getProperty("userKeytabName");
//        krb5File = clientInfo.getProperty("krb5ConfName");
//        jaasConf = clientInfo.getProperty("jaasConfName");
//    }
//
//    public String getRestServerInfo() {
//        return restServerInfo;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getUserKeytabFile() {
//        return userKeytabFile;
//    }
//
//    public String getKrb5File() {
//        return krb5File;
//    }
//
//    public String getJaasConf() {
//        return jaasConf;
//    }
//}
//
