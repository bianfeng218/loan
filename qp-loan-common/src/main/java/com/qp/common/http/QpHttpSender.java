package com.qp.common.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.qp.common.file.QpFileOperator;


/**
 * http请求发送类
 * 
 * @author GaoXiang Date: 2015-5-26
 */
public class QpHttpSender
{
    
    private QpFileOperator fileOperator = QpFileOperator.getInstance();
    
    private static QpHttpSender instance = new QpHttpSender();
    
    private QpHttpSender()
    {
    }
    
    public static QpHttpSender getInstance()
    {
        return instance;
    }
    
    /**
     * 发送Get请求
     * 
     * @param url
     * @return
     * @throws Exception
     */
    public String sendGetRequest(String url) throws Exception
    {
        if (url.toLowerCase().startsWith("http://"))
        {
            return sendHttpGetRequest(url);
        }
        else if (url.toLowerCase().startsWith("https://"))
        {
            return sendHttpsGetRequest(url);
        }
        
        return null;
    }
    
    /**
     * 发送http get请求
     * 
     * @param httpUrl
     * @return
     * @throws IOException
     */
    private String sendHttpGetRequest(String httpUrl) throws IOException
    {
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            URL url = new URL(httpUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            isr = new InputStreamReader(url.openStream(), "utf-8");
            br = new BufferedReader(isr);
            
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            fileOperator.closeResources(isr, br);
        }
        
        return sb.toString();
    }
    
    /**
     * 发送HTTPS GET请求
     * 
     * @param url
     * @return
     * @throws Exception
     */
    private String sendHttpsGetRequest(String url) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            URL console = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            is = conn.getInputStream();
            isr = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(isr);
            String ret = "";
            while (ret != null)
            {
                ret = br.readLine();
                if (ret != null && !ret.trim().equals(""))
                {
                    sb.append(ret);
                }
            }
            conn.disconnect();
        }
        finally
        {
            fileOperator.closeResources(is, isr, br);
        }
        
        return sb.toString();
    }
    
    /**
     * 利用URL发起POST请求，并接收返回信息
     * 
     * @param url 请求URL
     * @param params 请求参数
     * @return 响应内容
     * @throws Exception
     */
    public String sendPostRequest(String url, Map<String, String> params) throws Exception
    {
        if (url.toLowerCase().startsWith("http://"))
        {
            return sendHttpPostRequest(url, params);
        }
        else if (url.toLowerCase().startsWith("https://"))
        {
            return sendHttpsPostRequest(url, params);
        }
        
        return null;
    }
    
    /**
     * 利用URL发起POST请求，并接收返回信息
     * 
     * @param url 请求URL
     * @param params 请求参数
     * @return 响应内容
     * @throws IOException
     */
    private String sendHttpPostRequest(String url, Map<String, String> params) throws IOException
    {
        StringBuffer result = new StringBuffer();
        StringBuffer paramString = new StringBuffer();
        OutputStream os = null;
        DataOutputStream dos = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            URL urls = new URL(url);
            HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
            uc.setRequestMethod("POST");
            uc.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            uc.setRequestProperty("charset", "UTF-8");
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setReadTimeout(10000);
            uc.setConnectTimeout(10000);
            os = uc.getOutputStream();
            dos = new DataOutputStream(os);
            
            // 组装参数
            for (Entry<String, String> entry : params.entrySet())
            {
                paramString.append(entry.getKey());
                paramString.append("=");
                paramString.append(entry.getValue());
                paramString.append("&");
            }
            // 去掉末尾的&
            String param = paramString.length() > 0 ? paramString.substring(0, paramString.length() - 1) : paramString.toString();
            dos.write(param.getBytes("utf-8"));
            dos.flush();
            isr = new InputStreamReader(uc.getInputStream(), "utf-8");
            br = new BufferedReader(isr);
            String readLine = "";
            while ((readLine = br.readLine()) != null)
            {
                result.append(readLine);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            fileOperator.closeResources(os, dos, isr, br);
        }
        
        return result.toString();
    }
    
    /**
     * 发起HTTPS POST请求，并接收返回信息
     * 
     * @param url 请求URL
     * @param params 请求参数
     * @return 响应内容
     * @throws Exception
     */
    private String sendHttpsPostRequest(String url, Map<String, String> params) throws Exception
    {
        OutputStream os = null;
        PrintWriter out = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer result = new StringBuffer();
        try
        {
            TrustManager[] tm = { new TrustAnyTrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
            
            // 设置通用的请求属性
            conn.setSSLSocketFactory(ssf);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            // 获取URLConnection对象对应的输出流
            os = conn.getOutputStream();
            out = new PrintWriter(os);
            
            // 发送请求参数
            StringBuffer paramString = new StringBuffer();
            for (Entry<String, String> entry : params.entrySet())
            {
                paramString.append(entry.getKey());
                paramString.append("=");
                paramString.append(entry.getValue());
                paramString.append("&");
            }
            // 去掉末尾的&
            String param = paramString.length() > 0 ? paramString.substring(0, paramString.length() - 1) : paramString.toString();
            out.print(param);
            
            // flush输出流的缓冲
            out.flush();
            
            // 定义BufferedReader输入流来读取URL的响应
            is = conn.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null)
            {
                result.append(line);
            }
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            fileOperator.closeResources(is, isr, br, os, out);
        }
        
        return result.toString();
    }
    
    private static class TrustAnyTrustManager implements X509TrustManager
    {
        
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
        {
        }
        
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
        {
        }
        
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[] {};
        }
    }
    
    private static class TrustAnyHostnameVerifier implements HostnameVerifier
    {
        
        public boolean verify(String hostname, SSLSession session)
        {
            return true;
        }
    }
}
