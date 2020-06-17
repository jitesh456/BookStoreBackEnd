package com.bookstoreapp.util.implementation;

import com.bookstoreapp.util.IVerifyEmailTemplate;
import org.springframework.stereotype.Component;

@Component
public class VerifyEmailTemplate implements IVerifyEmailTemplate {

    public String verifyEmailTemplet(String url)
    {
        return ("<html>\n" +
                "\n" +
                "<head>\n" +
                "\t<style>\n" +
                "body {\n" +
                "  background-color: grey;\n" +
                "}\n" +
                "\n" +
                "\n" +
                ".app-header {\n" +
                "  top:0;\n" +
                "  background-color: #9b2020;\n" +
                "  height: 40px;\n" +
                "alight-content:center;"+
                "font-Size:18px;"+
                "  width: 100%;\n" +
                "  color: white;\n" +
                "}\n" +
                ".button{\n" +
                "\tbackground-color: maroon;\n" +
                "\tpadding: 10px 5px;\n" +
                "\tpadding-left: 10px;\n" +
                "\tpadding-right:10px;\n" +
                "\tcolor: white;\n" +
                "\tfont-weight: bold;\n" +
                "}"+

                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<header class=\"app-header\">\n Bug Buster Store" +
                "</header>\n" +
                "\n" +
                "<div >\n" +
                "\n" +
                "<p>Thank you for registration.Please verify your email in order to continue Shopping.Click on Verify Button </p>\n" +
                "\n" +
                "<a href="+url+"><Button class=\"button\">Verify Email</Button></a>"+
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html> \n");
    }
}
