/**
 * createby : JinxLbj
 * date : 2019/4/19
 * desc : 拒绝代理
 **/

package origin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//声明生命周期
@Retention(RetentionPolicy.RUNTIME)
//保证只能在方法上使用
@Target({ElementType.METHOD})
public @interface WithoutProxy {

}
