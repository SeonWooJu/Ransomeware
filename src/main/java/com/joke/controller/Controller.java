package com.joke.controller;

import java.util.List;

import com.joke.logic.FileEncryptionEncode;
import com.joke.object.PathAndResult;
import com.joke.view.NewView;
import com.joke.view.View;

public class Controller {

    private final static String KEY = "i_really_want_to_go_to_college_0";
    private final static String IV = "0123456789abcdef";

    public static void startFileEncryption() {
        NewView newView = new NewView();
        newView.Loading("Loding...");

        List<PathAndResult> pathAndResultsList = new FileEncryptionEncode().getFileEncryptionEncodeResultList(KEY, IV);

        newView.Close();

        new View(pathAndResultsList, KEY, IV);
    }

}
