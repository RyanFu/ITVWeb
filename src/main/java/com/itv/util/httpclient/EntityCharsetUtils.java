package com.itv.util.httpclient;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.log4j.Logger;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class EntityCharsetUtils{
    private final static Logger log=Logger.getLogger(EntityCharsetUtils.class);
	public static String toString(
            final HttpEntity entity, final Charset defaultCharset) throws IOException, ParseException {
        boolean cha=false;
		if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        InputStream instream = entity.getContent();
        if (instream == null) {
            return null;
        }
        try {
            if (entity.getContentLength() > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
            }
            int i = (int)entity.getContentLength();
            if (i < 0) {
                i = 4096;
            }
            Charset charset = null;
            try {
                String c=getContentCharSet(entity);
                if(c!=null&&!c.equals("")){
                    log.info("httpclient get charset : " + c);
                    charset=Charset.forName(c);
                }
            } catch (final UnsupportedCharsetException ex) {
                throw new UnsupportedEncodingException(ex.getMessage());
            }
            UniversalDetector detector=null;
            if (charset == null) {
            	detector = new UniversalDetector(null);
            }
            ByteArrayBuffer bytebuffer=new ByteArrayBuffer(i);
            byte [] tmp_=new byte[1024];
            int len;
            int j=0;
            
            while((len=instream.read(tmp_))!=-1){
            	bytebuffer.append(tmp_, 0, len);
            	if(j<4&&detector!=null){
            		detector.handleData(tmp_, 0, len);
            	}
            	j++;
            }
            if(detector!=null){
	            detector.dataEnd();
	            String encoding = detector.getDetectedCharset();
	            detector.reset();
                encoding=encoding==null?"GBK":encoding;
                charset=Charset.forName(encoding);
                log.info("下载内容使用的编码: "+charset);
            }
            if (charset == null) {
                charset = defaultCharset;
            }
            return new String(bytebuffer.toByteArray(),charset);
        } finally {
            instream.close();
        }
    }

    public static String getContentCharSet(final HttpEntity entity)
            throws ParseException {

        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        String charset = null;
        if (entity.getContentType() != null) {
            HeaderElement values[] = entity.getContentType().getElements();
            if (values.length > 0) {
                NameValuePair param = values[0].getParameterByName("charset");
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }
        return charset;
    }
}
