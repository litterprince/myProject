package main.java.exam.threadpool;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * 题目五：
 假设本地有一个文件夹，文件夹下面有若干文件（文件数大于50小于100），文件的存储格式是文本格式（后缀名是.txt)，文件的大小每个文件不会超过100k。
 文件格式如下：
 2000102，100，98.32000103，101，73.32000104，102，98.32000105，100，101.32000106，101，45.3......
 文件格式说明：文件每行都由三列构成，第一列是一个id，第二列是分组groupId, 第三列是指标quota。
 id的数据类型是String, groupId的数据类型String, quota的数据类型float。
 功能要求：
 1.把所有文件里面的内容按照分组进行排序，输出所有文件按照分组升序排序之后，每个分组下面的最小指标值。比如上面的数据输出结果为：
 100，2000102，98.3101，2000106，45.3102，2000104，98.3
 非功能要求
 1.文件读取要有线程池来执行，线程池的大小固定为10，文件内容需要存储到指定的内容数据结构当中。
 2.查找要求有独立线程来执行，直接消费读取线程池产生的内存数据结构。
 3.文件读取和排序要求并发作业，文件读取只要产生了数据，就可以把数据交给排序线程进行消费，计算最小值。
 代码要求
 1.重上面的要求语意里面抽象出合适的设计模式。
 2.需要考虑多线程的并发控制，同步机制。
 3.代码实现只能用JDK1.6或者1.8自带的工具类
 * */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);

        //获取用户输入的字符串
        String path=null;
        System.out.print("请输入目录路径:");
        path=sc.nextLine();
        File file = new File(path);
        if(file.exists() && file.isDirectory()) {
            ConcurrentTaskExecutor executor = new ConcurrentTaskExecutor();
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            executor.executeTask(files);
        }else{
            System.out.println("输入路径有误!path="+path);
            exit(0);
        }
    }
}