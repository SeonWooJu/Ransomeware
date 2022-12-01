package com.joke.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PathRead {

    public HashMap<String, List<String>> getRootByPathRead() {
        HashMap<String, List<String>> rootByPath = new HashMap<>();

        for (File root : _getRootList()) {
            if ("C:\\".equals(root.getAbsoluteFile().toString())) {
                rootByPath.put("C:\\Users", _getPathRead("C:\\Users"));
            } else {
                rootByPath.put(root.getAbsolutePath().toString(), _getPathRead(root.getAbsolutePath().toString()));
            }
        }

        return rootByPath;
    }

    private List<String> _getPathRead(String path) {
        List<String> pathList = new ArrayList<>();

        File dir = new File(path);

        File files[] = dir.listFiles();
        
        for (File file : files) {
            if (file.isDirectory()){
                if (_getLowDirectoryCheck(file.getAbsolutePath())) {
                    for (String path_ : _getPathRead(file.getAbsolutePath())) {
                        pathList.add(path_);
                    }
                }
            } else {
                pathList.add(file.getAbsolutePath());
            }
        }

        return pathList;
    }
    
    private boolean _getLowDirectoryCheck(String path) {
        try {
            File dir = new File(path);

            return dir.listFiles().length >= 1;
        } catch (Exception e) {
            return false;
        }
    }

    private File[] _getRootList() {
        return File.listRoots();
    }

    public String[] pathAndResultsList() {
        return null;
    }
}
