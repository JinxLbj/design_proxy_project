/**
 * createby : JinxLbj
 * date : 2019/4/19
 * desc : 构建代理工厂
 **/

package origin.proxy;

import origin.Player;
import origin.PlayerInterface;
import origin.PlayerNoInterface;
import origin.annotation.WithoutProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private Player player;

    public ProxyFactory(Player player) {
        this.player = player;
    }

    public PlayerInterface getProxyInstance() {
        return (PlayerInterface) Proxy.newProxyInstance(
                player.getClass().getClassLoader(),
                player.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Annotation[] annotations = method.getDeclaredAnnotations();
                        boolean needProxy = true;
                        for (Annotation annotation:
                                annotations) {
                            if(annotation.annotationType().isAssignableFrom(WithoutProxy.class)){
                                needProxy = false;
                            }
                        }
                        if(needProxy) {
                            System.out.println("proxy -> 安排");
                        }
                        return method.invoke(player, args);
                    }
                }
        );
    }

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new Player());
        PlayerInterface playerInterface = proxyFactory.getProxyInstance();
        playerInterface.interview();
        playerInterface.playBall();
    }

}
