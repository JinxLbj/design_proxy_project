/**
 * createby : JinxLbj
 * date : 2019/4/19
 * desc : 球员类（无需接口）
 **/

package origin;

import origin.annotation.WithoutProxy;

public class PlayerNoInterface {

    public void interview() {

        System.out.println("接受采访");

    }

    @WithoutProxy
    public void playBall() {

        System.out.println("开始打球");
    }

}
