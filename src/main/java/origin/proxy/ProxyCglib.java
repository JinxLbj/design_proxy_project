/**
 * createby : JinxLbj
 * date : 2019/4/19
 * desc : Cglib构建代理工厂
 **/

package origin.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import origin.PlayerNoInterface;
import origin.annotation.WithoutProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ProxyCglib implements MethodInterceptor{

    private PlayerNoInterface player;

    public ProxyCglib(PlayerNoInterface player) {
        this.player = player;
    }

    public PlayerNoInterface getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(player.getClass());
        enhancer.setCallback(this);
        return (PlayerNoInterface) enhancer.create();
    }


    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Annotation[] annotations = method.getDeclaredAnnotations();
        boolean needProxy = true;
        for (Annotation annotation:
                annotations) {
            if(annotation.annotationType().isAssignableFrom(WithoutProxy.class)){
                needProxy = false;
            }
        }
        if(needProxy) {
            System.out.println("proxy -> 安排采访");
        }
        return method.invoke(player, objects);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        ProxyCglib proxyFactory = new ProxyCglib(new PlayerNoInterface());
        PlayerNoInterface playerNoInterface = proxyFactory.getProxyInstance();
        playerNoInterface.interview();
        playerNoInterface.playBall();
    }
}
