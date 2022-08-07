package tokyo.ramune.farmmc.event;

import tokyo.ramune.farmmc.player.FarmPlayer;

public interface FarmEvent {

    FarmPlayer getPlayer();

    String getTitle();

    void onOpen();

    void onAccept();

    void onCancel();
}
