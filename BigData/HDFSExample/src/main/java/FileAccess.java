import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class FileAccess
{
    private FileSystem hdfs;

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     * for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) throws Exception
    {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        configuration.set("dfs.support.append", "true");
        configuration.set("dfs.replication", "1");
        System.setProperty("HADOOP_USER_NAME", "root");

        hdfs = FileSystem.get(new URI(rootPath), configuration);
    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws IOException {
        Path filePath = new Path(path);
        if (path.contains(".")) {
            if (hdfs.exists(filePath)) {
                hdfs.delete(filePath, true);
            }
            hdfs.create(filePath);
        }
        else {
            hdfs.mkdirs(filePath);
        }
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException {
        try(BufferedWriter w = new BufferedWriter(new OutputStreamWriter(hdfs.append(new Path(path))))) {
            w.write(content);
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException {
        String out;
        try (FSDataInputStream in = hdfs.open(new Path(path))){
            out = IOUtils.toString(in, "UTF-8");
        }
        return out;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException {
        hdfs.delete(new Path(path), true);
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws IOException {
        return hdfs.isDirectory(new Path(path));
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws IOException {
        List<String> fileList = new ArrayList<>();
        RemoteIterator<LocatedFileStatus> fileStatusIterator = hdfs.listLocatedStatus(new Path(path));
        while (fileStatusIterator.hasNext()){
            LocatedFileStatus fileStatus = fileStatusIterator.next();
            if (fileStatus.isDirectory()){
                fileList.add(fileStatus.getPath().toString());
                fileList.addAll(list(fileStatus.getPath().toString()));
            } else {
                fileList.add(fileStatus.getPath().toString());
            }
        }
        return fileList;
    }
}
