package com.mhm.create.prototype;

import java.io.*;
import java.util.Collection;

/**
 * @author Mhm
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2020-4-14 21:48
 */
public class CloneUtils implements Serializable, Cloneable {

    /**
     * 对象序列化的深拷贝
     *
     * @param obj
     * @return
     */
    public static Object cloneObject(Object obj) {
        Object cloneObj = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();

            ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            cloneObj = ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != oos) {
                    oos.close();
                }
                if (null != bos) {
                    bos.close();
                }
                if (null != oos) {
                    ois.close();
                }
            } catch (IOException e) {
                oos = null;
                bos = null;
                ois = null;
                e.printStackTrace();
            }
        }
        return cloneObj;
    }

    /**
     * 序列化深拷贝集合
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> Collection<T> cloneCollection(Collection<T> collection) {
        Collection<T> destClone = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(collection);
            oos.close();

            bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bis);
            destClone = (Collection<T>) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                bos.close();
                bis.close();
            } catch (IOException e) {
                oos = null;
                bos = null;
                bis = null;
                e.printStackTrace();
            }
        }

        return destClone;
    }
}
