import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * Created by 86181 on 2020/3/3.
 * 1.将视频进行分块
 * 1.1 先确定要分块的视频
 * 1.2 再确定要分块的路径
 * 1.3 根据视频总长度 计算出分块数量和每块大小
 * 1.4 通过RandomAccessFile的方式将分块写进文件夹
 */
public class TestChunk {

    //测试文件分块方法
    @Test
    public  void testChunk() throws IOException {

        //指定视频文件
        File sourceFile1 = new File("D:\\study\\ffmpeg\\video\\5.什么是FastDFS？以及话术编写.mp4");
        //指定视频分块的路径
        String chunkPath = "D:\\study\\ffmpeg\\video\\chunk\\";
        File chunkFolder = new File(chunkPath);
        if(!chunkFolder.exists()){
            chunkFolder.mkdirs();
        }
        //分块大小
        //long chunkSize = 1024*1024*5;
        long chunkNum=20;
        //分块数量
        long chunkSize = (long) Math.ceil(sourceFile1.length() * 1.0 / chunkNum);
        if(chunkNum<=0){
            chunkNum = 1;
        }

        //缓冲区大小
        byte[] b = new byte[1024];
        //使用RandomAccessFile访问文件
        RandomAccessFile raf_read = new RandomAccessFile(sourceFile1, "r");
        long startTime = System.currentTimeMillis();
        //将视频使用SHA1加密
        String sourceFile = SHA1Utils.fileSHA1(sourceFile1);
        System.err.println("----------------------加密后的源文件"+sourceFile);
        //分块
        for(int i=0;i<chunkNum;i++){
            //创建分块文件
            File file = new File(chunkPath+i);
            boolean newFile = file.createNewFile();
            if(newFile){
                //向分块文件中写数据
                RandomAccessFile raf_write = new RandomAccessFile(file, "rw");
                int len = -1;
                while((len = raf_read.read(b))!=-1){
                    raf_write.write(b,0,len);
                    if(file.length()>chunkSize){
                        break;
                    }
                }
                raf_write.close();
            }
        }
        raf_read.close();
    }

    /**
     * 2.测试文件合并方法
     * 2.1 确定合并文件的路径
     * 2.2 确定合并文件的文件
     * 2.3 通过RandomAccessFile将分块的文件写入指定好的mergeFile文件
     * @throws IOException
     */
    @Test
    public void testMerge() throws IOException {
        //块文件目录
        File chunkFolder = new File("D:\\study\\ffmpeg\\video\\chunk\\");
        //合并文件
        File mergeFile = new File("D:\\study\\ffmpeg\\video\\5.什么是FastDFS？以及话术编写555.mp4");
        if(mergeFile.exists()){
            mergeFile.delete();
        } 
        //创建新的合并文件
        mergeFile.createNewFile();
        //用于写文件
        RandomAccessFile raf_write = new RandomAccessFile(mergeFile, "rw");
        //指针指向文件顶端
        raf_write.seek(0);
        //缓冲区
        byte[] b = new byte[1024];
        //分块列表
        File[] fileArray = chunkFolder.listFiles();
        // 转成集合，便于排序
        List<File> fileList = new ArrayList<File>(Arrays.asList(fileArray));
        // 从小到大排序
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
                    return -1;
                } 
                return 1;
            }
        });
        //合并文件
        for(File chunkFile:fileList){
            RandomAccessFile raf_read = new RandomAccessFile(chunkFile,"r");
            int len = -1;
            while((len=raf_read.read(b))!=-1){
                raf_write.write(b,0,len);
            } 
            raf_read.close();
        }

        //合并之后再加密 如果视频文件一样的话，那么加完密也应该是一样的。所以对比一下，如果一样那么是一致的
        long startTime = System.currentTimeMillis();
        String mergeFile1 = SHA1Utils.fileSHA1(mergeFile);
        System.err.println("----------------------加密后的合并文件"+mergeFile1);
        long startTime1 = System.currentTimeMillis();
        String sourceFile = SHA1Utils.fileSHA1(new File("D:\\study\\ffmpeg\\video\\5.什么是FastDFS？以及话术编写.mp4"));
        System.err.println("----------------------加密后的源文件"+sourceFile);
        if(mergeFile1.equals(sourceFile)){
            System.out.println("------------------------一样的");
        }
    }
}
