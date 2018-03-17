import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by guoyong on 2018/1/27.
 */
public class TestManager {


    @Test
    public void test() throws IOException {
        File file = new File("files/root/chongshi.jpg");
        BasicFileAttributeView basicView = Files.getFileAttributeView(Paths.get("files/root/chongshi.jpg"),
                BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        Instant instant = Instant.ofEpochMilli(basicView.readAttributes().lastModifiedTime().toMillis());

        System.out.println(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
    }

    @Test
    public void test1() {
        String path = "/root/123";
        System.out.println(path.split(File.separator)[2]);
    }
}
