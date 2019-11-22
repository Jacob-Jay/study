package com.jq.base.io;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-18 18:55
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
        byte[] b = new byte[1024];
       RandomAccessFile file = new RandomAccessFile( "D:\\own\\github\\study\\netty\\src\\main\\resources\\file\\fileoutPutStream.txt"
               ,"rw");
        System.out.println(file.length());
        getPosition(file);
        file.write("w".getBytes(),0,1);
        file.read(b,0,1024);
        System.out.println(new String(b,0,10));
        file.seek(2);
        file.write("w".getBytes(),0,1);
        file.seek(0);
        int read = file.read(b, 0, 3);
        System.out.println(new String(b,0,read));
        getPosition(file);
        getPosition(file);
        getPosition(file);
        getPosition(file);
        file.seek(0);
        file.skipBytes(37);
        file.write("12345".getBytes());

    }

    private static void getPosition(RandomAccessFile file) throws IOException {
        System.out.println(file.getFilePointer());
    }


    public static void t5() throws Exception {
        ByteArrayOutputStream btout = new ByteArrayOutputStream();
        btout.write("jq".getBytes("utf-8"));
        System.out.println(new String(btout.toByteArray(),"utf-8"));
    }


    public static void t3() throws IOException {
        Student student = new Student();
        student.setAge(10);
        student.setName("jq");

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(new Student("stw", 20));
        OutputStream outPutStream = getOutPutStream();
        ObjectOutputStream dataOutputStream = new ObjectOutputStream(outPutStream);
//        dataOutputStream.write(1);
        dataOutputStream.writeObject(students);
        dataOutputStream.flush();
        dataOutputStream.close();
    }
    public static void t4() throws IOException, ClassNotFoundException {

        InputStream inputStream = getInputStream();
      ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        List<Student> students = (List<Student>  ) objectInputStream.readObject();
        System.out.println(students);
    }


    public static  void t1() throws Exception {
        BufferedOutputStream outputStream = null;
        try {
            String path = "D:\\own\\github\\study\\netty\\src\\main\\resources\\file\\fileoutPutStream.txt";
            outputStream = new BufferedOutputStream(new FileOutputStream(path,true));
            outputStream.write("abc".getBytes("utf-8"));
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }


    public static void t2() throws IOException {
         int perlineSize = 72;
        int allSize = 94;
        int first = 33;
        int start = first;
        OutputStream outputStream = getOutPutStream();
        while (true) {
            for(int i = start;i<start+perlineSize;i++) {
                outputStream.write(((i-first    )%allSize)+first);
            }
            outputStream.write('\r');
            outputStream.write('\n');
            outputStream.flush();
            start = ((start + 1) - first) % allSize + first;
        }

    }

    public static OutputStream getOutPutStream() {
        String path = "D:\\own\\github\\study\\netty\\src\\main\\resources\\file\\fileoutPutStream.txt";
        try {
            FileOutputStream outputStream = new FileOutputStream(path, false);

            return outputStream;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream getInputStream() {
        String path = "D:\\own\\github\\study\\netty\\src\\main\\resources\\file\\fileoutPutStream.txt";
        try{

            FileInputStream outputStream = new FileInputStream(path);
            return outputStream;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
