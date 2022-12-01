package com.joke.logic;

import java.util.List;

import com.joke.object.PathAndResult;
import com.joke.view.NewView;

public class ButtonLogic {
    
    public void Decode(String key, String iv, String pw) {
        NewView newView = new NewView();
        List<PathAndResult> pathAndResultsList = null;

        newView.Loading("Decode...");
            
        pathAndResultsList = new FileEncryptionDecode().getFileEncryptionDecodeResultList(key, iv);

        newView.Close();

        newView.Report(pathAndResultsList, "Decode Report");
    }
}
