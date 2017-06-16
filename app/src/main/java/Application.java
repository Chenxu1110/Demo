import com.lzy.okgo.OkGo;

/**
 * Created by 95394 on 2017/6/14.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);

    }
}
