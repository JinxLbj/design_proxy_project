/**
 * createby : JinxLbj
 * date : 2019/4/19
 * desc :
 **/

package origin.proxy;

import origin.Player;
import origin.PlayerInterface;

public class PlayerProxy implements PlayerInterface {

    private Player player;

    public PlayerProxy(Player player) {
        this.player = player;
    }

    public void interview() {

        System.out.println("proxy -> 安排采访");

        player.interview();

    }

    public void playBall() {

        player.playBall();
    }

    public static void main(String[] args) {
        PlayerProxy playerProxy = new PlayerProxy(new Player());
        playerProxy.interview();
    }

}
