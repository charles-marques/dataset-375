package net.oauth.v2;

/**
 * Created by IntelliJ IDEA.
 * User: Yutaka Obuchi
 * Date: 11/12/30
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class BaseResponseType {
    private static BaseResponseType ourInstance;

    public synchronized static BaseResponseType getInstance() {
        if (ourInstance == null) {
            // TODO make it possible to specify an extened class with config class
            //String className = "test";
            //if (className != null && !className.equals("")) {
            //    try {
            //        ourInstance = (BaseResponseType) Class.forName(className).newInstance();
            //    } catch (Exception e) {
                    //TODO log
            //    }
            //}
            //if (ourInstance == null)
                ourInstance = new BaseResponseType();
        }
        return ourInstance;
    }

    public static final String CODE = "code";
    public static final String TOKEN = "token";
    public static final String CODE_AND_TOKEN = "code_and_token";


    private BaseResponseType() {
    }

    public synchronized static void addExtension(BaseResponseType baseResponseType) {

        if (baseResponseType == null) {
            // TODO throw Exception
            return;
        }

        if(ourInstance != null && !(ourInstance instanceof BaseResponseType)){
            // TODO throw Exception
            return;
        }

        ourInstance = baseResponseType;
    }
}
