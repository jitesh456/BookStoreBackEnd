package com.bookstoreapp.util.implementation;

import com.bookstoreapp.model.BookCart;
import com.bookstoreapp.model.Cart;
import com.bookstoreapp.model.User;
import com.bookstoreapp.util.IOrderPlaceTemplet;
import org.springframework.stereotype.Component;

@Component
public class OrderPlaceTemplet implements IOrderPlaceTemplet {
    @Override
    public String placeOrderTemplet(Cart cart, User user) {
        return ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "    <title></title>\n" +
                "    <!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]-->\n" +
                "    <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
                "    <!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "    <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG></o:AllowPNG>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "    </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]-->\n" +
                "    <style></style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"es-wrapper-color\">\n" +
                "        <!--[if gte mso 9]>\n" +
                "            <v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "                <v:fill type=\"tile\" color=\"#efefef\"></v:fill>\n" +
                "            </v:background>\n" +
                "        <![endif]-->\n" +
                "        <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td class=\"esd-email-paddings\" valign=\"top\">\n" +
                "                        \n" +
                "                        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" esd-custom-block-id=\"1754\" align=\"center\">\n" +
                "                                        <table class=\"es-content-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p10t es-p10b es-p20r es-p20l\" esd-general-paddings-checked=\"false\" align=\"left\">\n" +
                "                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"560\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table style=\"border-radius: 0px; border-collapse: separate;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text es-p10t es-p15b\" align=\"center\">\n" +
                "                                                                                        <h1>Thanks for your order<br></h1>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text es-p5t es-p5b es-p40r es-p40l\" align=\"center\">\n" +
                "                                                                                        <p style=\"color: #333333;\">You'll receive an email when your items are shipped. If you have any questions, Call us +91 98568455.</p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                \n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" esd-custom-block-id=\"1758\" align=\"center\">\n" +
                "                                        <table class=\"es-content-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p10t es-p10b es-p20r es-p20l\" esd-general-paddings-checked=\"false\" align=\"left\">\n" +
                "                                                        <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"270\" valign=\"top\"><![endif]-->\n" +
                "                                                        <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"es-m-p0r es-m-p20b esd-container-frame\" width=\"270\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text es-p20l\" align=\"left\">\n" +
                "                                                                                        <h4>ITEMS ORDERED</h4>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                        <!--[if mso]></td><td width=\"20\"></td><td width=\"270\" valign=\"top\"><![endif]-->\n" +
                "                                                        <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"270\" align=\"left\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text\" align=\"left\">\n" +
                "                                                                                        <table style=\"width: 100%;\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\">\n" +
                "                                                                                            <tbody>\n" +
                "                                                                                                <tr>\n" +
                "                                                                                                    <td><span style=\"font-size:13px;\">BOOK NAME</span></td>\n" +
                "                                                                                                    <td style=\"text-align: center;\" width=\"60\"><span style=\"font-size:13px;\"><span style=\"line-height: 100%;\">QTY</span></span></td>\n" +
                "                                                                                                    <td style=\"text-align: center;\" width=\"100\"><span style=\"font-size:13px;\"><span style=\"line-height: 100%;\">PRICE</span></span></td>\n" +
                "                                                                                                </tr>\n" +
                "                                                                                            </tbody>\n" +
                "                                                                                        </table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                                                                appendTable(cart) +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p5t es-p30b es-p40r es-p20l\" align=\"left\">\n" +
                "                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"540\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text\" align=\"right\">\n" +
                "                                                                                        <table style=\"width: 500px;\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\" align=\"right\">\n" +
                "                                                                                            <tbody>\n" +
                "                                                                                                <tr>\n" +
                "                                                                                                    <td style=\"text-align: right; font-size: 18px; line-height: 150%;\">Total Quantity:</td>\n" +
                "                                                                                                    <td style=\"text-align: right; font-size: 18px; line-height: 150%;\">"+cart.quantity+"</td>\n" +

                "                                                                                                    <td style=\"text-align: right; font-size: 18px; line-height: 150%;\"><strong> Total Price:</strong></td>\n" +
                "                                                                                                    <td style=\"text-align: right; font-size: 18px; line-height: 150%; color: #d48344;\"><strong>RS:"+cart.totalPrice+"</strong></td>\n" +
                "                                                                                                </tr>\n" +
                "                                                                                            </tbody>\n" +
                "                                                                                        </table>\n" +
                "                                                                                        <p style=\"line-height: 150%;\"><br></p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>                                \n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
    }

    public String orderTable(BookCart bookCart){
        return ("  <tr>\n" +
                "                                                    <td class=\"esd-structure es-p5t es-p10b es-p20r es-p20l\" esd-general-paddings-checked=\"false\" align=\"left\">\n" +
                "                                                        <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"178\" valign=\"top\"><![endif]-->\n" +
                "                                                        <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"es-m-p0r es-m-p20b esd-container-frame\" width=\"178\" valign=\"top\" align=\"center\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-image\" align=\"center\" style=\"font-size:0\"><img src=\"https://i.ibb.co/py4Wq3N/book-Cover.png\" alt=\"book Image\" class=\"adapt-img\" title=\"Book Cover\" width=\"125\"></a></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                        <!--[if mso]></td><td width=\"20\"></td><td width=\"362\" valign=\"top\"><![endif]-->\n" +
                "                                                        <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td class=\"esd-container-frame\" width=\"362\" align=\"left\">\n" +
                "                                                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-text\" align=\"left\">\n" +
                "                                                                                        <p><br></p>\n" +
                "                                                                                        <table style=\"width: 100%;\" class=\"cke_show_border\" cellspacing=\"1\" cellpadding=\"1\" border=\"0\">\n" +
                "                                                                                            <tbody>\n" +
                "                                                                                                <tr>\n" +
                "                                                                                                    <td style=\"text-align: center;\" width=\"60\">"+bookCart.book.name+"<br></td>\n" +
                "                                                                                                    <td style=\"text-align: center;\" width=\"60\">"+bookCart.bookQuantity+"</td>\n" +
                "                                                                                                    <td style=\"text-align: center;\" width=\"100\">"+bookCart.book.price*bookCart.bookQuantity+"</td>\n" +
                "                                                                                                </tr>\n" +
                "                                                                                            </tbody>\n" +
                "                                                                                        </table>\n" +
                "                                                                                        <p><br></p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
                "                                                    </td>\n" +
                "                                                </tr>");
    }

    public StringBuffer appendTable(Cart cart)
    {
         StringBuffer result=new StringBuffer();
        for(int i=0;i<cart.bookCartList.size();i++){
            result.append(orderTable(cart.bookCartList.get(i)));
        }
        return result;
    }

}
