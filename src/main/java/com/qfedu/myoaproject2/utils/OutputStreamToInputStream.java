package com.qfedu.myoaproject2.utils;

import java.io.*;

public class OutputStreamToInputStream {
    /**
     * 输入流转byte[ ]
     * @param inStream
     * @return
     * @throws IOException
     */
    private static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }

    /**
     *  字节数组 转file对象
     * @param b 字节数组
     * @param outputFile 目标地址
     * @return
     */
    public static File writeBytesToFile(byte[] b, String outputFile) {
        File file = null;
        FileOutputStream os = null;
        try {
            file = new File(outputFile);
            os = new FileOutputStream(file);
            os.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    // outputStream转inputStream
    public static ByteArrayInputStream parse(OutputStream out) throws Exception {
        return new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
    }
}
