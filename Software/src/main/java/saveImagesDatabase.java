import com.jcraft.jsch.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class saveImagesDatabase {
    private static final String REMOTE_HOST = "176.31.163.242";
    private static final String USERNAME = "ubuntu";
    private static final String PASSWORD = "1508VAlentin!!";
    private static final int REMOTE_PORT = 22;
    private static final int SESSION_TIMEOUT = 10000;
    private static final int CHANNEL_TIMEOUT = 5000;
    public static List<String>  AllImages=new ArrayList<String>();

    public static List<String> getAllImages(){
        System.out.println(AllImages);
        return AllImages;
    }

    public static void whenUploadFileUsingJsch_thenSuccess(String imageName) {

            String localFile = "img_tmp/" + imageName;
            String remoteFile = imageName;

            Session jschSession = null;

            int test = AllImages.size() + 1;
            AllImages.add(remoteFile);

            try {

                JSch jsch = new JSch();
                jsch.setKnownHosts("/home/briez-banuls/.ssh/known_hosts");
                jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);

                // authenticate using private key
                // jsch.addIdentity("/home/mkyong/.ssh/id_rsa");

                // authenticate using password
                jschSession.setPassword(PASSWORD);

                // 10 seconds session timeout
                jschSession.connect(SESSION_TIMEOUT);

                Channel sftp = jschSession.openChannel("sftp");

                // 5 seconds timeout
                sftp.connect(CHANNEL_TIMEOUT);

                ChannelSftp channelSftp = (ChannelSftp) sftp;

                // transfer file from local to remote server
                channelSftp.put(localFile, remoteFile);

                // download file from remote server to local
                // channelSftp.get(remoteFile, localFile);

                channelSftp.exit();

            } catch (JSchException | SftpException e) {

                e.printStackTrace();

            } finally {
                if (jschSession != null) {
                    jschSession.disconnect();
                }
            }

            System.out.println("Done");
    }



}