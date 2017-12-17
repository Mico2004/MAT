package com.qualitest.platforms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by Lenovo on 07/09/2017.
 */
public class ProcessManager {
    public static String executeProccess(String... commands) {
        String s = null;
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));
            s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        return s;
    }

}
