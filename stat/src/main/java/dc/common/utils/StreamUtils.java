/**
 * Filename:    StreamUtils.java
 * Description:
 * Copyright:   Copyright (c)2012
 * Company:
 *
 * @author: senRsl dong_M@yeah.net
 * @version: 1.0
 * Create at:   2012-5-6 下午05:02:28
 * <p>
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2012-5-6      senRsl      1.0         1.0 Version
 */

package dc.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static dc.common.Global.ENCODE_UTF_8;

/**
 * 流
 *
 * @author senRsl
 * @ClassName: StreamUtils
 * @Package: dc.common.utils
 * @CreateTime: 2012-5-6 下午05:02:28
 */
public class StreamUtils {

    /**
     * 流操作
     *
     * @param is 输入流
     * @return 流的二进制解析
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream is) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; //每次读取多少字节

        int len = 0;

        //循环读取，最后读不到(读取到的长度为-1)时停止
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len); //写入内存
        }
        is.close();

        return os.toByteArray();
    }

    /**
     * 流转字符串
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String is2string(InputStream is) throws IOException {
        String buf;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, ENCODE_UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        is.close();
        buf = sb.toString();
        return buf;
    }

}
