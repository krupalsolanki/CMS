//SendEmail.java
//Satyam Singh(satyam.mgs@gmail.com)
package mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    private Session session;
    //private String bodyOfMail;
    private String fromMail;
    private String subjectOfMail;
    private String toMail;
    private Transport transport;

    public SendEmail(String subject) throws MessagingException, UnsupportedEncodingException {
        try {

            subjectOfMail = subject;
            fromMail = "Compassites Software Solutions Pvt Ltd.";
            toMail = "satyam.mgs@gmail.com";

            // bodyOfMail="Hi<br>Ye humare mail ka badan hai.."
            //        + "Is likhit vakyakhand ke samapt hone ke baad ek chitra prakat hona chahiye,"
            //        + "jiske madhyam se hum ye pata karenge ki aapne ye e-mail dekha hai ya nahi.";

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.debug", "true");
            session = Session.getInstance(props);

            transport = session.getTransport("smtp");
            transport.connect("compassitessolution@gmail.com", "developer@compass");


            System.out.println("Email Sent to " + toMail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void composeSend(String toMail, String content, String firstName) throws MessagingException, UnsupportedEncodingException {

        Message msg = new MimeMessage(session);
        //For Read Receipt
        msg.setHeader("Return-Receipt-To", "<compassitessolution@gmail.com>");
        //Delivery notification
        msg.setHeader("Disposition-Notification-To", "<compassitessolution@gmail.com>");
        msg.setSubject(subjectOfMail);
        InternetAddress from = new InternetAddress("compassitessolution@gmail.com", fromMail);
        InternetAddress to = new InternetAddress(toMail, "Compassites");
        msg.addRecipient(Message.RecipientType.TO, to);
        msg.setFrom(from);
        Multipart multipart = new MimeMultipart("related");
        BodyPart htmlPart = new MimeBodyPart();
        content = content.replaceAll("##email##", toMail);
        content = content.replaceAll("##fName##", firstName);
        
        htmlPart.setContent("<html><body>" + content + "<br/>" + "<img src=\"54.225.23.238/test.php?email="+toMail+"\"/></body></html>", "text/html");
        multipart.addBodyPart(htmlPart);
        //BodyPart imgPart=new MimeBodyPart();

        // Loading the image
        //DataSource ds=new FileDataSource("localhost/test.php");
        //imgPart.setDataHandler(new DataHandler(ds));
        //URL url = new URL("http://localhost/test.php");
        //imgPart.setDataHandler(new DataHandler(url));
        //Setting the header
        //imgPart.setHeader("Content-ID","<the-img-1>");
        //multipart.addBodyPart(imgPart);

        //Attaching the multi-part to the message
        msg.setContent(multipart);
        System.out.println("Message setting complete");
        transport.sendMessage(msg, msg.getAllRecipients());
        System.out.println("Email sent to " + toMail);
    }

    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
        //SendEmail se=new SendEmail();
        int i = 0;
        while (i < 500) {

            try {
                //  se.composeSend("12030142039@sicsr.ac.in");
                if ((i + 1) % 50 == 0) {
                    Thread.sleep(60000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
            System.out.println("Mail sent : " + i);
        }
    }
}
