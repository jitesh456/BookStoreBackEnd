package com.bookstoreapp.util.implementation;

import com.bookstoreapp.util.IResetPasswordTemplate;
import org.springframework.stereotype.Component;

@Component
public class ResetPasswordTemplate implements IResetPasswordTemplate {
    @Override
    public String getPasswordTemplate(String appUrl) {
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
                "<p>Please click the below button to reset your password.</p>\n" +
                "\n" +
                "<a href="+appUrl+"><Button class=\"button\">Reset Password</Button></a>"+
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html> \n");
    }
}
