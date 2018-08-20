package com.zkk.test.util.git;


//import com.aliyun.oss.common.utils.HttpUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhangkaikai on 2018/8/20 020 14:24 .
 */

/**
 * HTTP 请求工具类
 *
 * @author : lmc
 * @version : 1.0.0
 * @date : 2017/6/25
 * @see : TODO
 */
public class HttpUtils {
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 60000;

    /**
     * 日志
     **/
    protected static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

//    /**
//     * 发送 GET 请求（HTTP），不带输入数据
//     *
//     * @param url
//     * @return
//     */
//    public static String doGet(String url) {
//        return doGet(url, new HashMap<String, Object>());
//    }
//
//    /**
//     * 发送 GET 请求（HTTP），K-V形式
//     *
//     * @param url
//     * @param params
//     * @return
//     */
//    public static String doGet(String url, Map<String, Object> params) {
//        String apiUrl = url;
//        StringBuffer param = new StringBuffer();
//        int i = 0;
//        for (String key : params.keySet()) {
//            if (i == 0)
//                param.append("?");
//            else
//                param.append("&");
//            param.append(key).append("=").append(params.get(key));
//            i++;
//        }
//        apiUrl += param;
//        String result = null;
//        HttpClient httpclient = new DefaultHttpClient();
//        try {
//            HttpGet httpPost = new HttpGet(apiUrl);
//            HttpResponse response = httpclient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            result = EntityUtils.toString(entity, "UTF-8");
//        } catch (IOException e) {
//            logger.error(apiUrl, e);
//        }
//        logger.info("url:" + apiUrl + "-----result:" + result);
//        return result;
//    }
//
//
//    /**
//     * 发送 GET 请求（HTTP），K-V形式
//     *
//     * @param url
//     * @return
//     */
//    public static byte[] doGetReturnByte(String url,File filePathName) {
//        String apiUrl = url;
//        String result = null;
//        HttpClient httpclient = new DefaultHttpClient();
//        try {
//            HttpGet httpPost = new HttpGet(apiUrl);
//            HttpResponse response = httpclient.execute(httpPost);
//
//
//
//
//            HttpEntity entity = response.getEntity();
//            byte[] bytes = EntityUtils.toByteArray(entity);
//            return bytes;
//        } catch (IOException e) {
//            logger.error(apiUrl, e);
//        }
//        logger.info("url:" + apiUrl + "-----result:" + result);
//        return null;
//    }
//
//
//    /**
//     * 发送 POST 请求（HTTP），不带输入数据
//     *
//     * @param apiUrl
//     * @return
//     */
//    public static String doPost(String apiUrl) {
//        return doPost(apiUrl, new HashMap<String, Object>());
//    }
//
//    /**
//     * 发送 POST 请求（HTTP），K-V形式
//     *
//     * @param apiUrl API接口URL
//     * @param params 参数map
//     * @return
//     */
//    public static String doPost(String apiUrl, Map<String, Object> params) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        String httpStr = null;
//        HttpPost httpPost = new HttpPost(apiUrl);
//        CloseableHttpResponse response = null;
//
//        try {
//            httpPost.setConfig(requestConfig);
//            List<NameValuePair> pairList = new ArrayList<>(params.size());
//            for (Map.Entry<String, Object> entry : params.entrySet()) {
//                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
//                        .getValue().toString());
//                pairList.add(pair);
//            }
//            logger.info("【post parameters】:{}", pairList);
//            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
//            response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            httpStr = EntityUtils.toString(entity, "UTF-8");
//        } catch (IOException e) {
//            logger.error(httpPost.toString(), e);
//        } finally {
//            if (response != null) {
//                try {
//                    EntityUtils.consume(response.getEntity());
//                } catch (IOException e) {
//                    logger.error(apiUrl, e);
//                }
//            }
//        }
//        logger.debug("url:" + apiUrl + "-----result:" + httpStr);
//        return httpStr;
//    }
//
//    /**
//     * @param apiUrl
//     * @param param
//     * @param header 自定义header
//     * @return
//     */
//    public static String doPost(String apiUrl, String param, Header header) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        String httpStr = null;
//        HttpPost httpPost = new HttpPost(apiUrl);
//        CloseableHttpResponse response = null;
//        try {
//            if (null != header) {
//                httpPost.setHeader(header);
//            }
//            httpPost.setConfig(requestConfig);
//            StringEntity stringEntity = new StringEntity(param, "UTF-8");//解决中文乱码问题
//            stringEntity.setContentEncoding("UTF-8");
//            httpPost.setEntity(stringEntity);
//            response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            httpStr = EntityUtils.toString(entity, "UTF-8");
//        } catch (IOException e) {
//            logger.error(httpPost.toString(), e);
//        } finally {
//            if (response != null) {
//                try {
//                    EntityUtils.consume(response.getEntity());
//                } catch (IOException e) {
//                    logger.error(httpPost.toString(), e);
//                }
//            }
//        }
//        logger.info("url:" + apiUrl + "-----result:" + httpStr);
//        return httpStr;
//    }
//
//    /**
//     * 发送 POST 请求（HTTP），JSON形式
//     *
//     * @param apiUrl
//     * @param
//     * @return
//     */
//    public static String doPost(String apiUrl, String param) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        String httpStr = null;
//        HttpPost httpPost = new HttpPost(apiUrl);
//        CloseableHttpResponse response = null;
//        try {
//            httpPost.setConfig(requestConfig);
//            StringEntity stringEntity = new StringEntity(param, "UTF-8");//解决中文乱码问题
//            stringEntity.setContentEncoding("UTF-8");
//            httpPost.setEntity(stringEntity);
//            response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            httpStr = EntityUtils.toString(entity, "UTF-8");
//        } catch (IOException e) {
//            logger.error(httpPost.toString(), e);
//        } finally {
//            if (response != null) {
//                try {
//                    EntityUtils.consume(response.getEntity());
//                } catch (IOException e) {
//                    logger.error(httpPost.toString(), e);
//                }
//            }
//        }
//        logger.info("url:" + apiUrl + "-----result:" + httpStr);
//        return httpStr;
//    }
//
//    /**
//     * 发送 SSL POST 请求（HTTPS），K-V形式
//     *
//     * @param apiUrl API接口URL
//     * @param params 参数map
//     * @return
//     */
//    public static String doPostSSL(String apiUrl, Map<String, Object> params) {
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
//        HttpPost httpPost = new HttpPost(apiUrl);
//        CloseableHttpResponse response = null;
//        String httpStr = null;
//        try {
//            httpPost.setConfig(requestConfig);
//            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
//            for (Map.Entry<String, Object> entry : params.entrySet()) {
//                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
//                        .getValue().toString());
//                pairList.add(pair);
//            }
//            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
//            response = httpClient.execute(httpPost);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != HttpStatus.SC_OK) {
//                return null;
//            }
//            HttpEntity entity = response.getEntity();
//            if (entity == null) {
//                return null;
//            }
//            httpStr = EntityUtils.toString(entity, "utf-8");
//        } catch (Exception e) {
//            logger.error(httpPost.toString(), e);
//        } finally {
//            if (response != null) {
//                try {
//                    EntityUtils.consume(response.getEntity());
//                } catch (IOException e) {
//                    logger.error(httpPost.toString(), e);
//                }
//            }
//        }
//        logger.info("url:" + apiUrl + "-----result:" + httpStr);
//        return httpStr;
//    }
//
//    /**
//     * 发送 SSL POST 请求（HTTPS），JSON形式
//     *
//     * @param apiUrl API接口URL
//     * @param
//     * @return
//     */
//    public static String doPostSSL(String apiUrl, String param) {
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
//        HttpPost httpPost = new HttpPost(apiUrl);
//        CloseableHttpResponse response = null;
//        String httpStr = null;
//        try {
//            httpPost.setConfig(requestConfig);
//            StringEntity stringEntity = new StringEntity(param, "UTF-8");//解决中文乱码问题
//            stringEntity.setContentEncoding("UTF-8");
//            httpPost.setEntity(stringEntity);
//            response = httpClient.execute(httpPost);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != HttpStatus.SC_OK) {
//                return null;
//            }
//            HttpEntity entity = response.getEntity();
//            if (entity == null) {
//                return null;
//            }
//            httpStr = EntityUtils.toString(entity, "utf-8");
//        } catch (Exception e) {
//            logger.error(httpPost.toString(), e);
//        } finally {
//            if (response != null) {
//                try {
//                    EntityUtils.consume(response.getEntity());
//                } catch (IOException e) {
//                    logger.error(httpPost.toString(), e);
//                }
//            }
//        }
//        logger.info("url:" + apiUrl + "-----result:" + httpStr);
//        return httpStr;
//    }
//
//    /**
//     * 创建SSL安全连接
//     *
//     * @return
//     */
//    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
//        SSLConnectionSocketFactory sslsf = null;
//        try {
//            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
//
//                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                    return true;
//                }
//            }).build();
//            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
//
//                @Override
//                public boolean verify(String arg0, SSLSession arg1) {
//                    return true;
//                }
//
//                @Override
//                public void verify(String host, SSLSocket ssl) throws IOException {
//                }
//
//                @Override
//                public void verify(String host, X509Certificate cert) throws SSLException {
//                }
//
//                @Override
//                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
//                }
//            });
//        } catch (GeneralSecurityException e) {
//            logger.error("ssl", e);
//        }
//        return sslsf;
//    }
//
//    /***
//     *发送POST请求 文件上传
//     * @param apiUrl 上传地址
//     * @param fileNamePath 文件绝对路径
//     * @param paramsMap 上传其他参数
//     * @return
//     * @throws Exception
//     */
//    public static String doPostFileUpload(String apiUrl, File fileNamePath, Map<String, Object> paramsMap) throws Exception {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost post = new HttpPost(apiUrl);
//        String httpStr = null;
//        HttpResponse response = null;
//        try {
//            FileBody fileBody = new FileBody(fileNamePath);
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            builder.addPart("file", fileBody);
//            Set<Map.Entry<String, Object>> entries = paramsMap.entrySet();
//            for (Map.Entry<String, Object> entry : entries) {
//                builder.addPart(entry.getKey(), new StringBody(entry.getValue() != null ? entry.getValue().toString() : ""));
//            }
//            HttpEntity entity = builder.build();
//            post.setEntity(entity);
//            response = httpclient.execute(post);
//            httpStr = EntityUtils.toString(response.getEntity(), "UTF-8");
//        } catch (IOException e) {
//            logger.error(post.toString(), e);
//        } catch (ParseException e) {
//            logger.error(post.toString(), e);
//        } finally {
//            if (response != null) {
//                try {
//                    EntityUtils.consume(response.getEntity());
//                } catch (IOException e) {
//                    logger.error(post.toString(), e);
//                }
//            }
//        }
//        logger.info("url:" + apiUrl + "-----result:" + httpStr);
//        return httpStr;
//    }
//
//    /**
//     * 测试方法
//     *
//     * @param args
//     */
//    public static void main(String[] args) throws Exception {
//        String url = "http://192.168.23.121:18112/add_mlearn";
//        String fileName = "C:\\Users\\lmc\\Desktop\\test\\test.java";
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("filename", "fileName");
//        map.put("mlname", "测试添加");
//        String s = doPostFileUpload(url, new File(fileName), map);
//        System.out.println("上传文件结果:" + s);
//    }
}