package org.basecucumbertaf.utils.mail;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.basecucumbertaf.utils.IGlobals;
import org.basecucumbertaf.utils.reporter.ExtentReportingService;
import org.basecucumbertaf.utils.excel.ExcelReader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

public class MailUtil {

    public static void sendMail(Map<String, String> map) {

        Properties props = new Properties();
        map.forEach(props::put);

        ExcelReader excelReader = new ExcelReader(IGlobals.mail_exl_path, "mail");
        List<Map<String, String>> allSheetData = excelReader.getAllSheetData();

        String sender_mail = allSheetData.get(0).get("sendermail");
        String sender_pwd = CryptoUtil.getDecryptedPassword(allSheetData.get(0).get("senderpassword"));
        String recipientslist = allSheetData.get(0).get("recipientslist");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender_mail, sender_pwd);
            }
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        try {

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setSubject("Test Report by Automation", "UTF-8");
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientslist));
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Find mail as report");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String folder_name = findLatestTestResultsFolderPath(System.getProperty("user.dir") + File.separator + "test-output") + File.separator;
            String filename = folder_name + ExtentReportingService.getReportFileName() + ".html";

            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("cucumbertaf.html");
            messageBodyPart.setHeader("Content-ID", "image_id");
            File f = new File(folder_name);
            String[] myFiles = f.list();
            String path = folder_name;
            for (int i = 0; i < Objects.requireNonNull(myFiles).length; i++) {
                myFiles[i] = path + myFiles[i];
            }

            String zipFile = System.getProperty("user.dir") + "/target/CucumberReport.zip";
            try {
                MailUtil.FileZipping.zip(myFiles, zipFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String filename2 = System.getProperty("user.dir") + "/target/CucumberReport.zip";

            try {
                messageBodyPart2.attachFile(filename2, "application/zip", "base64");
            } catch (IOException e) {
                e.printStackTrace();
            }

            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<h1>Attached is the zip</h1>", "text/html");
            multipart.addBodyPart(messageBodyPart2);

            msg.setContent(multipart);

            Transport.send(msg);
            System.out.println("EMail Sent Successfully with image!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static class FileZipping {

        private static final int BUFFER_SIZE = 4096;

        public static void zip(List<File> listFiles, String destZipFile) throws IOException {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFile));
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    zipDirectory(file, file.getName(), zos);
                } else {
                    zipFile(file, zos);
                }
            }
            zos.flush();
            zos.close();
        }

        public static void zip(String[] files, String destZipFile) throws IOException {
            List<File> listFiles = new ArrayList<>();
            for (String file : files) {
                listFiles.add(new File(file));
            }
            zip(listFiles, destZipFile);
        }


        private static void zipDirectory(File folder, String parentFolder, ZipOutputStream zos)
                throws IOException {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.isDirectory()) {
                    zipDirectory(file, parentFolder + "/" + file.getName(), zos);
                    continue;
                }
                zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                byte[] bytesIn = new byte[BUFFER_SIZE];
                int read;
                while ((read = bis.read(bytesIn)) != -1) {
                    zos.write(bytesIn, 0, read);
                }
                zos.closeEntry();
            }
        }

        private static void zipFile(File file, ZipOutputStream zos) throws IOException {
            zos.putNextEntry(new ZipEntry(file.getName()));
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read;
            while ((read = bis.read(bytesIn)) != -1) {
                zos.write(bytesIn, 0, read);
            }
            zos.closeEntry();
        }

    }

    public static File findLatestTestResultsFolderPath(String sdir) {
        File dir = new File(sdir);

        if (dir.isDirectory()) {
            File[] dirFiles = dir.listFiles((FileFilter) FileFilterUtils.directoryFileFilter());
            if (dirFiles != null && dirFiles.length > 0) {
                Arrays.sort(dirFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                return dirFiles[0];
            }
        }
        return null;
    }

}