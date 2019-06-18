package cn.bucheng;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.junit.Test;

import java.io.*;

/**
 * @author ：yinchong
 * @create ：2019/6/18 9:29
 * @description：ssh连接测试
 * @modified By：
 * @version:
 */
public class SSHTest {

    public static void main(String[] args) {
        topTest();
    }


    @Test
    public static void topTest() {
        try {
            Connection conn = new Connection("192.168.12.110");
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword("root",
                    "introcks");
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed");
            }
            Session sess = conn.openSession();
            sess.requestPTY("bash");
            sess.startShell();
            InputStream stdout = new StreamGobbler(sess.getStdout());
            InputStream stderr = new StreamGobbler(sess.getStderr());
            BufferedReader stdoutReader = new BufferedReader(
                    new InputStreamReader(stdout));
            BufferedReader stderrReader = new BufferedReader(
                    new InputStreamReader(stderr));
            PrintWriter out = new PrintWriter(sess.getStdin());
            String temp = "iotop -P -n 1 -k  -b";
            out.println(temp);
            out.flush();
            System.out.println("Here is the output from stderr:");
            String line;
            while (true) {
                line = stdoutReader.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            sess.close();
            conn.close();
            System.out.println("close connection");
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }
}
