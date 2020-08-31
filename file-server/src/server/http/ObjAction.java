package server.http;

/**
 * @author liubinhao
 * @date 2020/8/16
 */
public interface ObjAction<T> {

    /**
     * 操作行为
     * @param obj 操作对象
     * @param action on do or not
     */
    void manage(T obj,boolean action);
}
